
package net.woogie.demomod.item;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.world.World;
import net.woogie.demomod.Config;

public class DemoBow extends ItemBow {
	public DemoBow() {
		super();
		setUnlocalizedName(Config.MODID + ":" + Config.bowName);
		setCreativeTab(CreativeTabs.tabTools);
		this.maxStackSize = Config.bowMaxStackSize;
	}

	@Override
	public void onCreated(ItemStack itemStack, World worldIn, EntityPlayer player) {
		for (int i = 0; i < Config.bowEnchantments.length; i++) {
			itemStack.addEnchantment(Config.bowEnchantments[i], Config.bowEnchantmentLevels[i]);
		}
	}

	private long getLastUseTime(ItemStack stack) {
		return stack.hasTagCompound() ? stack.getTagCompound().getLong("LastUse") : 0;
	}

	private void setLastUseTime(ItemStack stack, long time) {
		stack.setTagInfo("LastUse", new NBTTagLong(time));
	}

	@Override
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {

		long ticksSinceLastUse = this.getLastUseTime(stack);

		if (ticksSinceLastUse >= 60) {
			return new ModelResourceLocation(getUnlocalizedName() + "_pulling_2", "inventory");
		} else if (ticksSinceLastUse >= 40) {
			return new ModelResourceLocation(getUnlocalizedName() + "_pulling_1", "inventory");
		} else if (ticksSinceLastUse >= 20) {
			return new ModelResourceLocation(getUnlocalizedName() + "_pulling_0", "inventory");
		} else {
			return new ModelResourceLocation(getUnlocalizedName(), "inventory");
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		setLastUseTime(itemStackIn, worldIn.getTotalWorldTime());

		return super.onItemRightClick(itemStackIn, worldIn, playerIn);
	}
}
