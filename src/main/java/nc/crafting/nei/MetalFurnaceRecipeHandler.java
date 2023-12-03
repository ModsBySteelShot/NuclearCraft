package nc.crafting.nei;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.FurnaceRecipeHandler.FuelPair;
import codechicken.nei.recipe.TemplateRecipeHandler;
import nc.gui.machine.GuiFurnace;

public class MetalFurnaceRecipeHandler extends TemplateRecipeHandler {

    public static List<FuelPair> afuels;

    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(50, 23, 18, 18), "metalfuel"));
        transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 18), "metalsmelting"));
    }

    public Class<? extends GuiContainer> getGuiClass() {
        return GuiFurnace.class;
    }

    public String getRecipeName() {
        return "Metal Furnace";
    }

    public TemplateRecipeHandler newInstance() {
        if (afuels == null) afuels = FuelHandlerHelper.includedFuels();

        return super.newInstance();
    }

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("metalsmelting") && getClass() == MetalFurnaceRecipeHandler.class) {// don't want subclasses
            // getting a hold of
            // this
            @SuppressWarnings("unchecked")
            Map<ItemStack, ItemStack> recipes = (Map<ItemStack, ItemStack>) FurnaceRecipes.smelting()
                .getSmeltingList();
            for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
                arecipes.add(new MetalSmeltingPair(recipe.getKey(), recipe.getValue()));
        } else super.loadCraftingRecipes(outputId, results);
    }

    public void loadCraftingRecipes(ItemStack result) {
        @SuppressWarnings("unchecked")
        Map<ItemStack, ItemStack> recipes = (Map<ItemStack, ItemStack>) FurnaceRecipes.smelting()
            .getSmeltingList();
        for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
            if (NEIServerUtils.areStacksSameType(recipe.getValue(), result))
                arecipes.add(new MetalSmeltingPair(recipe.getKey(), recipe.getValue()));
    }

    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (inputId.equals("metalfuel") && getClass() == MetalFurnaceRecipeHandler.class)// don't want subclasses
            // getting a hold of this
            loadCraftingRecipes("metalsmelting");
        else super.loadUsageRecipes(inputId, ingredients);
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        @SuppressWarnings("unchecked")
        Map<ItemStack, ItemStack> recipes = (Map<ItemStack, ItemStack>) FurnaceRecipes.smelting()
            .getSmeltingList();
        for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getKey(), ingredient)) {
                MetalSmeltingPair arecipe = new MetalSmeltingPair(recipe.getKey(), recipe.getValue());
                arecipe.setIngredientPermutation(Collections.singletonList(arecipe.ingred), ingredient);
                arecipes.add(arecipe);
            }
    }

    public String getGuiTexture() {
        return "nc:textures/gui/furnace.png";
    }

    public void drawExtras(int recipe) {
        drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
        drawProgressBar(74, 23, 176, 14, 24, 16, 40, 0);
    }

    public class MetalSmeltingPair extends CachedRecipe {

        PositionedStack ingred;
        PositionedStack result;

        public MetalSmeltingPair(ItemStack ingred, ItemStack result) {
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
