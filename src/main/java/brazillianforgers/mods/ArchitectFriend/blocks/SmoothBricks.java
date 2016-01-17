package brazillianforgers.mods.ArchitectFriend.blocks;

import java.util.ArrayList;
import java.util.List;

import brazillianforgers.lib.RecipeHelper.ICraftable;
import brazillianforgers.lib.RecipeHelper.Recipe;
import brazillianforgers.lib.RecipeHelper.RecipeValue;
import brazillianforgers.mods.ArchitectFriend.ArchitectFriend;
import brazillianforgers.mods.ArchitectFriend.Lib;
import brazillianforgers.mods.ArchitectFriend.manager.BlockManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class SmoothBricks extends Block implements ICraftable {
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[16];
	
	public SmoothBricks() {
		super(Material.rock);
		this.setBlockName("Bricks");
		this.setBlockTextureName(Lib.TEXTURE_PATH + "Bricks");
		this.setCreativeTab(ArchitectFriend.tabArchitect);
		this.setHardness(2.0F);
		this.setResistance(6.0F);
		this.setStepSound(soundTypeStone);
		this.setLightLevel(1.0F);
	}

	/* Metadata Override */
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 16; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}

	/* Texture Override */
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.icons[meta];
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 16; i++) {
			this.icons[i] = reg.registerIcon(this.textureName + "_" + i);
		}
	}
	
	/* ICraftable Method */
	@Override
	public Recipe[] getRecipes() {
		List<Recipe> r = new ArrayList<Recipe>();
		for(int i = 0; i < 16; i++)
		{
			r.add(new Recipe(
				new ItemStack(this,4,i), new String[]{"BB","BB"},
				new RecipeValue('B', new ItemStack(BlockManager.smoothBlock,1,i))
			));
		}
		return r.toArray(new Recipe[r.size()]);
	}
}
