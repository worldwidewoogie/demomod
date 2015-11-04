package net.woogie.demomod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoHoe extends ItemHoe {
	public DemoHoe() {
		super(DemoMod.demoToolMaterial);
		setUnlocalizedName(Config.MODID + ":" + Config.hoeName);
		setCreativeTab(CreativeTabs.tabTools);
		this.maxStackSize = Config.hoeMaxStackSize;
	}

	@Override
	public void onCreated(ItemStack itemStack, World worldIn, EntityPlayer player) {
		for (int i = 0; i < Config.hoeEnchantments.length; i++) {
			itemStack.addEnchantment(Config.hoeEnchantments[i], Config.hoeEnchantmentLevels[i]);
		}
	}
}
