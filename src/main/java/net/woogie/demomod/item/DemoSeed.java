package net.woogie.demomod.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;

public class DemoSeed extends ItemFood implements IPlantable {
	private final IBlockState blockPlant;

	public DemoSeed() {
		super(Config.seedHealAmount, Config.seedSaturationModifier, false);
		setUnlocalizedName(Config.MODID + ":" + Config.seedName);
		setCreativeTab(CreativeTabs.tabFood);
		this.blockPlant = DemoMod.demoBlockCrop.getDefaultState();
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World worldIn, BlockPos position,
			EnumFacing side, float hitx, float hity, float hitz) {

		// If on top of block and player can edit the block and block is
		// farmland and there is air on the top, then we can plant

		if (side == EnumFacing.UP && player.canPlayerEdit(position, side, itemStack)
				&& worldIn.getBlockState(position).getBlock() == Blocks.farmland
				&& worldIn.isAirBlock(position.offset(side))) {

			worldIn.setBlockState(position.offset(side), this.blockPlant);

			--itemStack.stackSize;

			return true;
		} else {
			return false;
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Crop;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return this.blockPlant;
	}
}