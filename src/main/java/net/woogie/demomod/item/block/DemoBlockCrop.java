package net.woogie.demomod.item.block;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoBlockCrop extends BlockCrops {

	protected boolean isFullyGrown = false;

	public DemoBlockCrop() {
		super();
		this.setUnlocalizedName(Config.MODID + ":" + Config.blockCropName);
	}

	@Override
	protected Item getSeed() {
		return DemoMod.demoSeed;
	}

	@Override
	protected Item getCrop() {
		return DemoMod.demoFood;
	}
}
