package nc.itemblock.generator;

import net.minecraft.block.Block;

import nc.NuclearCraft;
import nc.itemblock.ItemBlockNC;

public class ItemBlockSteamDecompressor extends ItemBlockNC {

    public ItemBlockSteamDecompressor(Block block) {
        super(
            block,
            "Takes in dense steam and decompresses it to",
            "form steam at a maximum rate of " + NuclearCraft.steamDecompressRate + " mB/t of",
            "dense steam to " + NuclearCraft.steamDecompressRate * 1000 + " mB/t of steam.");
    }
}
