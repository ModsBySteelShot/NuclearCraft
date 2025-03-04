package nc.itemblock.generator;

import net.minecraft.block.Block;

import nc.NuclearCraft;
import nc.itemblock.ItemBlockNC;

public class ItemBlockDenseSteamDecompressor extends ItemBlockNC {

    public ItemBlockDenseSteamDecompressor(Block block) {
        super(
            block,
            "Takes in superdense steam and decompresses it to",
            "form dense steam at a maximum rate of " + NuclearCraft.steamDecompressRate + " mB/t of",
            "superdense steam to " + NuclearCraft.steamDecompressRate * 1000 + " mB/t of dense steam.");
    }
}
