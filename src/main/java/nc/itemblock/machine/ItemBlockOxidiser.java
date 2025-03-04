package nc.itemblock.machine;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;

public class ItemBlockOxidiser extends ItemBlockNC {

    public ItemBlockOxidiser(Block block) {
        super(
            block,
            "Uses RF to oxidise materials and make fuels more efficient.",
            "Can accept speed and efficiency upgrades.");
    }
}
