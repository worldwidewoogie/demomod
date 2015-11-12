package net.woogie.demomod.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoBiome extends BiomeGenBase {

	public DemoBiome() {
		super(Config.biomeId);

		this.setBiomeName(Config.biomeName);

		this.topBlock = Config.biomeTopBlock.getDefaultState();
		this.fillerBlock = DemoMod.demoBlockOre.getDefaultState();

		this.theBiomeDecorator = new DemoBiomeDecorator();

		((DemoBiomeDecorator) this.theBiomeDecorator).demoPlantsPerChunk = Config.biomeDemoBushesPerChunk;

		this.theBiomeDecorator.waterlilyPerChunk = Config.biomeWaterlilyPerChunk;
		this.theBiomeDecorator.treesPerChunk = Config.biomeTreesPerChunk;
		this.theBiomeDecorator.flowersPerChunk = Config.biomeFlowersPerChunk;
		this.theBiomeDecorator.grassPerChunk = Config.biomeGrassPerChunk;
		this.theBiomeDecorator.deadBushPerChunk = Config.biomeDeadBushPerChunk;
		this.theBiomeDecorator.mushroomsPerChunk = Config.biomeMushroomsPerChunk;
		this.theBiomeDecorator.reedsPerChunk = Config.biomeReedsPerChunk;
		this.theBiomeDecorator.cactiPerChunk = Config.biomeCactiPerChunk;
		this.theBiomeDecorator.sandPerChunk = Config.biomeSandPerChunk;
		this.theBiomeDecorator.sandPerChunk2 = Config.biomeSandPerChunk2;
		this.theBiomeDecorator.clayPerChunk = Config.biomeClayPerChunk;
		this.theBiomeDecorator.bigMushroomsPerChunk = Config.biomeBigMushroomsPerChunk;
		this.theBiomeDecorator.generateLakes = true;

		this.setHeight(new Height(Config.biomeMinHeight, Config.biomeMaxHeight));
		this.setTemperatureRainfall(Config.biomeTemperature, Config.biomeRainfall);
	}
}
