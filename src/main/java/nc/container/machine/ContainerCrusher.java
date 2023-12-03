package nc.container.machine;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import nc.crafting.machine.CrusherRecipes;
import nc.tile.machine.TileCrusher;

public class ContainerCrusher extends ContainerFuelUserBase<TileCrusher> {

    public ContainerCrusher(InventoryPlayer inventory, TileCrusher tileEntity) {
        super(inventory, tileEntity);
    }

    @Override
    protected boolean validateItemStack(ItemStack itemStack) {
        return CrusherRecipes.instance()
            .validInput(itemStack);
    }
}
