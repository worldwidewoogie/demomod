package net.woogie.demomod.item.block;

import java.util.ArrayList;
import java.util.Random;

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

public class DemoBlockOre extends Block {

	public DemoBlockOre() {
		super(Config.blockParentMaterial);
		this.setHarvestLevel(Config.blockMineTool, Config.blockMineLevel);
		this.setHardness(Config.blockHardness);
		this.setResistance(Config.blockResistance);
		this.setUnlocalizedName(Config.MODID + ":" + Config.blockOreName);
		this.setCreativeTab(CreativeTabs.tabBlock);
		if (Config.blockLightLevel != 0.0F) {
			this.setLightLevel(Config.blockLightLevel);
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();

		drops.add(new ItemStack(DemoMod.demoBlock,
				RANDOM.nextInt(Config.blockDropMax - Config.blockDropMin) + Config.blockDropMin));

		return drops;
	}
}
