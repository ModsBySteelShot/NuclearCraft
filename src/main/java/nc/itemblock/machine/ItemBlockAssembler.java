package nc.itemblock.machine;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;

public class ItemBlockAssembler extends ItemBlockNC {

    public ItemBlockAssembler(Block block) {
        super(
            block,
            "A very useful machine that uses RF to automate Nuclear Workspace recipes.",
            "Use sticks to block slots. Will not leave empty slots unless in 'use' mode.",
            "Can accept speed and efficiency upgrades.");
    }
}
