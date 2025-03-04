package nc.itemblock.generator;

import net.minecraft.block.Block;

import nc.NuclearCraft;
import nc.itemblock.ItemBlockNC;

public class ItemBlockSteamGenerator extends ItemBlockNC {

    public ItemBlockSteamGenerator(Block block) {
        super(
            block,
            "Generates RF from steam at a maximum",
            "rate of " + NuclearCraft.steamRFUsageRate + " mB/t to " + NuclearCraft.steamRFUsageRate * 2 + " RF/t",
            "(2 RF per 1 mB of Steam)");
    }
}
