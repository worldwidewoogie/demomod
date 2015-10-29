package net.woogie.demomod.item.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoBlockMultiOre extends Block {

	public DemoBlockMultiOre() {
		super(Config.blockParentMaterial);
		this.setHarvestLevel(Config.blockMineTool, Config.blockMineLevel);
		this.setHardness(Config.blockHardness);
		this.setResistance(Config.blockResistance);
		this.setUnlocalizedName(Config.MODID + ":" + Config.blockMultiOreName);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		drops.add(new ItemStack(DemoMod.demoBlock,
				RANDOM.nextInt(Config.blockDropMax - Config.blockDropMin) + Config.blockDropMin));
		drops.add(new ItemStack(DemoMod.demoBlock,
				RANDOM.nextInt(Config.blockMultiDropMax - Config.blockMultiDropMin) + Config.blockMultiDropMin));
		if (RANDOM.nextFloat() < Config.blockMultiDropBonusChance)
			drops.add(new ItemStack(Config.blockMultiDropBonusItem));
		return drops;
	}
}
