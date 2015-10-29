package net.woogie.demomod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.woogie.demomod.Config;

public class DemoFood extends ItemFood {

	public DemoFood() {
		super(Config.foodHealAmount, Config.foodSaturationModifier, Config.foodWolvesFavorite);
		setUnlocalizedName(Config.MODID + ":" + Config.foodName);
		setCreativeTab(CreativeTabs.tabFood);
		setAlwaysEdible();
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);

		for (int i = 0; i < Config.foodEffects.length; i++) {
			if (!world.isRemote && Config.foodEffects[i] != null && Config.foodEffects[i].getPotionID() > 0)
				player.addPotionEffect(new PotionEffect(Config.foodEffects[i].getPotionID(),
						Config.foodEffects[i].getDuration(), Config.foodEffects[i].getAmplifier(),
						Config.foodEffects[i].getIsAmbient(), Config.foodEffects[i].getIsShowParticles()));
		}
	}

}
