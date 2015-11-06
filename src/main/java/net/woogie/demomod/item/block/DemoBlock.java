package net.woogie.demomod.item.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.woogie.demomod.Config;

public class DemoBlock extends Block {

	public DemoBlock() {
		super(Config.blockParentMaterial);
		this.setHarvestLevel(Config.blockMineTool, Config.blockMineLevel);
		this.setHardness(Config.blockHardness);
		this.setResistance(Config.blockResistance);
		this.setUnlocalizedName(Config.MODID + ":" + Config.blockName);
		this.setCreativeTab(CreativeTabs.tabBlock);
		if (Config.blockLightLevel != 0.0F) {
			this.setLightLevel(Config.blockLightLevel);
		}
	}
}
