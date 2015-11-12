package net.woogie.demomod.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoGiantSword extends ItemSword {

	public DemoGiantSword() {
		super(DemoMod.demoToolMaterial);
		setUnlocalizedName(Config.MODID + ":" + Config.giantSwordName);
		setCreativeTab(CreativeTabs.tabCombat);
		this.maxStackSize = Config.giantSwordMaxStackSize;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {

		if (!entity.worldObj.isRemote) {

			if (Config.giantSwordSummonsLightning) {
				entity.worldObj.addWeatherEffect(new EntityLightningBolt(entity.worldObj, entity.posX + 0.5,
						entity.posY + 1, entity.posZ + 0.5));
			}

			if (entity instanceof EntityPlayer) {
				EntityPlayer enemyPlayer = (EntityPlayer) entity;

				for (int i = 0; i < Config.giantSwordEffects.length; i++) {
					if (Config.giantSwordEffects[i] != null && Config.giantSwordEffects[i].getPotionID() > 0)
						enemyPlayer.addPotionEffect(new PotionEffect(Config.giantSwordEffects[i].getPotionID(),
								Config.giantSwordEffects[i].getDuration(), Config.giantSwordEffects[i].getAmplifier(),
								Config.giantSwordEffects[i].getIsAmbient(),
								Config.giantSwordEffects[i].getIsShowParticles()));
				}
			}
		}
		return false;
	}

	@Override
	public void onCreated(ItemStack itemStack, World worldIn, EntityPlayer player) {
		for (int i = 0; i < Config.giantSwordEnchantments.length; i++) {
			itemStack.addEnchantment(Config.giantSwordEnchantments[i], Config.giantSwordEnchantmentLevels[i]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		ItemStack itemStack = new ItemStack(itemIn, 1, 0);
		for (int i = 0; i < Config.giantSwordEnchantments.length; i++) {
			itemStack.addEnchantment(Config.giantSwordEnchantments[i], Config.giantSwordEnchantmentLevels[i]);
		}
		subItems.add(itemStack);
	}
}
