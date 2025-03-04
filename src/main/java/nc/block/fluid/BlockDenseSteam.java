package nc.block.fluid;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import nc.NuclearCraft;

public class BlockDenseSteam extends BlockFluidClassic {

    @SideOnly(Side.CLIENT)
    public static IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon flowingIcon;

    public static DamageSource damageSource;

    public static int tickCount;

    public BlockDenseSteam(Fluid f, Material m, DamageSource damage) {
        super(f, m);
        damageSource = damage;
        setQuantaPerBlock(9);
        setCreativeTab(NuclearCraft.tabNC);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return (side == 0 || side == 1) ? stillIcon : flowingIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        stillIcon = register.registerIcon("nc:fluid/denseSteamStill");
        flowingIcon = register.registerIcon("nc:fluid/denseSteamFlowing");
    }

    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        if (world.getBlock(x, y, z)
            .getMaterial()
            .isLiquid()) return false;
        return super.canDisplace(world, x, y, z);
    }

    public boolean displaceIfPossible(World world, int x, int y, int z) {
        if (world.getBlock(x, y, z)
            .getMaterial()
            .isLiquid()) return false;
        return super.displaceIfPossible(world, x, y, z);
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (world.isRemote) {
            return;
        }
        if (entity instanceof EntityBlaze) {
            return;
        } else {
            boolean bool = entity.velocityChanged;
            entity.attackEntityFrom(damageSource, 4.0F);
            entity.velocityChanged = bool;
        }
    }

    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
        if (tickCount >= 15) {
            if (isSourceBlock(world, x, y, z)) world.setBlockToAir(x, y, z);
        } else tickCount++;
    }
}
