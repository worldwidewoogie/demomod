package net.woogie.demomod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.woogie.demomod.Config;

public class DemoItem extends Item {

	public DemoItem() {
		this.setUnlocalizedName(Config.MODID + ":" + Config.itemName);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.maxStackSize = Config.itemMaxStackSize;
	}
}
