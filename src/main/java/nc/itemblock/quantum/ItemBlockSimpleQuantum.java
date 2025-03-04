package nc.itemblock.quantum;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;

public class ItemBlockSimpleQuantum extends ItemBlockNC {

    public ItemBlockSimpleQuantum(Block block) {
        super(
            block,
            "A block that mimics the probabilistic",
            "quantum mechanical physics of a spin-1/2",
            "particle, such as an electron or a neutron.");
    }
}
