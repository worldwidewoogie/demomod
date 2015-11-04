package net.woogie.demomod;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.woogie.demomod.entity.fourm.EntityFourm;

public class Config {

	public static final String MODID = "demomod";
	public static final String MODNAME = "Demo Mod";
	public static final String VERSION = "1.0";

	public static final String toolMaterialName = "demoToolMaterial";
	public static final int toolMaterialHarvestLevel = 3;
	public static final int toolMaterialDurability = 1000;
	public static final float toolMaterialMiningSpeed = 15.0F;
	public static final float toolMaterialDamageVsEnitities = 4.0F;
	public static final int toolMaterialEnchantability = 30;

	public static final String armorMaterialName = "demoArmorMaterial";
	public static final String armorMaterialTextureName = MODID + ":" + armorMaterialName;

	public static final int armorMaterialDurability = 1000;

	public static final int armorMaterialDamageReductionHelmet = 3;
	public static final int armorMaterialDamageReductionChestplate = 8;
	public static final int armorMaterialDamageReductionLeggings = 6;
	public static final int armorMaterialDamageReductionBoots = 3;

	public static final int[] armorMaterialDamageReduction = new int[] { //
			armorMaterialDamageReductionHelmet, //
			armorMaterialDamageReductionChestplate, //
			armorMaterialDamageReductionLeggings, //
			armorMaterialDamageReductionBoots };

	public static final int armorMaterialEnchantability = 30;

	public static final String itemName = "demoItem";
	public static final int itemMaxStackSize = 64;
	public static Object[] itemRecipe = null;
	public static final float itemExperience = 1.0F;

	public static final String blockName = "demoBlock";
	public static final Material blockParentMaterial = Material.rock;
	public static final float blockHardness = 10.0F;
	public static final float blockResistance = 15.0F;
	public static final String blockMineTool = Config.MODID + "." + Config.pickaxeName;
	public static final int blockMineLevel = 1;
	public static final int blockDropMax = 7;
	public static final int blockDropMin = 2;
	public static final Item blockMultiDropItem = Items.gold_nugget;
	public static final int blockMultiDropMax = 7;
	public static final int blockMultiDropMin = 2;
	public static final Item blockMultiDropBonusItem = Items.diamond;
	public static final float blockMultiDropBonusChance = 0.1F;

	public static final String blockOreName = "demoBlockOre";
	public static final int blockOreDropCount = 8;
	public static final int blockOreChancesToSpawn = 20;
	public static final int blockOreMinHeight = 0;
	public static final int blockOreMaxHeight = 64;

	public static final String blockMultiOreName = "demoBlockMultiOre";
	public static final int blockMultiOreChancesToSpawn = 10;
	public static final int blockMultiOreMinHeight = 0;
	public static final int blockMultiOreMaxHeight = 16;

	public static final String swordName = "demoSword";
	public static final int swordMaxStackSize = 1;
	public static final boolean swordSummonsLightning = true;
	public static final PotionEffect[] swordEffects = { //
			new PotionEffect(Potion.blindness.id, 1200, 1), //
			new PotionEffect(Potion.poison.jump.id, 600, 0), //
			new PotionEffect(Potion.weakness.id, 200, 1) };
	public static final Enchantment[] swordEnchantments = { Enchantment.sharpness, Enchantment.fireAspect };
	public static final int[] swordEnchantmentLevels = { 3, 3 };
	public static Object[] swordRecipe = null;

	public static final String pickaxeName = "demoPickaxe";
	public static final int pickaxeMaxStackSize = 1;
	public static final boolean pickaxeSummonsLightning = true;
	public static final PotionEffect[] pickaxeEffects = { //
			new PotionEffect(Potion.blindness.id, 1200, 1), //
			new PotionEffect(Potion.poison.jump.id, 600, 0), //
			new PotionEffect(Potion.weakness.id, 200, 1) };
	public static final Enchantment[] pickaxeEnchantments = { Enchantment.efficiency, Enchantment.fortune };
	public static final int[] pickaxeEnchantmentLevels = { 3, 3 };
	public static Object[] pickaxeRecipe = null;

	public static final String hoeName = "demoHoe";
	public static final int hoeMaxStackSize = 1;
	public static final Enchantment[] hoeEnchantments = { Enchantment.unbreaking };
	public static final int[] hoeEnchantmentLevels = { 3 };
	public static Object[] hoeRecipe = null;

	public static final String shovelName = "demoShovel";
	public static final int shovelMaxStackSize = 1;
	public static final Enchantment[] shovelEnchantments = { Enchantment.efficiency, Enchantment.silkTouch };
	public static final int[] shovelEnchantmentLevels = { 3, 3 };
	public static Object[] shovelRecipe = null;

	public static final String axeName = "demoAxe";
	public static final int axeMaxStackSize = 1;
	public static final Enchantment[] axeEnchantments = { Enchantment.efficiency, Enchantment.silkTouch };
	public static final int[] axeEnchantmentLevels = { 3, 3 };
	public static Object[] axeRecipe = null;

	public static final String bowName = "demoBow";
	public static final int bowMaxStackSize = 1;
	public static final Enchantment[] bowEnchantments = { Enchantment.flame, Enchantment.infinity };
	public static final int[] bowEnchantmentLevels = { 3, 3 };
	public static Object[] bowRecipe = null;

	public static final String armorName = "demoArmor";

	public static final String helmetName = "demoHelmet";
	public static final int helmetMaxStackSize = 1;
	public static final Enchantment[] helmetEnchantments = { Enchantment.thorns };
	public static final int[] helmetEnchantmentLevels = { 3 };
	public static final PotionEffect[] helmetEffects = { new PotionEffect(Potion.waterBreathing.id, 200, 0) };
	public static Object[] helmetRecipe = null;

	public static final String chestplateName = "demoChestplate";
	public static final int chestplateMaxStackSize = 1;
	public static final Enchantment[] chestplateEnchantments = { Enchantment.thorns };
	public static final int[] chestplateEnchantmentLevels = { 3 };
	public static final PotionEffect[] chestplateEffects = { new PotionEffect(Potion.regeneration.id, 200, 0) };
	public static Object[] chestplateRecipe = null;

	public static final String leggingsName = "demoLeggings";
	public static final int leggingsMaxStackSize = 1;
	public static final Enchantment[] leggingsEnchantments = { Enchantment.thorns };
	public static final int[] leggingsEnchantmentLevels = { 3 };
	public static final PotionEffect[] leggingsEffects = { new PotionEffect(Potion.moveSpeed.id, 1200, 0) };
	public static Object[] leggingsRecipe = null;

	public static final String bootsName = "demoBoots";
	public static final int bootsMaxStackSize = 1;
	public static final Enchantment[] bootsEnchantments = { Enchantment.thorns };
	public static final int[] bootsEnchantmentLevels = { 3 };
	public static final PotionEffect[] bootsEffects = { new PotionEffect(Potion.jump.id, 600, 0) };
	public static Object[] bootsRecipe = null;

	public static final String foodName = "demoFood";
	public static final int foodMaxStackSize = 64;
	public static final int foodHealAmount = 10;
	public static final float foodSaturationModifier = 2F;
	public static final boolean foodWolvesFavorite = false;
	public static final PotionEffect[] foodEffects = { //
			new PotionEffect(Potion.moveSpeed.id, 1200, 1), //
			new PotionEffect(Potion.jump.id, 600, 0), //
			new PotionEffect(Potion.regeneration.id, 200, 1) };

	public static final String seedName = "demoSeed";

	public static final String blockCropName = "demoBlockCrop";
	public static final String blockBushName = "demoBlockBush";

	public static final String biomeIdConfigName = "demo_biome_id";
	public static final int defaultBiomeId = -1;
	public static final int minBiomeId = 10;
	public static final int maxBiomeId = 1000;
	public static int biomeId = defaultBiomeId;
	public static final String biomeName = "Demo Biome";
	public static final Block biomeTopBlock = Blocks.grass;
	public static final int biomeWeight = 100;
	public static final BiomeType biomeType = BiomeType.COOL;
	public static final int biomeWorldGenerationWeight = 0;

	public static final int biomeDemoBushesPerChunk = 50;
	public static final int biomeWaterlilyPerChunk = 0;
	public static final int biomeTreesPerChunk = 10;
	public static final int biomeFlowersPerChunk = 0;
	public static final int biomeGrassPerChunk = 0;
	public static final int biomeDeadBushPerChunk = 0;
	public static final int biomeMushroomsPerChunk = 0;
	public static final int biomeReedsPerChunk = 0;
	public static final int biomeCactiPerChunk = 0;
	public static final int biomeSandPerChunk = 0;
	public static final int biomeSandPerChunk2 = 0;
	public static final int biomeClayPerChunk = 0;
	public static final int biomeBigMushroomsPerChunk = 15;
	public static final boolean biomeGenerateLakes = true;
	public static final float biomeMinHeight = 0.1F;
	// default biomeMaxHeight = 0.2F
	public static final float biomeMaxHeight = 1.5F;
	public static final float biomeTemperature = 1.5F;
	public static final float biomeRainfall = 0.2F;
	public static final List<SpawnListEntry> biomeSpawnList = new ArrayList<SpawnListEntry>() {
		{
			// new SpawnListEntry(EntityToSpawn.class, spawnRate, spawnMin,
			// spawnMax);
			new SpawnListEntry(EntityMooshroom.class, 25, 4, 8);
			new SpawnListEntry(EntityFourm.class, 25, 4, 8);
		}
	};

	public static final String entityFourmName = "demoFourm";
	public static final int entityFourmId = 1;
	public static final EnumCreatureType entityFourmType = EnumCreatureType.CREATURE;
	public static final float entityFourmShadowSize = 0.3F;
	public static final int entityFourmSpawnColorBase = (new Color(0, 0, 0)).getRGB();
	public static final int entityFourmSpawnColorSpots = (new Color(8, 178, 60)).getRGB();

	public static void initRecipes() {

		swordRecipe = new Object[] { //
				" X ", //
				" X ", //
				" S ", //
				'S', Items.stick, //
				'X', DemoMod.demoItem };

		pickaxeRecipe = new Object[] { //
				"XXX", //
				" S ", //
				" S ", //
				'S', Items.stick, //
				'X', DemoMod.demoItem };

		hoeRecipe = new Object[] { //
				"XX ", //
				" S ", //
				" S ", //
				'S', Items.stick, //
				'X', DemoMod.demoItem };

		shovelRecipe = new Object[] { //
				" X ", //
				" S ", //
				" S ", //
				'S', Items.stick, //
				'X', DemoMod.demoItem };

		axeRecipe = new Object[] { //
				"XX ", //
				"XS ", //
				" S ", //
				'S', Items.stick, //
				'X', DemoMod.demoItem };

		bowRecipe = new Object[] { //
				" XS", //
				"X S", //
				" XS", //
				'S', Items.string, //
				'X', DemoMod.demoItem };

		helmetRecipe = new Object[] { //
				"XXX", //
				"X X", //
				'X', DemoMod.demoItem };

		chestplateRecipe = new Object[] { //
				"X X", //
				"XXX", //
				"XXX", //
				'X', DemoMod.demoItem };

		leggingsRecipe = new Object[] { //
				"XXX", //
				"X X", //
				"X X", //
				'X', DemoMod.demoItem };

		bootsRecipe = new Object[] { //
				"X X", //
				"X X", //
				'X', DemoMod.demoItem };

	}
}
