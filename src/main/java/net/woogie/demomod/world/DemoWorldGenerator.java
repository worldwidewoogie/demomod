package net.woogie.demomod.world;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoWorldGenerator implements IWorldGenerator {

	private WorldGenerator genWoogieBlockOre;

	private WorldGenerator genWoogieBlockMultiOre;

	public DemoWorldGenerator() {

		this.genWoogieBlockOre = new WorldGenMinable(DemoMod.demoBlockOre.getDefaultState(),
				Config.blockOreDropCount);
		this.genWoogieBlockMultiOre = new DemoGenSingleMinable(DemoMod.demoBlockMultiOre.getDefaultState());
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {

		switch (world.provider.getDimensionId()) {
		case 0: // Overworld
			this.runGenerator(this.genWoogieBlockOre, world, random, chunkX, chunkZ, Config.blockOreChancesToSpawn,
					Config.blockOreMinHeight, Config.blockOreMaxHeight);
			this.runGenerator(this.genWoogieBlockMultiOre, world, random, chunkX, chunkZ,
					Config.blockMultiOreChancesToSpawn, Config.blockMultiOreMinHeight, Config.blockMultiOreMaxHeight);
			break;
		case -1: // Nether
			break;
		case 1: // End
			break;
		}

	}

	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z,
			int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			generator.generate(world, rand, new BlockPos(x, y, z));
		}
	}

}