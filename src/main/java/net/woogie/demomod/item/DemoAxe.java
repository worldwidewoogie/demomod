
package net.woogie.demomod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoAxe extends ItemAxe {
	public DemoAxe() {
		super(DemoMod.demoToolMaterial);
		setUnlocalizedName(Config.MODID + ":" + Config.axeName);
		setCreativeTab(CreativeTabs.tabTools);
		this.maxStackSize = Config.axeMaxStackSize;
	}

	@Override
	public void onCreated(ItemStack itemStack, World worldIn, EntityPlayer player) {
		for (int i = 0; i < Config.axeEnchantments.length; i++) {
			itemStack.addEnchantment(Config.axeEnchantments[i], Config.axeEnchantmentLevels[i]);
		}
	}
}
