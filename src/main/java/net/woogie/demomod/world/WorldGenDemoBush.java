package net.woogie.demomod.world;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.woogie.demomod.DemoMod;
import net.woogie.demomod.item.block.DemoBlockBush;

public class WorldGenDemoBush extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos) {

		{
			for (int i = 0; i < 64; ++i) {
				BlockPos blockpos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4),
						rand.nextInt(8) - rand.nextInt(8));

				if (worldIn.isAirBlock(blockpos1) && (!worldIn.provider.getHasNoSky() || blockpos1.getY() < 255)
						&& DemoMod.demoBlockBush.canBlockStay(worldIn, blockpos1,
								DemoMod.demoBlockBush.getDefaultState())) {
					worldIn.setBlockState(blockpos1, DemoMod.demoBlockBush.getDefaultState());
				}
			}

			return true;
		}
	}
}
