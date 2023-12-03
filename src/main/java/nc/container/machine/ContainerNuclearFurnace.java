package nc.container.machine;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import nc.tile.machine.TileNuclearFurnace;

public class ContainerNuclearFurnace extends ContainerFuelUserBase<TileNuclearFurnace> {

    public ContainerNuclearFurnace(InventoryPlayer inventory, TileNuclearFurnace tileEntity) {
        super(inventory, tileEntity);
    }

    @Override
    protected boolean validateItemStack(ItemStack itemStack) {
        return FurnaceRecipes.smelting()
            .getSmeltingResult(itemStack) != null;
    }
}
