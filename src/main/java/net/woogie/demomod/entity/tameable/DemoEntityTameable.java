package net.woogie.demomod.entity.tameable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;
import net.woogie.demomod.entity.hostile.DemoEntityHostile;
import net.woogie.demomod.entity.tameable.ai.DemoEntityAIBeg;

public class DemoEntityTameable extends EntityTameable {

	private float headRotationCourse;
	private float headRotationCourseOld;
	private boolean isWet;
	private boolean isShaking;
	private float timeDemoEntityTameableIsShaking;
	private float prevTimeDemoEntityTameableIsShaking;

	public DemoEntityTameable(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 0.8F);
		((PathNavigateGround) this.getNavigator()).func_179690_a(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, Config.entityTameableAILeapAtTargetHeight));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, Config.entityTameableAIAttackOnCollideSpeed, true));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, Config.entityTameableAIFollowOwnerSpeed,
				Config.entityTameableAIFollowOwnerMaxDistance, Config.entityTameableAIFollowOwnerMinDistance));
		this.tasks.addTask(6, new EntityAIMate(this, Config.entityTameableAIMateMoveSpeed));
		this.tasks.addTask(7, new EntityAIWander(this, Config.entityTameableAIWanderSpeed));
		this.tasks.addTask(8, new DemoEntityAIBeg(this, Config.entityTameableAIBegDistance));
		this.tasks.addTask(9,
				new EntityAIWatchClosest(this, EntityPlayer.class, Config.entityTameableAIWatchClosestDistance));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, DemoEntityHostile.class, false));
		this.setTamed(false);
	}

	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(Config.entityTameableMovementSpeed);

		if (this.isTamed()) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
					.setBaseValue(Config.entityTameableMaxTamedHealth);
		} else {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(Config.entityTameableMaxWildHealth);
		}

		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(Config.entityTameableAttackDamage);
	}

	@Override
	public void setAttackTarget(EntityLivingBase p_70624_1_) {
		super.setAttackTarget(p_70624_1_);

		if (p_70624_1_ == null) {
			this.setAngry(false);
		} else if (!this.isTamed()) {
			this.setAngry(true);
		}
	}

	@Override
	protected void updateAITasks() {
		this.dataWatcher.updateObject(18, Float.valueOf(this.getHealth()));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(18, new Float(this.getHealth()));
		this.dataWatcher.addObject(19, new Byte((byte) 0));
		this.dataWatcher.addObject(20, new Byte((byte) EnumDyeColor.RED.getMetadata()));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
		tagCompound.setBoolean("Angry", this.isAngry());
		tagCompound.setByte("CollarColor", (byte) this.getCollarColor().getDyeDamage());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund) {
		super.readEntityFromNBT(tagCompund);
		this.setAngry(tagCompund.getBoolean("Angry"));

		if (tagCompund.hasKey("CollarColor", 99)) {
			this.setCollarColor(EnumDyeColor.byDyeDamage(tagCompund.getByte("CollarColor")));
		}
	}

	@Override
	protected Item getDropItem() {
		return Item.getItemById(-1);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!this.worldObj.isRemote && this.isWet && !this.isShaking && !this.hasPath() && this.onGround) {
			this.isShaking = true;
			this.timeDemoEntityTameableIsShaking = 0.0F;
			this.prevTimeDemoEntityTameableIsShaking = 0.0F;
			this.worldObj.setEntityState(this, (byte) 8);
		}

		if (!this.worldObj.isRemote && this.getAttackTarget() == null && this.isAngry()) {
			this.setAngry(false);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.headRotationCourseOld = this.headRotationCourse;

		if (this.func_70922_bv()) {
			this.headRotationCourse += (1.0F - this.headRotationCourse) * 0.4F;
		} else {
			this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
		}

		if (this.isWet()) {
			this.isWet = true;
			this.isShaking = false;
			this.timeDemoEntityTameableIsShaking = 0.0F;
			this.prevTimeDemoEntityTameableIsShaking = 0.0F;
		} else if ((this.isWet || this.isShaking) && this.isShaking) {

			// TODO: maybe don't user mob.wolf.shake here?
			if (this.timeDemoEntityTameableIsShaking == 0.0F) {
				this.playSound("mob.wolf.shake", this.getSoundVolume(),
						(this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			}

			this.prevTimeDemoEntityTameableIsShaking = this.timeDemoEntityTameableIsShaking;
			this.timeDemoEntityTameableIsShaking += 0.05F;

			if (this.prevTimeDemoEntityTameableIsShaking >= 2.0F) {
				this.isWet = false;
				this.isShaking = false;
				this.prevTimeDemoEntityTameableIsShaking = 0.0F;
				this.timeDemoEntityTameableIsShaking = 0.0F;
			}

			if (this.timeDemoEntityTameableIsShaking > 0.4F) {
				float f = (float) this.getEntityBoundingBox().minY;
				int i = (int) (MathHelper.sin((this.timeDemoEntityTameableIsShaking - 0.4F) * (float) Math.PI) * 7.0F);

				for (int j = 0; j < i; ++j) {
					float f1 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
					float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
					this.worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX + f1, f + 0.8F,
							this.posZ + f2, this.motionX, this.motionY, this.motionZ, new int[0]);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean isDemoEnitityTameableWet() {
		return this.isWet;
	}

	@SideOnly(Side.CLIENT)
	public float getShadingWhileWet(float p_70915_1_) {
		return 0.75F + (this.prevTimeDemoEntityTameableIsShaking
				+ (this.timeDemoEntityTameableIsShaking - this.prevTimeDemoEntityTameableIsShaking) * p_70915_1_) / 2.0F
				* 0.25F;
	}

	@SideOnly(Side.CLIENT)
	public float getShakeAngle(float p_70923_1_, float p_70923_2_) {
		float f2 = (this.prevTimeDemoEntityTameableIsShaking
				+ (this.timeDemoEntityTameableIsShaking - this.prevTimeDemoEntityTameableIsShaking) * p_70923_1_
				+ p_70923_2_) / 1.8F;

		if (f2 < 0.0F) {
			f2 = 0.0F;
		} else if (f2 > 1.0F) {
			f2 = 1.0F;
		}

		return MathHelper.sin(f2 * (float) Math.PI) * MathHelper.sin(f2 * (float) Math.PI * 11.0F) * 0.15F
				* (float) Math.PI;
	}

	@SideOnly(Side.CLIENT)
	public float getInterestedAngle(float p_70917_1_) {
		return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * p_70917_1_)
				* 0.15F * (float) Math.PI;
	}

	@Override
	public float getEyeHeight() {
		return this.height * 0.8F;
	}

	@Override
	public int getVerticalFaceSpeed() {
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		} else {
			Entity entity = source.getEntity();
			this.aiSit.setSitting(false);

			if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
				amount = (amount + 1.0F) / 2.0F;
			}

			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity p_70652_1_) {
		boolean flag = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this),
				((int) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue()));

		if (flag) {
			this.func_174815_a(this, p_70652_1_);
		}

		return flag;
	}

	@Override
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);

		if (tamed) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
					.setBaseValue(Config.entityTameableMaxTamedHealth);
		} else {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(Config.entityTameableMaxWildHealth);
		}

		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(Config.entityTameableAttackDamage);
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (this.isTamed()) {
			if (itemstack != null) {
				if (itemstack.getItem() instanceof ItemFood) {
					ItemFood itemfood = (ItemFood) itemstack.getItem();

					if (itemfood == DemoMod.demoFood
							&& this.dataWatcher.getWatchableObjectFloat(18) < Config.entityTameableMaxTamedHealth) {
						if (!player.capabilities.isCreativeMode) {
							--itemstack.stackSize;
						}

						this.heal(itemfood.getHealAmount(itemstack));

						if (itemstack.stackSize <= 0) {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
						}

						return true;
					}
				} else if (itemstack.getItem() == Items.dye) {
					EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(itemstack.getMetadata());

					if (enumdyecolor != this.getCollarColor()) {
						this.setCollarColor(enumdyecolor);

						if (!player.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
						}

						return true;
					}
				}
			}

			if (this.isOwner(player) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack)) {
				this.aiSit.setSitting(!this.isSitting());
				this.isJumping = false;
				this.navigator.clearPathEntity();
				this.setAttackTarget((EntityLivingBase) null);
			}
		} else if (itemstack != null && itemstack.getItem() == DemoMod.demoFood && !this.isAngry()) {
			if (!player.capabilities.isCreativeMode) {
				--itemstack.stackSize;
			}

			if (itemstack.stackSize <= 0) {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
			}

			if (!this.worldObj.isRemote) {
				if (this.rand.nextInt(3) == 0) {
					this.setTamed(true);
					this.navigator.clearPathEntity();
					this.setAttackTarget((EntityLivingBase) null);
					this.aiSit.setSitting(true);
					this.setHealth((float) Config.entityTameableMaxTamedHealth);
					this.setOwnerId(player.getUniqueID().toString());
					this.playTameEffect(true);
					this.worldObj.setEntityState(this, (byte) 7);
				} else {
					this.playTameEffect(false);
					this.worldObj.setEntityState(this, (byte) 6);
				}
			}

			return true;
		}

		return super.interact(player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte p_70103_1_) {
		if (p_70103_1_ == 8) {
			this.isShaking = true;
			this.timeDemoEntityTameableIsShaking = 0.0F;
			this.prevTimeDemoEntityTameableIsShaking = 0.0F;
		} else {
			super.handleHealthUpdate(p_70103_1_);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getTailRotation() {
		return this.isAngry() ? 1.5393804F
				: (this.isTamed()
						? (0.55F - (20.0F - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * (float) Math.PI
						: ((float) Math.PI / 5F));
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack == null ? false
				: (!(stack.getItem() instanceof ItemFood) ? false : (stack.getItem() == DemoMod.demoFood));
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 8;
	}

	public boolean isAngry() {
		return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
	}

	public void setAngry(boolean angry) {
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (angry) {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 2)));
		} else {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -3)));
		}
	}

	public EnumDyeColor getCollarColor() {
		return EnumDyeColor.byDyeDamage(this.dataWatcher.getWatchableObjectByte(20) & 15);
	}

	public void setCollarColor(EnumDyeColor collarcolor) {
		this.dataWatcher.updateObject(20, Byte.valueOf((byte) (collarcolor.getDyeDamage() & 15)));
	}

	@Override
	public DemoEntityTameable createChild(EntityAgeable ageable) {
		DemoEntityTameable demoEntityTameable = new DemoEntityTameable(this.worldObj);
		String s = this.getOwnerId();

		if (s != null && s.trim().length() > 0) {
			demoEntityTameable.setOwnerId(s);
			demoEntityTameable.setTamed(true);
		}

		return demoEntityTameable;
	}

	public void func_70918_i(boolean p_70918_1_) {
		if (p_70918_1_) {
			this.dataWatcher.updateObject(19, Byte.valueOf((byte) 1));
		} else {
			this.dataWatcher.updateObject(19, Byte.valueOf((byte) 0));
		}
	}

	@Override
	public boolean canMateWith(EntityAnimal otherAnimal) {
		if (otherAnimal == this) {
			return false;
		} else if (!this.isTamed()) {
			return false;
		} else if (!(otherAnimal instanceof DemoEntityTameable)) {
			return false;
		} else {
			DemoEntityTameable demoEntityTameable = (DemoEntityTameable) otherAnimal;
			return !demoEntityTameable.isTamed() ? false
					: (demoEntityTameable.isSitting() ? false : this.isInLove() && demoEntityTameable.isInLove());
		}
	}

	public boolean func_70922_bv() {
		return this.dataWatcher.getWatchableObjectByte(19) == 1;
	}

	@Override
	protected boolean canDespawn() {
		return !this.isTamed() && this.ticksExisted > 2400;
	}

	@Override
	public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
		if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
			if (p_142018_1_ instanceof DemoEntityTameable) {
				DemoEntityTameable demoEntityTameable = (DemoEntityTameable) p_142018_1_;

				if (demoEntityTameable.isTamed() && demoEntityTameable.getOwnerEntity() == p_142018_2_) {
					return false;
				}
			}

			return p_142018_1_ instanceof EntityPlayer && p_142018_2_ instanceof EntityPlayer
					&& !((EntityPlayer) p_142018_2_).canAttackPlayer((EntityPlayer) p_142018_1_) ? false
							: !(p_142018_1_ instanceof EntityHorse) || !((EntityHorse) p_142018_1_).isTame();
		} else {
			return false;
		}
	}

	@Override
	public boolean allowLeashing() {
		return !this.isAngry() && super.allowLeashing();
	}

}