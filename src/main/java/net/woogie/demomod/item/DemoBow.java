
package net.woogie.demomod.item;

import java.util.List;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		ItemStack itemStack = new ItemStack(itemIn, 1, 0);
		for (int i = 0; i < Config.bowEnchantments.length; i++) {
			itemStack.addEnchantment(Config.bowEnchantments[i], Config.bowEnchantmentLevels[i]);
		}
		subItems.add(itemStack);
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

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {

		int j = this.getMaxItemUseDuration(stack) - timeLeft;
		net.minecraftforge.event.entity.player.ArrowLooseEvent event = new net.minecraftforge.event.entity.player.ArrowLooseEvent(
				playerIn, stack, j);
		if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
			return;
		j = event.charge;

		boolean flag = playerIn.capabilities.isCreativeMode
				|| EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

		if (flag || playerIn.inventory.hasItem(Items.arrow)) {
			float f = (float) j / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if ((double) f < 0.1D) {
				return;
			}

			if (f > 1.0F) {
				f = 1.0F;
			}

			EntityArrow entityarrow = new EntityArrow(worldIn, playerIn, f * 2.0F);

			if (f == 1.0F) {
				entityarrow.setIsCritical(true);
			}

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);

			if (k > 0) {
				entityarrow.setDamage(entityarrow.getDamage() + (double) k * 0.5D + 0.5D);
			}

			int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

			if (l > 0) {
				entityarrow.setKnockbackStrength(l);
			}

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0) {
				entityarrow.setFire(100);
			}

			stack.damageItem(1, playerIn);
			worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F,
					1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if (flag) {
				entityarrow.canBePickedUp = 2;
			} else {
				playerIn.inventory.consumeInventoryItem(Items.arrow);
			}

			playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);

			if (!worldIn.isRemote) {
				worldIn.spawnEntityInWorld(entityarrow);
			}
		}
	}
}
