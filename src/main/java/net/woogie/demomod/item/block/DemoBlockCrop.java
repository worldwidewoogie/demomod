package net.woogie.demomod.item.block;

import net.minecraft.block.BlockCrops;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoBlockCrop extends BlockCrops {

	protected boolean isFullyGrown = false;

	public DemoBlockCrop() {
		super();
		this.setUnlocalizedName(Config.MODID + ":" + Config.blockName);
	}

	protected Item getSeed() {
		return DemoMod.demoSeed;
	}

	protected Item getCrop() {
		return DemoMod.demoFood;
	}
}
