package nc.crafting.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import codechicken.nei.ItemList;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.FurnaceRecipeHandler.FuelPair;
import codechicken.nei.recipe.TemplateRecipeHandler;
import nc.NuclearCraft;
import nc.gui.machine.GuiNuclearFurnace;
import nc.item.NCItems;

public class NuclearFurnaceRecipeHandler extends TemplateRecipeHandler {

    public static List<FuelPair> afuels;

    private static List<FuelPair> nuclearFuels() {
        List<FuelPair> includedFuels = new ArrayList<>();
        Set<Item> excludedFuels = FuelHandlerHelper.excludedFuels();

        for (ItemStack itemStack : ItemList.items) {
            if (!excludedFuels.contains(itemStack.getItem())) {
                int burnTime;

                if ((burnTime = getItemBurnTime(itemStack)) > 0) {
                    includedFuels.add(new FuelPair(itemStack, burnTime));
                }
            }
        }

        return includedFuels;
    }

    public static int getItemBurnTime(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            if (item == new ItemStack(NCItems.material, 1, 4).getItem() && item.getDamage(itemstack) == 4) {
                return (int) Math.ceil(
                    ((double) (NuclearCraft.nuclearFurnaceCookSpeed * 32) / NuclearCraft.nuclearFurnaceCookEfficiency)
                        * Math.ceil((double) 300 / NuclearCraft.nuclearFurnaceCookSpeed));
            } else if (item == new ItemStack(NCItems.material, 1, 5).getItem() && item.getDamage(itemstack) == 5) {
                return (int) Math.ceil(
                    ((double) (NuclearCraft.nuclearFurnaceCookSpeed * 32) / NuclearCraft.nuclearFurnaceCookEfficiency)
                        * Math.ceil((double) 300 / NuclearCraft.nuclearFurnaceCookSpeed));
            } else if (item == new ItemStack(NCItems.material, 1, 19).getItem() && item.getDamage(itemstack) == 19) {
                return (int) Math.ceil(
                    ((double) (NuclearCraft.nuclearFurnaceCookSpeed * 32) / NuclearCraft.nuclearFurnaceCookEfficiency)
                        * Math.ceil((double) 300 / NuclearCraft.nuclearFurnaceCookSpeed));
            } else if (item == new ItemStack(NCItems.material, 1, 20).getItem() && item.getDamage(itemstack) == 20) {
                return (int) Math.ceil(
                    ((double) (NuclearCraft.nuclearFurnaceCookSpeed * 32) / NuclearCraft.nuclearFurnaceCookEfficiency)
                        * Math.ceil((double) 300 / NuclearCraft.nuclearFurnaceCookSpeed));
            }
        }
        return 0;
    }

    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(50, 23, 18, 18), "nuclearfuel"));
        transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 18), "nuclearsmelting"));
    }

    public Class<? extends GuiContainer> getGuiClass() {
        return GuiNuclearFurnace.class;
    }

    public String getRecipeName() {
        return "Nuclear Furnace";
    }

    public TemplateRecipeHandler newInstance() {
        if (afuels == null) afuels = nuclearFuels();

        return super.newInstance();
    }

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("nuclearsmelting") && getClass() == NuclearFurnaceRecipeHandler.class) {// don't want
            // subclasses
            // getting a hold of
            // this
            @SuppressWarnings("unchecked")
            Map<ItemStack, ItemStack> recipes = (Map<ItemStack, ItemStack>) FurnaceRecipes.smelting()
                .getSmeltingList();
            for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
                arecipes.add(new NuclearSmeltingPair(recipe.getKey(), recipe.getValue()));
        } else super.loadCraftingRecipes(outputId, results);
    }

    public void loadCraftingRecipes(ItemStack result) {
        @SuppressWarnings("unchecked")
        Map<ItemStack, ItemStack> recipes = (Map<ItemStack, ItemStack>) FurnaceRecipes.smelting()
            .getSmeltingList();
        for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
            if (NEIServerUtils.areStacksSameType(recipe.getValue(), result))
                arecipes.add(new NuclearSmeltingPair(recipe.getKey(), recipe.getValue()));
    }

    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (inputId.equals("nuclearfuel") && getClass() == NuclearFurnaceRecipeHandler.class)// don't want subclasses
            // getting a hold of this
            loadCraftingRecipes("nuclearsmelting");
        else super.loadUsageRecipes(inputId, ingredients);
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        @SuppressWarnings("unchecked")
        Map<ItemStack, ItemStack> recipes = (Map<ItemStack, ItemStack>) FurnaceRecipes.smelting()
            .getSmeltingList();
        for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getKey(), ingredient)) {
                NuclearSmeltingPair arecipe = new NuclearSmeltingPair(recipe.getKey(), recipe.getValue());
                arecipe.setIngredientPermutation(Collections.singletonList(arecipe.ingred), ingredient);
                arecipes.add(arecipe);
            }
    }

    public String getGuiTexture() {
        return "nc:textures/gui/nuclearFurnaceNEI.png";
    }

    public void drawExtras(int recipe) {
        drawProgressBar(51, 25, 176, 0, 14, 14, 192, 7);
        drawProgressBar(74, 23, 176, 14, 24, 16, 3, 0);
    }

    public class NuclearSmeltingPair extends CachedRecipe {

        PositionedStack ingred;
        PositionedStack result;

        public NuclearSmeltingPair(ItemStack ingred, ItemStack result) {
            ingred.stackSize = 1;
            this.ingred = new PositionedStack(ingred, 51, 6);
            this.result = new PositionedStack(result, 111, 24);
        }

        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(cycleticks / 24, Collections.singletonList(ingred));
        }

        public PositionedStack getResult() {
            return result;
        }

        public PositionedStack getOtherStack() {
            return afuels.get((cycleticks / 24) % afuels.size()).stack;
        }
    }
}
