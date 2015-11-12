package net.woogie.demomod.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		ItemStack itemStack = new ItemStack(itemIn, 1, 0);
		for (int i = 0; i < Config.shovelEnchantments.length; i++) {
			itemStack.addEnchantment(Config.shovelEnchantments[i], Config.shovelEnchantmentLevels[i]);
		}
		subItems.add(itemStack);
	}
}
