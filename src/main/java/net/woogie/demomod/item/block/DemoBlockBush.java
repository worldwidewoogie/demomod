package net.woogie.demomod.item.block;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoBlockBush extends BlockBush {
	public DemoBlockBush(Material materialIn) {
		super(materialIn);
		this.setUnlocalizedName(Config.MODID + ":" + Config.blockBushName);
	}

	public DemoBlockBush() {
		this(Material.plants);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if (rand.nextFloat() < 0.1F) {
			return DemoMod.demoSeed;			
		} else {
			return null;
		}
	}
}
