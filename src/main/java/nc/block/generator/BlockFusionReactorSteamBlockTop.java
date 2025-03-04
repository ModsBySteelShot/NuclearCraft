package nc.block.generator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import nc.NuclearCraft;
import nc.block.NCBlocks;
import nc.tile.generator.TileFusionReactorSteamBlockTop;

public class BlockFusionReactorSteamBlockTop extends BlockFusionReactorSteam {

    public int below = 1;

    public BlockFusionReactorSteamBlockTop() {
        super(Material.iron);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    protected IIcon blockIcon;

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister i) {
        blockIcon = i.registerIcon("nc:generator/fusionReactorSteam/" + "dummy");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return blockIcon;
    }

    public int getRenderType() {
        return -1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World w, int i) {
        return new TileFusionReactorSteamBlockTop();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        if (!player.isSneaking()) {
            if ((player != null) && (!world.isRemote)) {
                if (world.getBlock(x, y - 2, z) == NCBlocks.fusionReactorSteam)
                    FMLNetworkHandler.openGui(player, NuclearCraft.instance, 22, world, x, y - 2, z);
                else if (world.getBlock(x + 1, y - 2, z) == NCBlocks.fusionReactorSteam)
                    FMLNetworkHandler.openGui(player, NuclearCraft.instance, 22, world, x + 1, y - 2, z);
                else if (world.getBlock(x + 1, y - 2, z + 1) == NCBlocks.fusionReactorSteam)
                    FMLNetworkHandler.openGui(player, NuclearCraft.instance, 22, world, x + 1, y - 2, z + 1);
                else if (world.getBlock(x, y - 2, z + 1) == NCBlocks.fusionReactorSteam)
                    FMLNetworkHandler.openGui(player, NuclearCraft.instance, 22, world, x, y - 2, z + 1);
                else if (world.getBlock(x - 1, y - 2, z + 1) == NCBlocks.fusionReactorSteam)
                    FMLNetworkHandler.openGui(player, NuclearCraft.instance, 22, world, x - 1, y - 2, z + 1);
                else if (world.getBlock(x - 1, y - 2, z) == NCBlocks.fusionReactorSteam)
                    FMLNetworkHandler.openGui(player, NuclearCraft.instance, 22, world, x - 1, y - 2, z);
                else if (world.getBlock(x - 1, y - 2, z - 1) == NCBlocks.fusionReactorSteam)
                    FMLNetworkHandler.openGui(player, NuclearCraft.instance, 22, world, x - 1, y - 2, z - 1);
                else if (world.getBlock(x, y - 2, z - 1) == NCBlocks.fusionReactorSteam)
                    FMLNetworkHandler.openGui(player, NuclearCraft.instance, 22, world, x, y - 2, z - 1);
                else if (world.getBlock(x + 1, y - 2, z - 1) == NCBlocks.fusionReactorSteam)
                    FMLNetworkHandler.openGui(player, NuclearCraft.instance, 22, world, x + 1, y - 2, z - 1);
            }
        }
        return true;
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return world.getBlock(x, y, z)
            .isReplaceable(world, x, y, z);
    }

    public void onBlockAdded(World world, int x, int y, int z) {}

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase,
        ItemStack itemstack) {}

    public void breakBlock(World world, int x, int y, int z, Block oldblock, int oldMetadata) {
        world.setBlockToAir(x, y, z);
        if (world.getBlock(x, y - 2, z) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x, y - 2, z);
            bi.dropBlockAsItem(world, x, y - 2, z, world.getBlockMetadata(x, y - 2, z), 0);
            world.setBlockToAir(x, y - 2, z);
        } else if (world.getBlock(x + 1, y - 2, z) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x + 1, y - 2, z);
            bi.dropBlockAsItem(world, x + 1, y - 2, z, world.getBlockMetadata(x + 1, y - 2, z), 0);
            world.setBlockToAir(x + 1, y - 2, z);
        } else if (world.getBlock(x + 1, y - 2, z - 1) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x + 1, y - 2, z - 1);
            bi.dropBlockAsItem(world, x + 1, y - 2, z - 1, world.getBlockMetadata(x + 1, y - 2, z - 1), 0);
            world.setBlockToAir(x + 1, y - 2, z - 1);
        } else if (world.getBlock(x, y - 2, z - 1) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x, y - 2, z - 1);
            bi.dropBlockAsItem(world, x, y - 2, z - 1, world.getBlockMetadata(x, y - 2, z - 1), 0);
            world.setBlockToAir(x, y - 2, z - 1);
        } else if (world.getBlock(x - 1, y - 2, z - 1) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x - 1, y - 2, z - 1);
            bi.dropBlockAsItem(world, x - 1, y - 2, z - 1, world.getBlockMetadata(x - 1, y - 2, z - 1), 0);
            world.setBlockToAir(x - 1, y - 2, z - 1);
        } else if (world.getBlock(x - 1, y - 2, z) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x - 1, y - 2, z);
            bi.dropBlockAsItem(world, x - 1, y - 2, z, world.getBlockMetadata(x - 1, y - 2, z), 0);
            world.setBlockToAir(x - 1, y - 2, z);
        } else if (world.getBlock(x - 1, y - 2, z + 1) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x - 1, y - 2, z + 1);
            bi.dropBlockAsItem(world, x - 1, y - 2, z + 1, world.getBlockMetadata(x - 1, y - 2, z + 1), 0);
            world.setBlockToAir(x - 1, y - 2, z + 1);
        } else if (world.getBlock(x, y - 2, z + 1) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x, y - 2, z + 1);
            bi.dropBlockAsItem(world, x, y - 2, z + 1, world.getBlockMetadata(x, y - 2, z + 1), 0);
            world.setBlockToAir(x, y - 2, z + 1);
        } else if (world.getBlock(x + 1, y - 2, z + 1) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x + 1, y - 2, z + 1);
            bi.dropBlockAsItem(world, x + 1, y - 2, z + 1, world.getBlockMetadata(x + 1, y - 2, z + 1), 0);
            world.setBlockToAir(x + 1, y - 2, z + 1);
        }

    }

    public int quantityDropped(Random p_149745_1_) {
        return 0;
    }

    public Item getItem(World world, int x, int y, int z) {
        return null;
    }

    public static void updateBlockState(World worldObj, int xCoord, int yCoord, int zCoord) {}

    public int getComparatorInputOverride(World world, int x, int y, int z, int i) {
        if (world.getBlock(x, y - 2, z) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x, y - 2, z);
            return bi.getComparatorInputOverride(world, x, y - 2, z, i);
        } else if (world.getBlock(x + 1, y - 2, z) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x + 1, y - 2, z);
            return bi.getComparatorInputOverride(world, x + 1, y - 2, z, i);
        } else if (world.getBlock(x + 1, y - 2, z - 1) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x + 1, y - 2, z - 1);
            return bi.getComparatorInputOverride(world, x + 1, y - 2, z - 1, i);
        } else if (world.getBlock(x, y - 2, z - 1) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x, y - 2, z - 1);
            return bi.getComparatorInputOverride(world, x, y - 2, z - 1, i);
        } else if (world.getBlock(x - 1, y - 2, z - 1) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x - 1, y - 2, z - 1);
            return bi.getComparatorInputOverride(world, x - 1, y - 2, z - 1, i);
        } else if (world.getBlock(x - 1, y - 2, z) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x - 1, y - 2, z);
            return bi.getComparatorInputOverride(world, x - 1, y - 2, z, i);
        } else if (world.getBlock(x - 1, y - 2, z + 1) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x - 1, y - 2, z + 1);
            return bi.getComparatorInputOverride(world, x - 1, y - 2, z + 1, i);
        } else if (world.getBlock(x, y - 2, z + 1) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x, y - 2, z + 1);
            return bi.getComparatorInputOverride(world, x, y - 2, z + 1, i);
        } else if (world.getBlock(x + 1, y - 2, z + 1) == NCBlocks.fusionReactorSteam) {
            Block bi = world.getBlock(x + 1, y - 2, z + 1);
            return bi.getComparatorInputOverride(world, x + 1, y - 2, z + 1, i);
        }
        return 0;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return new ItemStack(NCBlocks.fusionReactorSteam);
    }
}
