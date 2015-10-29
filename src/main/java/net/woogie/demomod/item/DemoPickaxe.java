package net.woogie.demomod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoPickaxe extends ItemPickaxe {

	public DemoPickaxe() {
		super(DemoMod.demoToolMaterial);
		setUnlocalizedName(Config.MODID + ":" + Config.pickaxeName);
		setCreativeTab(CreativeTabs.tabTools);
		// TODO: set damage, enchantments, etc
		this.maxStackSize = Config.pickaxeMaxStackSize;
	}
}
