package nc.tile.processor;

import java.util.List;

import javax.annotation.Nonnull;

import it.unimi.dsi.fastutil.ints.IntList;
import nc.network.tile.ProcessorUpdatePacket;
import nc.recipe.*;
import nc.recipe.ingredient.*;
import nc.tile.ITileGui;
import nc.tile.dummy.IInterfaceable;
import nc.tile.fluid.ITileFluid;
import nc.tile.internal.fluid.*;
import nc.tile.internal.inventory.ItemOutputSetting;
import nc.tile.inventory.ITileInventory;
import nc.tile.processor.info.ProcessorContainerInfo;
import nc.util.*;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraftforge.fluids.FluidStack;

public abstract interface IProcessor<TILE extends TileEntity & IProcessor<TILE, INFO>, INFO extends ProcessorContainerInfo<TILE, INFO>> extends ITickable, ITileInventory, ITileFluid, IInterfaceable, ITileGui<TILE, ProcessorUpdatePacket, INFO> {
	
	@Override
	public INFO getContainerInfo();
	
	public BasicRecipeHandler getRecipeHandler();
	
	public double getBaseProcessTime();
	
	public double getBaseProcessPower();
	
	public boolean setRecipeStats();
	
	public boolean isProcessing();
	
	public boolean isHaltedByRedstone();
	
	public boolean readyToProcess();
	
	public boolean canProcessInputs();
	
	public void process();
	
	public void finishProcess();
	
	public default void loseProgress() {
		
	}
	
	public default int getProcessTime() {
		return Math.max(1, NCMath.toInt(Math.ceil(getBaseProcessTime() / getSpeedMultiplier())));
	}
	
	public default int getProcessPower() {
		return NCMath.toInt(Math.ceil(getBaseProcessPower() * getPowerMultiplier()));
	}
	
	public default int getProcessEnergy() {
		return getProcessTime() * getProcessPower();
	}
	
	public double getSpeedMultiplier();
	
	public double getPowerMultiplier();
	
	public double getCurrentTime();
	
	public boolean getIsProcessing();
	
	public boolean getHasConsumed();
	
	public void setHasConsumed(boolean hasConsumed);
	
	public List<ItemStack> getItemInputs(boolean consumed);
	
	public List<Tank> getFluidInputs(boolean consumed);
	
	public @Nonnull NonNullList<ItemStack> getConsumedStacks();
	
	public @Nonnull List<Tank> getConsumedTanks();
	
	public RecipeInfo<BasicRecipe> getRecipeInfo();
	
	public List<IItemIngredient> getItemIngredients();
	
	public List<IItemIngredient> getItemProducts();
	
	public List<IFluidIngredient> getFluidIngredients();
	
	public List<IFluidIngredient> getFluidProducts();
	
	public void refreshAll();
	
	public void refreshRecipe();
	
	public void refreshActivity();
	
	public void refreshActivityOnProduction();
	
	public default boolean hasConsumed() {
		if (!getContainerInfo().consumesInputs) {
			return false;
		}
		
		if (getTileWorld().isRemote) {
			return getHasConsumed();
		}
		
		for (ItemStack stack : getConsumedStacks()) {
			if (!stack.isEmpty()) {
				return true;
			}
		}
		for (Tank tank : getConsumedTanks()) {
			if (!tank.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	public default boolean canProduceProducts() {
		INFO info = getContainerInfo();
		int itemInputSize = info.itemInputSize;
		int itemOutputSize = info.itemOutputSize;
		
		List<ItemStack> stacks = getInventoryStacks();
		for (int i = 0; i < itemOutputSize; ++i) {
			int slot = i + itemInputSize;
			ItemOutputSetting outputSetting = getItemOutputSetting(slot);
			
			if (outputSetting == ItemOutputSetting.VOID) {
				stacks.set(slot, ItemStack.EMPTY);
				continue;
			}
			
			IItemIngredient product = getItemProducts().get(i);
			int productMaxStackSize = product.getMaxStackSize(0);
			ItemStack productStack = product.getStack();
			
			if (productMaxStackSize <= 0) {
				continue;
			}
			if (productStack == null || productStack.isEmpty()) {
				return false;
			}
			else {
				ItemStack stack = stacks.get(slot);
				if (!stack.isEmpty()) {
					if (!stack.isItemEqual(productStack)) {
						return false;
					}
					else if (outputSetting == ItemOutputSetting.DEFAULT && stack.getCount() + productMaxStackSize > stack.getMaxStackSize()) {
						return false;
					}
				}
			}
		}
		
		int fluidInputSize = info.fluidInputSize;
		int fluidOutputSize = info.fluidOutputSize;
		
		List<Tank> tanks = getTanks();
		for (int i = 0; i < fluidOutputSize; ++i) {
			int tankIndex = i + fluidInputSize;
			TankOutputSetting outputSetting = getTankOutputSetting(tankIndex);
			
			if (outputSetting == TankOutputSetting.VOID) {
				clearTank(tankIndex);
				continue;
			}
			
			IFluidIngredient product = getFluidProducts().get(i);
			int productMaxStackSize = product.getMaxStackSize(0);
			FluidStack productStack = product.getStack();
			
			if (productMaxStackSize <= 0) {
				continue;
			}
			if (productStack == null) {
				return false;
			}
			else {
				Tank tank = tanks.get(tankIndex);
				if (!tank.isEmpty()) {
					if (!tank.getFluid().isFluidEqual(productStack)) {
						return false;
					}
					else if (outputSetting == TankOutputSetting.DEFAULT && tank.getFluidAmount() + productMaxStackSize > tank.getCapacity()) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public default void consumeInputs() {
		RecipeInfo<BasicRecipe> recipeInfo;
		if (getHasConsumed() || (recipeInfo = getRecipeInfo()) == null) {
			return;
		}
		
		IntList itemInputOrder = recipeInfo.getItemInputOrder();
		if (itemInputOrder == AbstractRecipeHandler.INVALID) {
			return;
		}
		
		IntList fluidInputOrder = recipeInfo.getFluidInputOrder();
		if (fluidInputOrder == AbstractRecipeHandler.INVALID) {
			return;
		}
		
		INFO info = getContainerInfo();
		boolean consumesInputs = info.consumesInputs;
		int itemInputSize = info.itemInputSize;
		int fluidInputSize = info.fluidInputSize;
		
		NonNullList<ItemStack> consumedStacks = getConsumedStacks();
		List<Tank> consumedTanks = getConsumedTanks();
		
		if (consumesInputs) {
			for (int i = 0; i < itemInputSize; ++i) {
				if (!consumedStacks.get(i).isEmpty()) {
					consumedStacks.set(i, ItemStack.EMPTY);
				}
			}
			for (Tank tank : consumedTanks) {
				if (!tank.isEmpty()) {
					tank.setFluidStored(null);
				}
			}
		}
		
		List<ItemStack> stacks = getInventoryStacks();
		for (int i = 0; i < itemInputSize; ++i) {
			int itemIngredientStackSize = getItemIngredients().get(itemInputOrder.get(i)).getMaxStackSize(recipeInfo.getItemIngredientNumbers().get(i));
			ItemStack stack = stacks.get(i);
			
			if (itemIngredientStackSize > 0) {
				if (consumesInputs) {
					consumedStacks.set(i, new ItemStack(stack.getItem(), itemIngredientStackSize, StackHelper.getMetadata(stack)));
				}
				stack.shrink(itemIngredientStackSize);
			}
			
			if (stack.getCount() <= 0) {
				stacks.set(i, ItemStack.EMPTY);
			}
		}
		
		List<Tank> tanks = getTanks();
		for (int i = 0; i < fluidInputSize; ++i) {
			Tank tank = tanks.get(i);
			int fluidIngredientStackSize = getFluidIngredients().get(fluidInputOrder.get(i)).getMaxStackSize(recipeInfo.getFluidIngredientNumbers().get(i));
			if (fluidIngredientStackSize > 0) {
				if (consumesInputs) {
					consumedTanks.get(i).setFluidStored(new FluidStack(tank.getFluid(), fluidIngredientStackSize));
				}
				tank.changeFluidAmount(-fluidIngredientStackSize);
			}
			if (tank.getFluidAmount() <= 0) {
				tank.setFluidStored(null);
			}
		}
		
		if (consumesInputs) {
			setHasConsumed(true);
		}
	}
	
	public default void produceProducts() {
		INFO info = getContainerInfo();
		boolean consumesInputs = info.consumesInputs;
		int itemInputSize = info.itemInputSize;
		int fluidInputSize = info.fluidInputSize;
		
		NonNullList<ItemStack> consumedStacks = getConsumedStacks();
		List<Tank> consumedTanks = getConsumedTanks();
		
		if (consumesInputs) {
			for (int i = 0; i < itemInputSize; ++i) {
				consumedStacks.set(i, ItemStack.EMPTY);
			}
			for (int i = 0; i < fluidInputSize; ++i) {
				consumedTanks.get(i).setFluidStored(null);
			}
		}
		
		if ((consumesInputs && !getHasConsumed()) || getRecipeInfo() == null) {
			return;
		}
		
		if (!consumesInputs) {
			consumeInputs();
		}
		
		int itemOutputSize = info.itemOutputSize;
		
		List<ItemStack> stacks = getInventoryStacks();
		for (int i = 0; i < itemOutputSize; ++i) {
			int slot = i + itemInputSize;
			
			if (getItemOutputSetting(slot) == ItemOutputSetting.VOID) {
				stacks.set(slot, ItemStack.EMPTY);
				continue;
			}
			
			IItemIngredient product = getItemProducts().get(i);
			
			if (product.getMaxStackSize(0) <= 0) {
				continue;
			}
			
			ItemStack currentStack = stacks.get(slot);
			ItemStack nextStack = product.getNextStack(0);
			
			if (currentStack.isEmpty()) {
				stacks.set(slot, nextStack);
			}
			else if (currentStack.isItemEqual(product.getStack())) {
				int count = Math.min(getInventoryStackLimit(), currentStack.getCount() + nextStack.getCount());
				currentStack.setCount(count);
			}
		}
		
		int fluidOutputSize = info.fluidOutputSize;
		
		List<Tank> tanks = getTanks();
		for (int i = 0; i < fluidOutputSize; ++i) {
			int tankIndex = i + fluidInputSize;
			
			if (getTankOutputSetting(tankIndex) == TankOutputSetting.VOID) {
				clearTank(tankIndex);
				continue;
			}
			
			IFluidIngredient product = getFluidProducts().get(i);
			
			if (product.getMaxStackSize(0) <= 0) {
				continue;
			}
			
			Tank tank = tanks.get(tankIndex);
			FluidStack nextStack = product.getNextStack(0);
			
			if (tank.isEmpty()) {
				tank.setFluidStored(nextStack);
			}
			else if (tank.getFluid().isFluidEqual(product.getStack())) {
				tank.changeFluidAmount(nextStack.amount);
			}
		}
		
		if (consumesInputs) {
			setHasConsumed(false);
		}
	}
	
	public static <TILE extends TileEntity & IProcessor<TILE, INFO>, INFO extends ProcessorContainerInfo<TILE, INFO>> int energyCapacity(INFO info, double speedMultiplier, double powerMultiplier) {
		return NCMath.toInt(Math.ceil(RecipeStats.getProcessorMaxBaseProcessTime(info.name) / speedMultiplier) * Math.ceil(RecipeStats.getProcessorMaxBaseProcessPower(info.name) * powerMultiplier));
	}
}
