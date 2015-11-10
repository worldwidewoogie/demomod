package net.woogie.demomod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;
import net.woogie.demomod.entity.boss.DemoEntityBoss;
import net.woogie.demomod.entity.boss.DemoModelBoss;
import net.woogie.demomod.entity.boss.DemoRenderBoss;
import net.woogie.demomod.entity.hostile.DemoEntityHostile;
import net.woogie.demomod.entity.hostile.DemoModelHostile;
import net.woogie.demomod.entity.hostile.DemoRenderHostile;
import net.woogie.demomod.entity.tameable.DemoEntityTameable;
import net.woogie.demomod.entity.tameable.DemoModelTameable;
import net.woogie.demomod.entity.tameable.DemoRenderTameable;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(DemoMod.demoBlock),
				0, new ModelResourceLocation(Config.MODID + ":" + Config.blockName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
				Item.getItemFromBlock(DemoMod.demoBlockOre), 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.blockOreName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
				Item.getItemFromBlock(DemoMod.demoBlockMultiOre), 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.blockMultiOreName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
				Item.getItemFromBlock(DemoMod.demoBlockBush), 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.blockBushName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoItem, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.itemName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoSword, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.swordName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoGiantSword, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.giantSwordName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoPickaxe, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.pickaxeName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoHoe, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.hoeName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoShovel, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.shovelName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoAxe, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.axeName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoBow, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.bowName, "inventory"));

		ModelBakery.addVariantName(DemoMod.demoBow, Config.MODID + ":" + Config.bowName,
				Config.MODID + ":" + Config.bowName + "_0", Config.MODID + ":" + Config.bowName + "_1",
				Config.MODID + ":" + Config.bowName + "_2");

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

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoSeed, 0,
				new ModelResourceLocation(Config.MODID + ":" + Config.seedName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoHostileMonsterPlacer, 0,
				new ModelResourceLocation(Config.MODID + ":spawn_" + Config.entityHostileName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoTameableMonsterPlacer, 0,
				new ModelResourceLocation(Config.MODID + ":spawn_" + Config.entityTameableName, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DemoMod.demoBossMonsterPlacer, 0,
				new ModelResourceLocation(Config.MODID + ":spawn_" + Config.entityBossName, "inventory"));

		RenderingRegistry.registerEntityRenderingHandler(DemoEntityHostile.class, new DemoRenderHostile(
				Minecraft.getMinecraft().getRenderManager(), new DemoModelHostile(), Config.entityHostileShadowSize));

		RenderingRegistry.registerEntityRenderingHandler(DemoEntityTameable.class, new DemoRenderTameable(
				Minecraft.getMinecraft().getRenderManager(), new DemoModelTameable(), Config.entityTameableShadowSize));

		RenderingRegistry.registerEntityRenderingHandler(DemoEntityBoss.class, new DemoRenderBoss(
				Minecraft.getMinecraft().getRenderManager(), new DemoModelBoss(), Config.entityBossShadowSize));

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}
