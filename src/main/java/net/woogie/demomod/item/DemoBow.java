
package net.woogie.demomod.item;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.woogie.demomod.Config;

public class DemoBow extends ItemBow {
	public DemoBow() {
		super();
		setUnlocalizedName(Config.MODID + ":" + Config.bowName);
		setCreativeTab(CreativeTabs.tabCombat);
		this.maxStackSize = Config.bowMaxStackSize;
	}

	@Override
	public void onCreated(ItemStack itemStack, World worldIn, EntityPlayer player) {
		for (int i = 0; i < Config.bowEnchantments.length; i++) {
			itemStack.addEnchantment(Config.bowEnchantments[i], Config.bowEnchantmentLevels[i]);
		}
	}

	@Override
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {

		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(Config.MODID + ":" + Config.bowName,
				"inventory");

		useRemaining = 72000 - useRemaining;

		if (player.getItemInUse() != null) {
			if (stack.getItem() == this && player.getItemInUse() != null) {
				if (useRemaining >= 18) {
					modelResourceLocation = new ModelResourceLocation(Config.MODID + ":" + Config.bowName + "_2",
							"inventory");
				} else if (useRemaining > 13) {
					modelResourceLocation = new ModelResourceLocation(Config.MODID + ":" + Config.bowName + "_1",
							"inventory");
				} else if (useRemaining > 0) {
					modelResourceLocation = new ModelResourceLocation(Config.MODID + ":" + Config.bowName + "_0",
							"inventory");
				}
			}
		}

		return modelResourceLocation;
	}
}
