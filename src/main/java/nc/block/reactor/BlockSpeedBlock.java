package nc.block.reactor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpeedBlock extends Block {

    public BlockSpeedBlock() {
        super(Material.iron);
    }

    @SideOnly(Side.CLIENT)
    protected IIcon blockIcon;

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister icon) {
        blockIcon = icon.registerIcon(
            "nc:reactor/" + this.getUnlocalizedName()
                .substring(5));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int int1, int int2) {
        return blockIcon;
    }

    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }
}
