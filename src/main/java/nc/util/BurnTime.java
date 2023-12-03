package nc.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

import cpw.mods.fml.common.registry.GameRegistry;
import nc.NuclearCraft;

public class BurnTime {

    public static int getItemBurnTime(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        } else {
            Item item = itemstack.getItem();
            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);
                if (block == Blocks.wooden_slab) {
                    return 8000 / NuclearCraft.metalFurnaceCookEfficiency;
                }
                if (block.getMaterial() == Material.wood) {
                    return 16000 / NuclearCraft.metalFurnaceCookEfficiency;
                }
                if (block == Blocks.coal_block) {
                    return 960000 / NuclearCraft.metalFurnaceCookEfficiency;
                }
            }
            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName()
                .equals("WOOD")) return 16000 / NuclearCraft.metalFurnaceCookEfficiency;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName()
                .equals("WOOD")) return 16000 / NuclearCraft.metalFurnaceCookEfficiency;
            if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName()
                .equals("WOOD")) return 16000 / NuclearCraft.metalFurnaceCookEfficiency;
            if (item == Items.stick) return 4000 / NuclearCraft.metalFurnaceCookEfficiency;
            if (item == Items.coal) return 96000 / NuclearCraft.metalFurnaceCookEfficiency;
            if (item == Items.lava_bucket) return 1200000 / NuclearCraft.metalFurnaceCookEfficiency;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 4000 / NuclearCraft.metalFurnaceCookEfficiency;
            if (item == Items.blaze_rod) return 144000 / NuclearCraft.metalFurnaceCookEfficiency;
            return (GameRegistry.getFuelValue(itemstack) * 64) / NuclearCraft.metalFurnaceCookEfficiency;
        }
    }
}
