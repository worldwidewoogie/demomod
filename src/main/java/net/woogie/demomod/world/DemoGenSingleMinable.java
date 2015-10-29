package net.woogie.demomod.world;

import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DemoGenSingleMinable extends WorldGenerator {

	private IBlockState block;
	private Predicate<IBlockState> target;

	public DemoGenSingleMinable(IBlockState block, Predicate<IBlockState> target) {
		this.block = block;
		this.target = target;
	}

	@SuppressWarnings("unchecked")
	public DemoGenSingleMinable(IBlockState block) {
		this(block, BlockHelper.forBlock(Blocks.stone));
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		if (world.getBlockState(pos).getBlock().isReplaceableOreGen(world, pos, this.target))
			world.setBlockState(pos, this.block);
		return true;
	}
}