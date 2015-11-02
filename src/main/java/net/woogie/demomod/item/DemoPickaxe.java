package net.woogie.demomod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoPickaxe extends ItemPickaxe {

	public DemoPickaxe() {
		super(DemoMod.demoToolMaterial);
		setUnlocalizedName(Config.MODID + ":" + Config.pickaxeName);
		setCreativeTab(CreativeTabs.tabTools);
		this.maxStackSize = Config.pickaxeMaxStackSize;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {

		if (!entity.worldObj.isRemote) {

			if (Config.pickaxeSummonsLightning) {
				entity.worldObj.addWeatherEffect(new EntityLightningBolt(entity.worldObj, (double) entity.posX + 0.5,
						(double) entity.posY + 1, (double) entity.posZ + 0.5));
			}

			if (entity instanceof EntityPlayer) {
				EntityPlayer enemyPlayer = (EntityPlayer) entity;

				for (int i = 0; i < Config.pickaxeEffects.length; i++) {
					if (Config.pickaxeEffects[i] != null && Config.pickaxeEffects[i].getPotionID() > 0)
						enemyPlayer.addPotionEffect(new PotionEffect(Config.pickaxeEffects[i].getPotionID(),
								Config.pickaxeEffects[i].getDuration(), Config.pickaxeEffects[i].getAmplifier(),
								Config.pickaxeEffects[i].getIsAmbient(),
								Config.pickaxeEffects[i].getIsShowParticles()));
				}
			}
		}
		return false;
	}

	@Override
	public void onCreated(ItemStack itemStack, World worldIn, EntityPlayer player) {
		for (int i = 0; i < Config.pickaxeEnchantments.length; i++) {
			itemStack.addEnchantment(Config.pickaxeEnchantments[i], Config.pickaxeEnchantmentLevels[i]);
		}
	}
}
