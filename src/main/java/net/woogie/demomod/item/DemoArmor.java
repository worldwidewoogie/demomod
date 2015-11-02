package net.woogie.demomod.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoArmor extends ItemArmor {

	public DemoArmor(String typeString) {
		super(DemoMod.demoArmorMaterial, 0, typeString.equals(Config.helmetName) ? 0
				: typeString.equals(Config.chestplateName) ? 1
						: typeString.equals(Config.leggingsName) ? 2 : typeString.equals(Config.bootsName) ? 3 : 0);

		this.setUnlocalizedName(Config.MODID + ":" + typeString);

		if (typeString.equals(Config.helmetName)) {
			this.maxStackSize = Config.helmetMaxStackSize;
		} else {
			if (typeString.equals(Config.chestplateName)) {
				this.maxStackSize = Config.chestplateMaxStackSize;
			} else {
				if (typeString.equals(Config.leggingsName)) {
					this.maxStackSize = Config.leggingsMaxStackSize;
				} else {
					if (typeString.equals(Config.bootsName)) {
						this.maxStackSize = Config.bootsMaxStackSize;
					}
				}
			}
		}
	}

	@Override
	public void onCreated(ItemStack itemStack, World worldIn, EntityPlayer player) {

		if (itemStack.getDisplayName().equals(Config.helmetName)) {
			for (int i = 0; i < Config.helmetEnchantments.length; i++) {
				itemStack.addEnchantment(Config.helmetEnchantments[i], Config.helmetEnchantmentLevels[i]);
			}
		} else {
			if (itemStack.getDisplayName().equals(Config.chestplateName)) {
				for (int i = 0; i < Config.chestplateEnchantments.length; i++) {
					itemStack.addEnchantment(Config.chestplateEnchantments[i], Config.chestplateEnchantmentLevels[i]);
				}
			} else {
				if (itemStack.getDisplayName().equals(Config.leggingsName)) {
					for (int i = 0; i < Config.leggingsEnchantments.length; i++) {
						itemStack.addEnchantment(Config.leggingsEnchantments[i], Config.leggingsEnchantmentLevels[i]);
					}
				} else {
					if (itemStack.getDisplayName().equals(Config.bootsName)) {
						for (int i = 0; i < Config.bootsEnchantments.length; i++) {
							itemStack.addEnchantment(Config.bootsEnchantments[i], Config.bootsEnchantmentLevels[i]);
						}
					}
				}
			}
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return Config.MODID + ":textures/armor/" + Config.armorName + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {

		if (itemStack.getItem().equals(DemoMod.demoHelmet)) {
			for (int i = 0; i < Config.helmetEffects.length; i++) {
				if (!world.isRemote && Config.helmetEffects[i] != null && Config.helmetEffects[i].getPotionID() > 0)
					player.addPotionEffect(new PotionEffect(Config.helmetEffects[i].getPotionID(),
							Config.helmetEffects[i].getDuration(), Config.helmetEffects[i].getAmplifier(),
							Config.helmetEffects[i].getIsAmbient(), Config.helmetEffects[i].getIsShowParticles()));
			}
		}

		if (itemStack.getItem().equals(DemoMod.demoChestplate)) {
			for (int i = 0; i < Config.chestplateEffects.length; i++) {
				if (!world.isRemote && Config.chestplateEffects[i] != null
						&& Config.chestplateEffects[i].getPotionID() > 0)
					player.addPotionEffect(new PotionEffect(Config.chestplateEffects[i].getPotionID(),
							Config.chestplateEffects[i].getDuration(), Config.chestplateEffects[i].getAmplifier(),
							Config.chestplateEffects[i].getIsAmbient(),
							Config.chestplateEffects[i].getIsShowParticles()));
			}
		}

		if (itemStack.getItem().equals(DemoMod.demoLeggings)) {
			for (int i = 0; i < Config.leggingsEffects.length; i++) {
				if (!world.isRemote && Config.leggingsEffects[i] != null && Config.leggingsEffects[i].getPotionID() > 0)
					player.addPotionEffect(new PotionEffect(Config.leggingsEffects[i].getPotionID(),
							Config.leggingsEffects[i].getDuration(), Config.leggingsEffects[i].getAmplifier(),
							Config.leggingsEffects[i].getIsAmbient(), Config.leggingsEffects[i].getIsShowParticles()));
			}
		}

		if (itemStack.getItem().equals(DemoMod.demoBoots)) {
			for (int i = 0; i < Config.bootsEffects.length; i++) {
				if (!world.isRemote && Config.bootsEffects[i] != null && Config.bootsEffects[i].getPotionID() > 0)
					player.addPotionEffect(new PotionEffect(Config.bootsEffects[i].getPotionID(),
							Config.bootsEffects[i].getDuration(), Config.bootsEffects[i].getAmplifier(),
							Config.bootsEffects[i].getIsAmbient(), Config.bootsEffects[i].getIsShowParticles()));
			}
		}
	}
}
