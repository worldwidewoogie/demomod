package net.woogie.demomod.item;

import java.util.List;
import java.util.Set;

import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.woogie.demomod.Config;

public class DemoMonsterPlacer extends ItemMonsterPlacer {
	protected int colorBase = 0x000000;
	protected int colorSpots = 0xFFFFFF;
	protected String entityName = "";

	public DemoMonsterPlacer() {
		super();
	}

	public DemoMonsterPlacer(String entityName, int colorBase, int colorSpots) {
		this.setUnlocalizedName(Config.MODID + ":spawn_" + entityName);
		this.setHasSubtypes(false);
		this.maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.entityName = Config.MODID + "." + entityName;
		this.colorBase = colorBase;
		this.colorSpots = colorSpots;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item parItem, CreativeTabs parTab, List parListSubItems) {
		parListSubItems.add(new ItemStack(this, 1));
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 * 
	 * @param pos
	 *            The block being right-clicked
	 * @param side
	 *            The side being right-clicked
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else if (!playerIn.canPlayerEdit(pos.offset(side), side, stack)) {
			return false;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos);

			if (iblockstate.getBlock() == Blocks.mob_spawner) {
				TileEntity tileentity = worldIn.getTileEntity(pos);

				if (tileentity instanceof TileEntityMobSpawner) {
					MobSpawnerBaseLogic mobspawnerbaselogic = ((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic();
					mobspawnerbaselogic.setEntityName(EntityList.getStringFromID(stack.getMetadata()));
					tileentity.markDirty();
					worldIn.markBlockForUpdate(pos);

					if (!playerIn.capabilities.isCreativeMode) {
						--stack.stackSize;
					}

					return true;
				}
			}

			pos = pos.offset(side);
			double d0 = 0.0D;

			if (side == EnumFacing.UP && iblockstate instanceof BlockFence) {
				d0 = 0.5D;
			}

			Entity entity = spawnEntity(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);

			if (entity != null) {
				if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
					entity.setCustomNameTag(stack.getDisplayName());
				}

				if (!playerIn.capabilities.isCreativeMode) {
					--stack.stackSize;
				}
			}

			return true;
		}
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		if (worldIn.isRemote) {
			return itemStackIn;
		} else {
			MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, true);

			if (movingobjectposition == null) {
				return itemStackIn;
			} else {
				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					BlockPos blockpos = movingobjectposition.getBlockPos();

					if (!worldIn.isBlockModifiable(playerIn, blockpos)) {
						return itemStackIn;
					}

					if (!playerIn.canPlayerEdit(blockpos, movingobjectposition.sideHit, itemStackIn)) {
						return itemStackIn;
					}

					if (worldIn.getBlockState(blockpos).getBlock() instanceof BlockLiquid) {
						Entity entity = spawnEntity(worldIn, blockpos.getX() + 0.5D, blockpos.getY() + 0.5D,
								blockpos.getZ() + 0.5D);

						if (entity != null) {
							if (entity instanceof EntityLivingBase && itemStackIn.hasDisplayName()) {
								((EntityLiving) entity).setCustomNameTag(itemStackIn.getDisplayName());
							}

							if (!playerIn.capabilities.isCreativeMode) {
								--itemStackIn.stackSize;
							}

							playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
						}
					}
				}

				return itemStackIn;
			}
		}
	}

	/**
	 * Spawns the creature specified by the egg's type in the location specified
	 * by the last three parameters. Parameters: world, x, y, z.
	 */

	public Entity spawnEntity(World worldIn, double x, double y, double z) {

		EntityLiving entityToSpawn = null;

		Set<String> keys = EntityList.stringToClassMapping.keySet();

		if (!worldIn.isRemote) { // never spawn entity on client side
			if (EntityList.stringToClassMapping.containsKey(entityName)) {
				entityToSpawn = (EntityLiving) EntityList.createEntityByName(entityName, worldIn);
				entityToSpawn.setLocationAndAngles(x, y, z,
						MathHelper.wrapAngleTo180_float(worldIn.rand.nextFloat() * 360.0F), 0.0F);
				worldIn.spawnEntityInWorld(entityToSpawn);
				entityToSpawn.playLivingSound();
			} else {
				// DEBUG
				System.out.println("Entity not found " + entityName);
			}
		}

		return entityToSpawn;

	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int parRenderPass) {
		return (parRenderPass == 0) ? colorBase : colorSpots;
	}
}