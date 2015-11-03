package net.woogie.demomod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;
import net.woogie.demomod.biome.DemoBiome;
import net.woogie.demomod.entity.fourm.EntityFourm;
import net.woogie.demomod.entity.fourm.ModelFourm;
import net.woogie.demomod.entity.fourm.RenderFourm;
import net.woogie.demomod.item.DemoArmor;
import net.woogie.demomod.item.DemoFood;
import net.woogie.demomod.item.DemoItem;
import net.woogie.demomod.item.DemoMonsterPlacer;
import net.woogie.demomod.item.DemoPickaxe;
import net.woogie.demomod.item.DemoSeed;
import net.woogie.demomod.item.DemoSword;
import net.woogie.demomod.item.block.DemoBlock;
import net.woogie.demomod.item.block.DemoBlockBush;
import net.woogie.demomod.item.block.DemoBlockCrop;
import net.woogie.demomod.item.block.DemoBlockMultiOre;
import net.woogie.demomod.item.block.DemoBlockOre;
import net.woogie.demomod.network.DemoBiomeIdPacket;
import net.woogie.demomod.network.DemoConnectionHandler;
import net.woogie.demomod.network.DemoPacketHandler;
import net.woogie.demomod.world.DemoWorldGenerator;

public class CommonProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		config.load();

		config.getInt(Config.biomeIdConfigName, Configuration.CATEGORY_GENERAL, Config.defaultBiomeId,
				Config.defaultBiomeId, Config.maxBiomeId,
				"If you set this to " + Config.defaultBiomeId + " the mod will try to find a free biome id");

		if (Config.biomeId == Config.defaultBiomeId) {

			Config.biomeId = Config.minBiomeId;

			while (BiomeDictionary.isBiomeRegistered(Config.biomeId)) {
				Config.biomeId++;
			}

			config.get(Configuration.CATEGORY_GENERAL, "demo_biome_id", Config.defaultBiomeId).set(Config.biomeId);
			config.save();
		}

		DemoPacketHandler.registerPacketHandler(new DemoBiomeIdPacket());
		FMLCommonHandler.instance().bus().register(new DemoConnectionHandler());
		DemoPacketHandler.bus = NetworkRegistry.INSTANCE.newEventDrivenChannel(Config.MODID);
		DemoPacketHandler.bus.register(new DemoPacketHandler());

		DemoMod.demoBlock = new DemoBlock();
		GameRegistry.registerBlock(DemoMod.demoBlock, Config.blockName);

		DemoMod.demoBlockOre = new DemoBlockOre();
		GameRegistry.registerBlock(DemoMod.demoBlockOre, Config.blockOreName);

		DemoMod.demoBlockMultiOre = new DemoBlockMultiOre();
		GameRegistry.registerBlock(DemoMod.demoBlockMultiOre, Config.blockMultiOreName);

		DemoMod.demoBlockCrop = new DemoBlockCrop();
		GameRegistry.registerBlock(DemoMod.demoBlockCrop, Config.blockCropName);

		DemoMod.demoBlockBush = new DemoBlockBush();
		GameRegistry.registerBlock(DemoMod.demoBlockBush, Config.blockBushName);

		DemoMod.demoItem = new DemoItem();
		GameRegistry.registerItem(DemoMod.demoItem, Config.itemName);

		DemoMod.demoSword = new DemoSword();
		GameRegistry.registerItem(DemoMod.demoSword, Config.swordName);

		DemoMod.demoPickaxe = new DemoPickaxe();
		GameRegistry.registerItem(DemoMod.demoPickaxe, Config.pickaxeName);

		DemoMod.demoHelmet = new DemoArmor(Config.helmetName);
		GameRegistry.registerItem(DemoMod.demoHelmet, Config.helmetName);

		DemoMod.demoChestplate = new DemoArmor(Config.chestplateName);
		GameRegistry.registerItem(DemoMod.demoChestplate, Config.chestplateName);

		DemoMod.demoLeggings = new DemoArmor(Config.leggingsName);
		GameRegistry.registerItem(DemoMod.demoLeggings, Config.leggingsName);

		DemoMod.demoBoots = new DemoArmor(Config.bootsName);
		GameRegistry.registerItem(DemoMod.demoBoots, Config.bootsName);

		DemoMod.demoFood = new DemoFood();
		GameRegistry.registerItem(DemoMod.demoFood, Config.foodName);

		DemoMod.demoSeed = new DemoSeed();
		GameRegistry.registerItem(DemoMod.demoSeed, Config.seedName);

		DemoMod.demoFourmMonsterPlacer = new DemoMonsterPlacer(Config.entityFourmName, Config.entityFourmSpawnColorBase,
				Config.entityFourmSpawnColorSpots);
		GameRegistry.registerItem(DemoMod.demoFourmMonsterPlacer, "spawn_" + Config.entityFourmName);

		DemoMod.demoBiome = new DemoBiome();
		BiomeManager.addBiome(Config.biomeType, new BiomeEntry(DemoMod.demoBiome, Config.biomeWeight));

		Config.initRecipes();
	}

	@Override
	public void init(FMLInitializationEvent event) {

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(DemoMod.demoBlock),
				0, new ModelResourceLocation(Config.MODID + ":" + Config.blockName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
				Item.getItemFromBlock(DemoMod.demoBlockOre), 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.blockOreName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
				Item.getItemFromBlock(DemoMod.demoBlockMultiOre), 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.blockMultiOreName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoItem, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.itemName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoSword, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.swordName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoPickaxe, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.pickaxeName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoHelmet, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.helmetName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoChestplate, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.chestplateName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoLeggings, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.leggingsName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoBoots, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.bootsName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoFood, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.foodName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoFourmMonsterPlacer, 0,
				new ModelResourceLocation(Config.MODID + ":spawn_" + Config.entityFourmName, "inventory"));

		GameRegistry.addRecipe(new ItemStack(DemoMod.demoItem), Config.itemRecipe);

		GameRegistry.addRecipe(new ItemStack(DemoMod.demoSword, 1), Config.swordRecipe);

		GameRegistry.addRecipe(new ItemStack(DemoMod.demoPickaxe, 1), Config.pickaxeRecipe);

		GameRegistry.addRecipe(new ItemStack(DemoMod.demoHelmet, 1), Config.helmetRecipe);

		GameRegistry.addRecipe(new ItemStack(DemoMod.demoChestplate, 1), Config.chestplateRecipe);

		GameRegistry.addRecipe(new ItemStack(DemoMod.demoLeggings, 1), Config.leggingsRecipe);

		GameRegistry.addRecipe(new ItemStack(DemoMod.demoBoots, 1), Config.bootsRecipe);

		GameRegistry.addRecipe(new ItemStack(DemoMod.demoFood, 1), Config.foodRecipe);

		GameRegistry.addSmelting(DemoMod.demoBlock, new ItemStack(DemoMod.demoItem), Config.itemExperience);

		GameRegistry.registerWorldGenerator(new DemoWorldGenerator(), Config.biomeWorldGenerationWeight);

		EntityRegistry.registerModEntity(EntityFourm.class, Config.entityFourmName, Config.entityFourmId,
				DemoMod.instance, 64, 1, true);

		// EntityRegistry.addSpawn(EntityToSpawn.class, weightedProb, min, max,
		// typeOfCreature, BiomeGenBase... biomes)
		EntityRegistry.addSpawn(EntityFourm.class, 5, 1, 2, Config.entityFourmType, BiomeGenBase.desert,
				DemoMod.demoBiome);

		RenderingRegistry.registerEntityRenderingHandler(EntityFourm.class, new RenderFourm(
				Minecraft.getMinecraft().getRenderManager(), new ModelFourm(), Config.entityFourmShadowSize));

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {

	}

}
