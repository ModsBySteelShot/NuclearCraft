package nc.container.machine;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import nc.tile.machine.TileFurnace;

public class ContainerFurnace extends ContainerFuelUserBase<TileFurnace> {

    public ContainerFurnace(InventoryPlayer inventory, TileFurnace tileEntity) {
        super(inventory, tileEntity);
    }

    @Override
    protected boolean validateItemStack(ItemStack itemStack) {
        return FurnaceRecipes.smelting()
            .getSmeltingResult(itemStack) != null;
    }
}
