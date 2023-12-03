package nc.crafting.nei;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import nc.NuclearCraft;
import nc.block.NCBlocks;
import nc.item.NCItems;

public class InfoRecipes {

    private static final InfoRecipes infoBase = new InfoRecipes();
    @SuppressWarnings("rawtypes")
    private Map info = new HashMap();
    @SuppressWarnings("rawtypes")
    private Map infoTypes = new HashMap();

    public static InfoRecipes info() {
        return infoBase;
    }

    /*
     * private String text(String s) {
     * return StatCollector.translateToLocal(s);
     * }
     */

    private InfoRecipes() {
        fissionFuelInfo(11, NuclearCraft.baseRFLEU, NuclearCraft.baseFuelLEU, NuclearCraft.baseHeatLEU);
        fissionFuelInfo(17, NuclearCraft.baseRFLEU, NuclearCraft.baseFuelLEU, NuclearCraft.baseHeatLEU);
        fissionFuelInfo(12, NuclearCraft.baseRFHEU, NuclearCraft.baseFuelHEU, NuclearCraft.baseHeatHEU);
        fissionFuelInfo(18, NuclearCraft.baseRFHEU, NuclearCraft.baseFuelHEU, NuclearCraft.baseHeatHEU);
        fissionFuelInfo(13, NuclearCraft.baseRFLEP, NuclearCraft.baseFuelLEP, NuclearCraft.baseHeatLEP);
        fissionFuelInfo(19, NuclearCraft.baseRFLEP, NuclearCraft.baseFuelLEP, NuclearCraft.baseHeatLEP);
        fissionFuelInfo(14, NuclearCraft.baseRFHEP, NuclearCraft.baseFuelHEP, NuclearCraft.baseHeatHEP);
        fissionFuelInfo(20, NuclearCraft.baseRFHEP, NuclearCraft.baseFuelHEP, NuclearCraft.baseHeatHEP);
        fissionFuelInfo(15, NuclearCraft.baseRFMOX, NuclearCraft.baseFuelMOX, NuclearCraft.baseHeatMOX);
        fissionFuelInfo(21, NuclearCraft.baseRFMOX, NuclearCraft.baseFuelMOX, NuclearCraft.baseHeatMOX);
        fissionFuelInfo(16, NuclearCraft.baseRFTBU, NuclearCraft.baseFuelTBU, NuclearCraft.baseHeatTBU);

        fissionFuelInfo(59, NuclearCraft.baseRFLEUOx, NuclearCraft.baseFuelLEUOx, NuclearCraft.baseHeatLEUOx);
        fissionFuelInfo(63, NuclearCraft.baseRFLEUOx, NuclearCraft.baseFuelLEUOx, NuclearCraft.baseHeatLEUOx);
        fissionFuelInfo(60, NuclearCraft.baseRFHEUOx, NuclearCraft.baseFuelHEUOx, NuclearCraft.baseHeatHEUOx);
        fissionFuelInfo(64, NuclearCraft.baseRFHEUOx, NuclearCraft.baseFuelHEUOx, NuclearCraft.baseHeatHEUOx);
        fissionFuelInfo(61, NuclearCraft.baseRFLEPOx, NuclearCraft.baseFuelLEPOx, NuclearCraft.baseHeatLEPOx);
        fissionFuelInfo(65, NuclearCraft.baseRFLEPOx, NuclearCraft.baseFuelLEPOx, NuclearCraft.baseHeatLEPOx);
        fissionFuelInfo(62, NuclearCraft.baseRFHEPOx, NuclearCraft.baseFuelHEPOx, NuclearCraft.baseHeatHEPOx);
        fissionFuelInfo(66, NuclearCraft.baseRFHEPOx, NuclearCraft.baseFuelHEPOx, NuclearCraft.baseHeatHEPOx);

        addRecipe(new ItemStack(NCBlocks.graphiteBlock), "-Generates additional power-and heat in Fission Reactors");

        addRecipe(new ItemStack(NCBlocks.speedBlock), "-Causes nuclear fuels to-deplete faster in Fission-Reactors");

        coolerInfo(
            NCBlocks.coolerBlock,
            NuclearCraft.standardCool,
            "Doubly effective when adjacent-to another Standard Reactor-Cooler");
        coolerInfo(
            NCBlocks.waterCoolerBlock,
            NuclearCraft.waterCool,
            "Doubly effective when adjacent-to Reactor Casing");
        coolerInfo(
            NCBlocks.cryotheumCoolerBlock,
            NuclearCraft.cryotheumCool,
            "Doubly effective when not-adjacent to any other-Cryotheum Reactor Coolers");
        coolerInfo(
            NCBlocks.redstoneCoolerBlock,
            NuclearCraft.redstoneCool,
            "Doubly effective when adjacent-to a Fuel Cell Compartment");
        coolerInfo(
            NCBlocks.enderiumCoolerBlock,
            NuclearCraft.enderiumCool,
            "Doubly effective when adjacent-to Graphite");
        coolerInfo(
            NCBlocks.glowstoneCoolerBlock,
            NuclearCraft.glowstoneCool,
            "Quadrupally effective when-adjacent to Graphite on-all six sides");
        coolerInfo(
            NCBlocks.heliumCoolerBlock,
            NuclearCraft.heliumCool,
            "Not affected by its-position in the structure");
        coolerInfo(
            NCBlocks.coolantCoolerBlock,
            NuclearCraft.coolantCool,
            "Doubly effective when adjacent-to a Water Reactor Cooler");

        addRecipe(new ItemStack(NCItems.fuel, 1, 35), "-Used to oxidise and-improve fission fuels");
        addRecipe(new ItemStack(NCItems.fuel, 1, 36), "-Fusion fuel--Best combined with Boron11-or Lithium7");
        addRecipe(
            new ItemStack(NCItems.fuel, 1, 37),
            "-Fusion fuel--Best combined with Deuterium,-Tritium, Helium3 or Lithium6");
        addRecipe(new ItemStack(NCItems.fuel, 1, 38), "-Fusion fuel--Best combined with Deuterium-or Tritium");
        addRecipe(new ItemStack(NCItems.fuel, 1, 39), "-Fusion fuel--Best combined with Helium3-or Lithium6");
        addRecipe(new ItemStack(NCItems.fuel, 1, 41), "-Fusion fuel--Best combined with Deuterium-or Helium3");
        addRecipe(new ItemStack(NCItems.fuel, 1, 42), "-Fusion fuel--Best combined with Hydrogen");
        addRecipe(new ItemStack(NCItems.fuel, 1, 44), "-Fusion fuel--Best combined with Hydrogen");
    }

    public void fissionFuelInfo(int meta, int power, int time, int heat) {
        addRecipe(
            new ItemStack(NCItems.fuel, 1, meta),
            "Base Power = " + power * NuclearCraft.fissionRF / 100
                + " RF/t-Base Lifetime = "
                + (10000000 / (time * 20)) * NuclearCraft.fissionEfficiency
                + " s-Base Heat = "
                + heat
                + " H/t-For a n*m*k Reactor with-c cells and efficiency e:-Multiply Base Power by:-c*(e/100)-Multiply Base Lifetime by:-1/c-Heat produced is determined-by the positions of cells");
    }

    public void coolerInfo(Block cooler, int heat, String extra) {
        addRecipe(
            new ItemStack(cooler),
            "-Removes heat from-Fission Reactors--Each block removes " + heat + " H/t--" + extra);
    }

    @SuppressWarnings("unchecked")
    public void addRecipe(ItemStack input, String info) {
        this.info.put(input, info);
    }

    @SuppressWarnings("unchecked")
    public void addRecipe(String type, ItemStack input, String info) {
        this.info.put(input, info);
        this.infoTypes.put(input, type);
    }

    @SuppressWarnings("rawtypes")
    public String getInfo(ItemStack stack) {
        Iterator iterator = this.info.entrySet()
            .iterator();
        Map.Entry entry;
        do {
            if (!iterator.hasNext()) {
                return null;
            }
            entry = (Map.Entry) iterator.next();
        } while (!func_151397_a(stack, (ItemStack) entry.getKey()));
        return (String) entry.getValue();
    }

    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_) {
        return (p_151397_2_.getItem() == p_151397_1_.getItem())
            && ((p_151397_2_.getItemDamage() == 32767) || (p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage()));
    }

    @SuppressWarnings("rawtypes")
    public Map getInfoList() {
        return this.info;
    }

    @SuppressWarnings("rawtypes")
    public Map getInfoType() {
        return this.infoTypes;
    }
}
