package net.woogie.demomod.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
		ItemStack itemStack = new ItemStack(itemIn, 1, 0);
		for (int i = 0; i < Config.hoeEnchantments.length; i++) {
			itemStack.addEnchantment(Config.hoeEnchantments[i], Config.hoeEnchantmentLevels[i]);
		}
        subItems.add(itemStack);
    }
}
