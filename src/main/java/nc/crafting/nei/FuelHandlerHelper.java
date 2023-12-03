package nc.crafting.nei;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import codechicken.nei.ItemList;
import codechicken.nei.recipe.FurnaceRecipeHandler.FuelPair;
import nc.util.BurnTime;

public final class FuelHandlerHelper {

    private static List<FuelPair> includedFuels;
    private static Set<Item> excludedFuels;

    public static Set<Item> excludedFuels() {
        if (excludedFuels == null) {
            excludedFuels = new HashSet<>();

            excludedFuels.add(Item.getItemFromBlock(Blocks.brown_mushroom));
            excludedFuels.add(Item.getItemFromBlock(Blocks.red_mushroom));
            excludedFuels.add(Item.getItemFromBlock(Blocks.standing_sign));
            excludedFuels.add(Item.getItemFromBlock(Blocks.wall_sign));
            excludedFuels.add(Item.getItemFromBlock(Blocks.wooden_door));
            excludedFuels.add(Item.getItemFromBlock(Blocks.trapped_chest));
        }

        return excludedFuels;
    }

    public static List<FuelPair> includedFuels() {
        if (includedFuels == null) {
            includedFuels = new ArrayList<>();
            Set<Item> excludedFuels = excludedFuels();

            for (ItemStack itemStack : ItemList.items) {
                if (!excludedFuels.contains(itemStack.getItem())) {
                    int burnTime;

                    if ((burnTime = BurnTime.getItemBurnTime(itemStack)) > 0) {
                        includedFuels.add(new FuelPair(itemStack, burnTime));
                    }
                }
            }
        }

        return includedFuels;
    }
}
