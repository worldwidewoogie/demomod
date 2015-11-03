package net.woogie.demomod.item.block;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.woogie.demomod.Config;

public class DemoBlockBush extends BlockBush {
	public DemoBlockBush(Material materialIn) {
		super(materialIn);
		this.setUnlocalizedName(Config.MODID + ":" + Config.blockBushName);
	}

	public DemoBlockBush() {
		this(Material.plants);
	}

}
