package net.woogie.demomod;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionAbsorption;
import net.minecraft.potion.PotionAttackDamage;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHealth;
import net.minecraft.potion.PotionHealthBoost;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.woogie.demomod.entity.hostile.DemoEntityHostile;
import net.woogie.demomod.entity.tameable.DemoEntityTameable;

public class Config {

	// Don't change these unless you know what you are doing
	public static final String MODID = "demomod";
	public static final String MODNAME = "Demo Mod";
	public static final String VERSION = "1.0";

	// Changing the name can have unintended consequences
	public static final String toolMaterialName = "demoToolMaterial";
	// Level of the tool needed to harvest the material (0-3). Higher is harder
	//
	// Examples:
	//
	// wood: 0
	// stone: 1
	// iron: 2
	// diamond: 3
	// gold: 0
	public static final int toolMaterialHarvestLevel = 3;
	// Durability of the material. Higher is more durable
	//
	// Examples:
	//
	// wood: 59
	// stone: 131
	// iron: 250
	// diamond: 1561
	// gold: 32
	public static final int toolMaterialDurability = 1000;
	// Mining speed (how much faster is it to use a tool of this material than
	// to use your hands
	//
	// Examples:
	//
	// wood: 2.0F
	// stone: 4.0F
	// iron: 6.0F
	// diamond: 8.0F
	// gold: 12.0F
	public static final float toolMaterialMiningSpeed = 15.0F;
	// DamageVsEntitis is used to calculate the damage an entity takes if you
	// hit it with a tool/sword of this material. This value defines the basic
	// damage to which different values are added, depending on the type of
	// tool. For example, a sword causes 4 more damage this. If you want to
	// create a sword which adds 10 damage to your normal damage, the value in
	// the ToolMaterial needs to be 6.0F. The value can be below zero.
	//
	// Examples:
	//
	// wood: 0.0F
	// stone: 1.0F
	// iron: 2.0F
	// diamond: 3.0F
	// gold: 0.0F
	public static final float toolMaterialDamageVsEnitities = 4.0F;
	// Enchantablity of the material. This is complicated so
	// Wood: 15
	// Stone: 5
	// Iron: 14
	// Diamond: 10
	// Gold: 22
	public static final int toolMaterialEnchantability = 30;

	// Changing the name can have unintended consequences
	public static final String armorMaterialName = "demoArmorMaterial";
	public static final String armorMaterialTextureName = MODID + ":" + armorMaterialName;

	// Durability (See comment for toolMaterialDurability)
	public static final int armorMaterialDurability = 1000;

	// Enchantability (See comment for toolMaterialEnchantability)
	public static final int armorMaterialEnchantability = 30;

	// Damage reduction for each piece of armor
	public static final int armorMaterialDamageReductionHelmet = 3;
	public static final int armorMaterialDamageReductionChestplate = 8;
	public static final int armorMaterialDamageReductionLeggings = 6;
	public static final int armorMaterialDamageReductionBoots = 3;

	// Don't change this
	public static final int[] armorMaterialDamageReduction = new int[] { //
			armorMaterialDamageReductionHelmet, //
			armorMaterialDamageReductionChestplate, //
			armorMaterialDamageReductionLeggings, //
			armorMaterialDamageReductionBoots };

	// Changing the name can have unintended consequences
	public static final String itemName = "demoItem";
	// Max stack size in inventory
	public static final int itemMaxStackSize = 64;
	// Don't change this. Recipes are defined below
	public static Object[] itemRecipe = null;
	// Experience gained for this item
	public static final float itemExperience = 1.0F;

	// Changing the name can have unintended consequences
	public static final String blockName = "demoBlock";
	// The block's base material
	public static final Material blockParentMaterial = Material.rock;
	// The amount of light the block emits. If it is 0.0F, the block will not
	// emit any light
	public static final float blockLightLevel = 1.0F;
	// The blocks hardness.
	//
	// Examples:
	// stone: 1.5F
	// obsidian: 50.0F
	public static final float blockHardness = 10.0F;
	// The blocks resistance to explosions
	//
	// Examples
	// stone:10.0F
	// obsidian:2000.0F
	public static final float blockResistance = 15.0F;
	// you probably don't want to change this
	public static final String blockMineTool = Config.MODID + "." + Config.pickaxeName;
	// Level needed to harvest the block
	// Examples:
	//
	// wood: 0
	// stone: 1
	// iron: 2
	// diamond: 3
	// gold: 0
	public static final int blockMineLevel = 1;
	// Maximum number of Demo Items the block will drop when mined
	public static final int blockDropMax = 7;
	// Minimum number of Demo Items the block will drop when mined
	public static final int blockDropMin = 2;
	// If the block is the multi-ore, the extra type of item it will drop
	public static final Item blockMultiDropItem = Items.gold_nugget;
	// Maximum number of extra items the block will drop when mined
	public static final int blockMultiDropMax = 7;
	// Minimum number of extra items the block will drop when mined
	public static final int blockMultiDropMin = 2;
	// Rarely, the multi ore will drop a bonus item
	// What is the bonus item?
	public static final Item blockMultiDropBonusItem = Items.diamond;
	// What is the chance it will drop the bonus item?
	public static final float blockMultiDropBonusChance = 0.1F;

	// Changing the name can have unintended consequences
	public static final String blockOreName = "demoBlockOre";
	public static final int blockOreChancesToSpawn = 20;
	public static final int blockOreMinHeight = 0;
	public static final int blockOreMaxHeight = 64;

	// Changing the name can have unintended consequences
	public static final String blockMultiOreName = "demoBlockMultiOre";
	public static final int blockMultiOreChancesToSpawn = 10;
	public static final int blockMultiOreMinHeight = 0;
	public static final int blockMultiOreMaxHeight = 16;

	// Changing the name can have unintended consequences
	public static final String swordName = "demoSword";
	// Max stack size in inventory
	public static final int swordMaxStackSize = 1;
	// If this is true, the sword will summon lightning when it hits an entity
	// or player
	public static final boolean swordSummonsLightning = true;
	// Add potion effects to the sword. When hit by the sword, the opponent will
	// also have the potion applied. Add as many potions as you want, but they
	// must be comma separated.
	//
	// PotionEffect(potionId, effectDuration, effectAmplifier)
	//
	// These are the valid potion IDs:
	//
	// Potion.moveSpeed.id
	// Potion.moveSlowdown.id
	// Potion.digSpeed.id
	// Potion.digSlowdown.id
	// Potion.damageBoost.id
	// Potion.heal.id
	// Potion.harm.id
	// Potion.jump.id
	// Potion.confusion.id
	// Potion.regeneration.id
	// Potion.resistance.id
	// Potion.fireResistance.id
	// Potion.waterBreathing.id
	// Potion.invisibility.id
	// Potion.blindness.id
	// Potion.nightVision.id
	// Potion.hunger.id
	// Potion.weakness.id
	// Potion.poison.id
	// Potion.wither.id
	// Potion.healthBoost.id
	// Potion.absorption.id
	// Potion.saturation.id
	//
	// Effect duration is the time, in seconds that the effect lasts
	//
	// Effect amplifier is the level of the effect
	//
	// More documentation about effect can be found here:
	//
	// http://minecraft.gamepedia.com/Effect
	//
	public static final PotionEffect[] swordEffects = { //
			new PotionEffect(Potion.blindness.id, 1200, 1), //
			new PotionEffect(Potion.poison.jump.id, 600, 0), //
			new PotionEffect(Potion.weakness.id, 200, 1) };
	//
	// Add enchantments to the sword
	//
	// Enchantment.fireProtection
	// Enchantment.featherFalling
	// Enchantment.blastProtection
	// Enchantment.projectileProtection
	// Enchantment.respiration
	// Enchantment.aquaAffinity
	// Enchantment.thorns
	// Enchantment.depthStrider
	// Enchantment.sharpness
	// Enchantment.smite
	// Enchantment.baneOfArthropods
	// Enchantment.knockback
	// Enchantment.fireAspect
	// Enchantment.looting
	// Enchantment.efficiency
	// Enchantment.silkTouch
	// Enchantment.unbreaking
	// Enchantment.fortune
	// Enchantment.power
	// Enchantment.punch
	// Enchantment.flame
	// Enchantment.infinity
	// Enchantment.luckOfTheSea
	// Enchantment.lure
	//
	// Note that not all enchantments are appropriate for all item types, so
	// some might not have the desired effect.
	//
	// More documentation about enchantments can be found here:
	//
	// http://minecraft.gamepedia.com/Enchanting#Enchantments
	//
	public static final Enchantment[] swordEnchantments = { Enchantment.sharpness, Enchantment.fireAspect };
	// For each enchantment listed above, give the enchantment level. Make sure
	// you have the same number of enchantment
	// levels as enchantments, or your mod could crash
	public static final int[] swordEnchantmentLevels = { 3, 3 };
	// Don't change this. Recipes are defined below
	public static Object[] swordRecipe = null;

	// Changing the name can have unintended consequences
	public static final String giantSwordName = "demoGiantSword";
	// Max stack size in inventory
	public static final int giantSwordMaxStackSize = 1;
	// If this is true, the sword will summon lightning when it hits an entity
	// or player
	public static final boolean giantSwordSummonsLightning = true;
	// Add effects to your sword. See swordEffects above for options
	public static final PotionEffect[] giantSwordEffects = { //
			new PotionEffect(Potion.blindness.id, 1200, 1), //
			new PotionEffect(Potion.poison.jump.id, 600, 0), //
			new PotionEffect(Potion.weakness.id, 200, 1) };
	// Add enchantments to your sword. See swordEnchantments above for options
	public static final Enchantment[] giantSwordEnchantments = { Enchantment.sharpness, Enchantment.fireAspect };
	// For each enchantment listed above, give the enchantment level. Make sure
	// you have the same number of enchantment
	// levels as enchantments, or your mod could crash
	public static final int[] giantSwordEnchantmentLevels = { 3, 3 };

	// Changing the name can have unintended consequences
	public static final String pickaxeName = "demoPickaxe";
	// Max stack size in inventory
	public static final int pickaxeMaxStackSize = 1;
	// If this is true, the pickaxe will summon lightning when it hits an entity
	// or player
	public static final boolean pickaxeSummonsLightning = true;
	// Add effects to your pickaxe. See swordEffects above for options
	public static final PotionEffect[] pickaxeEffects = { //
			new PotionEffect(Potion.blindness.id, 1200, 1), //
			new PotionEffect(Potion.poison.jump.id, 600, 0), //
			new PotionEffect(Potion.weakness.id, 200, 1) };
	// Add enchantments to your pickaxe. See swordEnchantments above for options
	public static final Enchantment[] pickaxeEnchantments = { Enchantment.efficiency, Enchantment.fortune };
	// For each enchantment listed above, give the enchantment level. Make sure
	// you have the same number of enchantment
	// levels as enchantments, or your mod could crash
	public static final int[] pickaxeEnchantmentLevels = { 3, 3 };
	// Don't change this. Recipes are defined below
	public static Object[] pickaxeRecipe = null;

	// Changing the name can have unintended consequences
	public static final String hoeName = "demoHoe";
	// Max stack size in inventory
	public static final int hoeMaxStackSize = 1;
	// Add enchantments to your hoe. See swordEnchantments above for options
	public static final Enchantment[] hoeEnchantments = { Enchantment.unbreaking };
	// For each enchantment listed above, give the enchantment level. Make sure
	// you have the same number of enchantment
	// levels as enchantments, or your mod could crash
	public static final int[] hoeEnchantmentLevels = { 3 };
	public static Object[] hoeRecipe = null;

	// Changing the name can have unintended consequences
	public static final String shovelName = "demoShovel";
	// Max stack size in inventory
	public static final int shovelMaxStackSize = 1;
	// Add enchantments to your shovel. See swordEnchantments above for options
	public static final Enchantment[] shovelEnchantments = { Enchantment.efficiency, Enchantment.silkTouch };
	// For each enchantment listed above, give the enchantment level. Make sure
	// you have the same number of enchantment
	// levels as enchantments, or your mod could crash
	public static final int[] shovelEnchantmentLevels = { 3, 3 };
	// Don't change this. Recipes are defined below
	public static Object[] shovelRecipe = null;

	// Changing the name can have unintended consequences
	public static final String axeName = "demoAxe";
	// Max stack size in inventory
	public static final int axeMaxStackSize = 1;
	// Add enchantments to your axe. See swordEnchantments above for options
	public static final Enchantment[] axeEnchantments = { Enchantment.efficiency, Enchantment.silkTouch };
	// For each enchantment listed above, give the enchantment level. Make sure
	// you have the same number of enchantment
	// levels as enchantments, or your mod could crash
	public static final int[] axeEnchantmentLevels = { 3, 3 };
	// Don't change this. Recipes are defined below
	public static Object[] axeRecipe = null;

	// Changing the name can have unintended consequences
	public static final String bowName = "demoBow";
	// Max stack size in inventory
	public static final int bowMaxStackSize = 1;
	// Add enchantments to your bow. See swordEnchantments above for options
	public static final Enchantment[] bowEnchantments = { Enchantment.flame, Enchantment.infinity };
	// For each enchantment listed above, give the enchantment level. Make sure
	// you have the same number of enchantment
	// levels as enchantments, or your mod could crash
	public static final int[] bowEnchantmentLevels = { 3, 3 };
	// Don't change this. Recipes are defined below
	public static Object[] bowRecipe = null;

	// Changing the name can have unintended consequences
	public static final String armorName = "demoArmor";

	// Changing the name can have unintended consequences
	public static final String helmetName = "demoHelmet";
	// Max stack size in inventory
	public static final int helmetMaxStackSize = 1;
	// Add enchantments to your helmet. See swordEnchantments above for options
	public static final Enchantment[] helmetEnchantments = { Enchantment.thorns };
	// For each enchantment listed above, give the enchantment level. Make sure
	// you have the same number of enchantment
	// levels as enchantments, or your mod could crash
	public static final int[] helmetEnchantmentLevels = { 3 };
	// Add effects to your helmet. See swordEffects above for options
	public static final PotionEffect[] helmetEffects = { new PotionEffect(Potion.waterBreathing.id, 200, 0) };
	// Don't change this. Recipes are defined below
	public static Object[] helmetRecipe = null;

	// Changing the name can have unintended consequences
	public static final String chestplateName = "demoChestplate";
	// Max stack size in inventory
	public static final int chestplateMaxStackSize = 1;
	// Add enchantments to your chestplate. See swordEnchantments above for
	// options
	public static final Enchantment[] chestplateEnchantments = { Enchantment.thorns };
	// For each enchantment listed above, give the enchantment level. Make sure
	// you have the same number of enchantment
	// levels as enchantments, or your mod could crash
	public static final int[] chestplateEnchantmentLevels = { 3 };
	// Add effects to your chestplate. See swordEffects above for options
	public static final PotionEffect[] chestplateEffects = { new PotionEffect(Potion.regeneration.id, 200, 0) };
	// Don't change this. Recipes are defined below
	public static Object[] chestplateRecipe = null;

	// Changing the name can have unintended consequences
	public static final String leggingsName = "demoLeggings";
	// Max stack size in inventory
	public static final int leggingsMaxStackSize = 1;
	// Add enchantments to your leggings. See swordEnchantments above for
	// options
	public static final Enchantment[] leggingsEnchantments = { Enchantment.thorns };
	// For each enchantment listed above, give the enchantment level. Make sure
	// you have the same number of enchantment
	// levels as enchantments, or your mod could crash
	public static final int[] leggingsEnchantmentLevels = { 3 };
	// Add effects to your leggings. See swordEffects above for options
	public static final PotionEffect[] leggingsEffects = { new PotionEffect(Potion.moveSpeed.id, 1200, 0) };
	// Don't change this. Recipes are defined below
	public static Object[] leggingsRecipe = null;

	// Changing the name can have unintended consequences
	public static final String bootsName = "demoBoots";
	// Max stack size in inventory
	public static final int bootsMaxStackSize = 1;
	// Add enchantments to your boots. See swordEnchantments above for options
	public static final Enchantment[] bootsEnchantments = { Enchantment.thorns };
	// For each enchantment listed above, give the enchantment level. Make sure
	// you have the same number of enchantment
	// levels as enchantments, or your mod could crash
	public static final int[] bootsEnchantmentLevels = { 3 };
	// Add effects to your boots. See swordEffects above for options
	public static final PotionEffect[] bootsEffects = { new PotionEffect(Potion.jump.id, 600, 0) };
	// Don't change this. Recipes are defined below
	public static Object[] bootsRecipe = null;

	// Changing the name can have unintended consequences
	public static final String foodName = "demoFood";
	// Max stack size in inventory
	public static final int foodMaxStackSize = 64;
	public static final int foodHealAmount = 10;
	public static final float foodSaturationModifier = 2F;
	public static final boolean foodWolvesFavorite = false;
	public static final PotionEffect[] foodEffects = { //
			new PotionEffect(Potion.moveSpeed.id, 1200, 1), //
			new PotionEffect(Potion.jump.id, 600, 0), //
			new PotionEffect(Potion.regeneration.id, 200, 1) };

	// Changing the name can have unintended consequences
	public static final String seedName = "demoSeed";

	// Changing the name can have unintended consequences
	public static final String blockCropName = "demoBlockCrop";
	public static final String blockBushName = "demoBlockBush";

	// Changing the name can have unintended consequences
	public static final String biomeIdConfigName = "demo_biome_id";
	// Don't change any of these
	public static final int defaultBiomeId = -1;
	public static final int minBiomeId = 10;
	public static final int maxBiomeId = 1000;
	public static int biomeId = defaultBiomeId;
	// Set the Biome name
	public static final String biomeName = "Demo Biome";
	// Set the top block for your biome
	public static final Block biomeTopBlock = Blocks.grass;
	// The weight given to the biome. The higher the number, the more the biome
	// will appear in your world. The default is 10, but you probably want it
	// much higher so you don't have to spend too much time searching for it
	public static final int biomeWeight = 1000;
	// Set the biome type. Valid values are
	//
	// BiomeType.DESERT
	// BiomeType.WARM
	// BiomeType.COOL
	// BiomeType.ICY
	//
	public static final BiomeType biomeType = BiomeType.COOL;
	// Weight of you world generator. Smaller number is better. You probably
	// want to keep this as 0
	public static final int biomeWorldGenerationWeight = 0;
	// How many of the different decorations do you want in your biome? (A chunk
	// is 16x16 blocks)
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
	// Set to true if you want to generate lakes, false if you do not
	public static final boolean biomeGenerateLakes = true;
	// default biomeMinHeight = 0.1
	public static final float biomeMinHeight = 0.1F;
	// default biomeMaxHeight = 0.2F
	public static final float biomeMaxHeight = 0.2F;
	// set the temperature for your biome
	// less than 0.15F will have snow
	// 1.5F to 0.95F will have rain
	// greater than 0.95F will have neither
	public static final float biomeTemperature = 1.5F;
	// set how often it rains in your biome
	// 0.0F to 1.0F
	// 0.0F = no rain
	// 1.0F = frequent rain
	public static final float biomeRainfall = 0.2F;

	// Changing the name can have unintended consequences
	public static final String entityHostileName = "demoHostile";
	public static final int entityHostileId = 1;
	public static final EnumCreatureType entityHostileType = EnumCreatureType.MONSTER;
	public static final float entityHostileShadowSize = 0.6F;
	public static final int entityHostileSpawnColorBase = (new Color(0, 0, 0)).getRGB();
	public static final int entityHostileSpawnColorSpots = (new Color(255, 0, 0)).getRGB();
	public static final int entityHostileExperience = 5;
	public static final double entityHostileMaxHealth = 35.0D;
	public static final double entityHostileFollowRange = 32.0D;
	public static final double entityHostileKnockbackResistance = 0.0D;
	public static final double entityHostileMovementSpeed = 0.5D;
	public static final double entityHostileAttackDamage = 7.0D;
	public static final boolean entityHostileCanSwim = true;
	public static final boolean entityHostileAvoidDemoEntityTameable = true;
	public static final float entityHostileAvoidDemoEntityTameableRange = 6.0F;
	public static final double entityHostileAvoidDemoEntityTameableFarSpeed = 1.0D;
	public static final double entityHostileAvoidDemoEntityTameableNearSpeed = 1.2D;
	public static final double entityHostileAIAttackOnCollideSpeed = 1.0D;
	public static final double entityHostileAIWanderSpeed = 0.8D;
	public static final float entityHostileAIWatchClosestDistance = 8.0F;
	public static final int entityHostileBaseDrops = 3;
	public static final int entityHostileDropBonus = 2;
	public static final int entityHostileSpawnChance = 50;
	public static final int entityHostileSpawnMin = 4;
	public static final int entityHostileSpawnMax = 8;

	// Changing the name can have unintended consequences
	public static final String entityTameableName = "demoTameable";
	public static final int entityTameableId = 2;
	public static final EnumCreatureType entityTameableType = EnumCreatureType.CREATURE;
	public static final float entityTameableShadowSize = 0.3F;
	public static final int entityTameableSpawnColorBase = (new Color(255, 255, 255)).getRGB();
	public static final int entityTameableSpawnColorSpots = (new Color(0, 0, 0)).getRGB();
	public static final double entityTameableMovementSpeed = 0.5D;
	public static final double entityTameableAttackDamage = 7.0D;
	public static final int entityTameableExperience = 5;
	public static final double entityTameableMaxWildHealth = 9.0D;
	public static final double entityTameableMaxTamedHealth = 35.0D;
	public static final double entityTameableAIAttackOnCollideSpeed = 1.0D;
	public static final float entityTameableAILeapAtTargetHeight = 0.4F;
	public static final double entityTameableAIFollowOwnerSpeed = 1.0D;
	public static final float entityTameableAIFollowOwnerMaxDistance = 10.0F;
	public static final float entityTameableAIFollowOwnerMinDistance = 2.0F;
	public static final double entityTameableAIMateMoveSpeed = 1.0D;
	public static final double entityTameableAIWanderSpeed = 1.0D;
	public static final float entityTameableAIBegDistance = 8.0F;
	public static final float entityTameableAIWatchClosestDistance = 8.0F;
	public static final int entityTameableSpawnChance = 100;
	public static final int entityTameableSpawnMin = 4;
	public static final int entityTameableSpawnMax = 8;

	// Changing the name can have unintended consequences
	public static final String entityBossName = "demoBoss";
	public static final int entityBossId = 3;
	public static final EnumCreatureType entityBossType = EnumCreatureType.MONSTER;
	public static final float entityBossShadowSize = 0.6F;
	public static final int entityBossSpawnColorBase = (new Color(255, 0, 0)).getRGB();
	public static final int entityBossSpawnColorSpots = (new Color(0, 0, 0)).getRGB();
	public static final int entityBossExperience = 5;
	public static final double entityBossMaxHealth = 350.0D;
	public static final double entityBossFollowRange = 32.0D;
	public static final double entityBossKnockbackResistance = 1.0D;
	public static final double entityBossMovementSpeed = 0.5D;
	public static final double entityBossAttackDamage = 40.0D;
	public static final boolean entityBossCanSwim = true;
	public static final boolean entityBossAvoidDemoEntityTameable = true;
	public static final float entityBossAvoidDemoEntityTameableRange = 6.0F;
	public static final double entityBossAvoidDemoEntityTameableFarSpeed = 1.0D;
	public static final double entityBossAvoidDemoEntityTameableNearSpeed = 1.2D;
	public static final double entityBossAIAttackOnCollideSpeed = 1.0D;
	public static final double entityBossAIWanderSpeed = 0.8D;
	public static final float entityBossAIWatchClosestDistance = 8.0F;
	public static final int entityBossSpawnChance = 20;
	public static final int entityBossSpawnMin = 1;
	public static final int entityBossSpawnMax = 1;

	// All the recipes are defined here
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
