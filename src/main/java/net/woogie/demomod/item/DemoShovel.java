package net.woogie.demomod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoShovel extends ItemSpade {
	public DemoShovel() {
		super(DemoMod.demoToolMaterial);
		setUnlocalizedName(Config.MODID + ":" + Config.shovelName);
		setCreativeTab(CreativeTabs.tabTools);
		this.maxStackSize = Config.shovelMaxStackSize;
	}

	@Override
	public void onCreated(ItemStack itemStack, World worldIn, EntityPlayer player) {
		for (int i = 0; i < Config.shovelEnchantments.length; i++) {
			itemStack.addEnchantment(Config.shovelEnchantments[i], Config.shovelEnchantmentLevels[i]);
		}
	}
}
