package nc.crafting.nei;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.FurnaceRecipeHandler.FuelPair;
import codechicken.nei.recipe.TemplateRecipeHandler;
import nc.crafting.machine.CrusherRecipesOld;
import nc.gui.machine.GuiCrusher;

public class CrusherRecipeHandler extends TemplateRecipeHandler {

    public static List<FuelPair> afuels;

    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(50, 23, 18, 18), "crushfuel"));
        transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 18), "crushing"));
    }

    public Class<? extends GuiContainer> getGuiClass() {
        return GuiCrusher.class;
    }

    public String getRecipeName() {
        return "Crusher";
    }

    public TemplateRecipeHandler newInstance() {
        if (afuels == null) afuels = FuelHandlerHelper.includedFuels();

        return super.newInstance();
    }

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("crushing") && getClass() == CrusherRecipeHandler.class) {// don't want subclasses getting a
            // hold of this
            Map<ItemStack, ItemStack> recipes = CrusherRecipesOld.smelting()
                .getSmeltingList();
            for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
                arecipes.add(new CrushingPair(recipe.getKey(), recipe.getValue()));
        } else super.loadCraftingRecipes(outputId, results);
    }

    public void loadCraftingRecipes(ItemStack result) {
        Map<ItemStack, ItemStack> recipes = CrusherRecipesOld.smelting()
            .getSmeltingList();
        for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
            if (NEIServerUtils.areStacksSameType(recipe.getValue(), result))
                arecipes.add(new CrushingPair(recipe.getKey(), recipe.getValue()));
    }

    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (inputId.equals("crushfuel") && getClass() == CrusherRecipeHandler.class)// don't want subclasses getting a
            // hold of this
            loadCraftingRecipes("crushing");
        else super.loadUsageRecipes(inputId, ingredients);
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        Map<ItemStack, ItemStack> recipes = CrusherRecipesOld.smelting()
            .getSmeltingList();
        for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getKey(), ingredient)) {
                CrushingPair arecipe = new CrushingPair(recipe.getKey(), recipe.getValue());
                arecipe.setIngredientPermutation(Collections.singletonList(arecipe.ingred), ingredient);
                arecipes.add(arecipe);
            }
    }

    public String getGuiTexture() {
        return "nc:textures/gui/crusher.png";
    }

    public void drawExtras(int recipe) {
        drawProgressBar(51, 25, 176, 0, 14, 14, 24, 7);
        drawProgressBar(74, 23, 176, 14, 24, 16, 48, 0);
    }

    public class CrushingPair extends CachedRecipe {

        PositionedStack ingred;
        PositionedStack result;

        public CrushingPair(ItemStack ingred, ItemStack result) {
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
