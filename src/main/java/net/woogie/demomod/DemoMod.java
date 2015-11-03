package net.woogie.demomod;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.woogie.demomod.item.DemoMonsterPlacer;
import net.woogie.demomod.item.block.DemoBlock;
import net.woogie.demomod.item.block.DemoBlockBush;
import net.woogie.demomod.item.block.DemoBlockCrop;
import net.woogie.demomod.item.block.DemoBlockMultiOre;
import net.woogie.demomod.item.block.DemoBlockOre;
import net.woogie.demomod.proxy.CommonProxy;

@Mod(modid = Config.MODID, name = Config.MODNAME, version = Config.VERSION, acceptedMinecraftVersions = "[1.8]", dependencies = "required-after:Forge@[11.14.3.1450,)")
public class DemoMod {

	@SidedProxy(clientSide = "net.woogie.demomod.proxy.ClientProxy", serverSide = "net.woogie.demomod.proxy.ServerProxy")
	public static CommonProxy proxy;

	@Mod.Instance(Config.MODID)
	public static DemoMod instance;

	public static ToolMaterial demoToolMaterial = EnumHelper.addToolMaterial(Config.toolMaterialName,
			Config.toolMaterialHarvestLevel, Config.toolMaterialDurability, Config.toolMaterialMiningSpeed,
			Config.toolMaterialDamageVsEnitities, Config.toolMaterialEnchantability);

	public static ArmorMaterial demoArmorMaterial = EnumHelper.addArmorMaterial(Config.armorMaterialName,
			Config.armorMaterialTextureName, Config.armorMaterialDurability, Config.armorMaterialDamageReduction,
			Config.armorMaterialEnchantability);

	public static DemoBlock demoBlock;
	public static DemoBlockOre demoBlockOre;
	public static DemoBlockMultiOre demoBlockMultiOre;
	public static DemoBlockCrop demoBlockCrop;
	public static DemoBlockBush demoBlockBush;
	public static Item demoItem;
	public static ItemSword demoSword;
	public static ItemPickaxe demoPickaxe;
	public static Item demoHelmet;
	public static Item demoChestplate;
	public static Item demoLeggings;
	public static Item demoBoots;
	public static ItemFood demoFood;
	public static Item demoSeed;

	public static BiomeGenBase demoBiome;

	public static DemoMonsterPlacer demoFourmMonsterPlacer;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}