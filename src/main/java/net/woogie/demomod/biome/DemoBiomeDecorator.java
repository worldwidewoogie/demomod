package net.woogie.demomod.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE_WATER;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.PUMPKIN;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.REED;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND_PASS2;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CUSTOM;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.woogie.demomod.world.WorldGenDemoBush;

public class DemoBiomeDecorator extends BiomeDecorator {

	public int demoPlantsPerChunk;
	public WorldGenerator demoPlantGen;

	public DemoBiomeDecorator() {
		super();
		demoPlantGen = new WorldGenDemoBush();
	}

	protected void genDecorations(BiomeGenBase p_150513_1_) {
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, field_180294_c));
		this.generateOres();
		int i;
		int j;
		int k;

		boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, SAND);
		for (i = 0; doGen && i < this.sandPerChunk2; ++i) {
			j = this.randomGenerator.nextInt(16) + 8;
			k = this.randomGenerator.nextInt(16) + 8;
			this.sandGen.generate(this.currentWorld, this.randomGenerator,
					this.currentWorld.getTopSolidOrLiquidBlock(this.field_180294_c.add(j, 0, k)));
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, CLAY);
		for (i = 0; doGen && i < this.clayPerChunk; ++i) {
			j = this.randomGenerator.nextInt(16) + 8;
			k = this.randomGenerator.nextInt(16) + 8;
			this.clayGen.generate(this.currentWorld, this.randomGenerator,
					this.currentWorld.getTopSolidOrLiquidBlock(this.field_180294_c.add(j, 0, k)));
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, SAND_PASS2);
		for (i = 0; doGen && i < this.sandPerChunk; ++i) {
			j = this.randomGenerator.nextInt(16) + 8;
			k = this.randomGenerator.nextInt(16) + 8;
			this.gravelAsSandGen.generate(this.currentWorld, this.randomGenerator,
					this.currentWorld.getTopSolidOrLiquidBlock(this.field_180294_c.add(j, 0, k)));
		}

		i = this.treesPerChunk;

		if (this.randomGenerator.nextInt(10) == 0) {
			++i;
		}

		int l;
		BlockPos blockpos;

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, TREE);
		for (j = 0; doGen && j < i; ++j) {
			k = this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(16) + 8;
			WorldGenAbstractTree worldgenabstracttree = p_150513_1_.genBigTreeChance(this.randomGenerator);
			worldgenabstracttree.func_175904_e();
			blockpos = this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l));

			if (worldgenabstracttree.generate(this.currentWorld, this.randomGenerator, blockpos)) {
				worldgenabstracttree.func_180711_a(this.currentWorld, this.randomGenerator, blockpos);
			}
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, BIG_SHROOM);
		for (j = 0; doGen && j < this.bigMushroomsPerChunk; ++j) {
			k = this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(16) + 8;
			this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator,
					this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l)));
		}

		int i1;

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, FLOWERS);
		for (j = 0; doGen && j < this.flowersPerChunk; ++j) {
			k = this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l)).getY() + 32);
			blockpos = this.field_180294_c.add(k, i1, l);
			BlockFlower.EnumFlowerType enumflowertype = p_150513_1_.pickRandomFlower(this.randomGenerator, blockpos);
			BlockFlower blockflower = enumflowertype.getBlockType().getBlock();

			if (blockflower.getMaterial() != Material.air) {
				this.yellowFlowerGen.setGeneratedBlock(blockflower, enumflowertype);
				this.yellowFlowerGen.generate(this.currentWorld, this.randomGenerator, blockpos);
			}
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, GRASS);
		for (j = 0; doGen && j < this.grassPerChunk; ++j) {
			k = this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l)).getY() * 2);
			p_150513_1_.getRandomWorldGenForGrass(this.randomGenerator).generate(this.currentWorld,
					this.randomGenerator, this.field_180294_c.add(k, i1, l));
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, DEAD_BUSH);
		for (j = 0; doGen && j < this.deadBushPerChunk; ++j) {
			k = this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l)).getY() * 2);
			(new WorldGenDeadBush()).generate(this.currentWorld, this.randomGenerator,
					this.field_180294_c.add(k, i1, l));
		}

		j = 0;

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, LILYPAD);
		while (doGen && j < this.waterlilyPerChunk) {
			k = this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l)).getY() * 2);
			blockpos = this.field_180294_c.add(k, i1, l);

			while (true) {
				if (blockpos.getY() > 0) {
					BlockPos blockpos3 = blockpos.down();

					if (this.currentWorld.isAirBlock(blockpos3)) {
						blockpos = blockpos3;
						continue;
					}
				}

				this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, blockpos);
				++j;
				break;
			}
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, SHROOM);
		for (j = 0; doGen && j < this.mushroomsPerChunk; ++j) {
			if (this.randomGenerator.nextInt(4) == 0) {
				k = this.randomGenerator.nextInt(16) + 8;
				l = this.randomGenerator.nextInt(16) + 8;
				BlockPos blockpos2 = this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l));
				this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, blockpos2);
			}

			if (this.randomGenerator.nextInt(8) == 0) {
				k = this.randomGenerator.nextInt(16) + 8;
				l = this.randomGenerator.nextInt(16) + 8;
				i1 = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l)).getY() * 2);
				blockpos = this.field_180294_c.add(k, i1, l);
				this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, blockpos);
			}
		}

		if (doGen && this.randomGenerator.nextInt(4) == 0) {
			j = this.randomGenerator.nextInt(16) + 8;
			k = this.randomGenerator.nextInt(16) + 8;
			l = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(j, 0, k)).getY() * 2);
			this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(j, l, k));
		}

		if (doGen && this.randomGenerator.nextInt(8) == 0) {
			j = this.randomGenerator.nextInt(16) + 8;
			k = this.randomGenerator.nextInt(16) + 8;
			l = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(j, 0, k)).getY() * 2);
			this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(j, l, k));
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, REED);
		for (j = 0; doGen && j < this.reedsPerChunk; ++j) {
			k = this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l)).getY() * 2);
			this.reedGen.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(k, i1, l));
		}

		for (j = 0; doGen && j < 10; ++j) {
			k = this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l)).getY() * 2);
			this.reedGen.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(k, i1, l));
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, PUMPKIN);
		if (doGen && this.randomGenerator.nextInt(32) == 0) {
			j = this.randomGenerator.nextInt(16) + 8;
			k = this.randomGenerator.nextInt(16) + 8;
			l = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(j, 0, k)).getY() * 2);
			(new WorldGenPumpkin()).generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(j, l, k));
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, CACTUS);
		for (j = 0; doGen && j < this.cactiPerChunk; ++j) {
			k = this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l)).getY() * 2);
			this.cactusGen.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(k, i1, l));
		}

		if (this.generateLakes) {
			BlockPos blockpos1;

			doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, LAKE_WATER);
			for (j = 0; doGen && j < 50; ++j) {
				blockpos1 = this.field_180294_c.add(this.randomGenerator.nextInt(16) + 8,
						this.randomGenerator.nextInt(this.randomGenerator.nextInt(248) + 8),
						this.randomGenerator.nextInt(16) + 8);
				(new WorldGenLiquids(Blocks.flowing_water)).generate(this.currentWorld, this.randomGenerator,
						blockpos1);
			}

			doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, LAKE_LAVA);
			for (j = 0; doGen && j < 20; ++j) {
				blockpos1 = this.field_180294_c.add(this.randomGenerator.nextInt(16) + 8,
						this.randomGenerator
								.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8) + 8),
						this.randomGenerator.nextInt(16) + 8);
				(new WorldGenLiquids(Blocks.flowing_lava)).generate(this.currentWorld, this.randomGenerator, blockpos1);
			}
		}

		// DemoMod custom code
		doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, CUSTOM);
		for (j = 0; doGen && j < this.demoPlantsPerChunk; ++j) {
			k = this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l)).getY() * 2);
			this.demoPlantGen.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(k, i1, l));
		}
		// end DemoMod custom code

		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, field_180294_c));
	}

	private int nextInt(int i) {
		if (i <= 1)
			return 0;
		return this.randomGenerator.nextInt(i);
	}
}
