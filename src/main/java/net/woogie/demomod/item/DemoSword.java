package net.woogie.demomod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoSword extends ItemSword {

	public DemoSword() {
		super(DemoMod.demoToolMaterial);
		setUnlocalizedName(Config.MODID + ":" + Config.swordName);
		setCreativeTab(CreativeTabs.tabTools);
		this.maxStackSize = Config.swordMaxStackSize;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {

		if (Config.swordStartsFire) {
			entity.setFire(Config.swordFireSeconds);
		}

		if (entity instanceof EntityPlayer && !entity.worldObj.isRemote) {
			EntityPlayer enemyPlayer = (EntityPlayer) entity;

			for (int i = 0; i < Config.swordEffects.length; i++) {
				if (Config.swordEffects[i] != null && Config.swordEffects[i].getPotionID() > 0)
					enemyPlayer.addPotionEffect(new PotionEffect(Config.swordEffects[i].getPotionID(),
							Config.swordEffects[i].getDuration(), Config.swordEffects[i].getAmplifier(),
							Config.swordEffects[i].getIsAmbient(), Config.swordEffects[i].getIsShowParticles()));
			}
		}
		return false;
	}
}
