package net.woogie.demomod.entity.hostile;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;
import net.woogie.demomod.entity.tameable.DemoEntityTameable;

public class DemoEntityHostile extends EntityMob {
	public DemoEntityHostile(World worldIn) {
		super(worldIn);

		this.experienceValue = Config.entityHostileExperience;

		if (Config.entityHostileCanSwim) {
			this.tasks.addTask(0, new EntityAISwimming(this));
		}

		// avoid DemoEntityTameable
		if (Config.entityHostileAvoidDemoEntityTameable) {
			this.tasks.addTask(1, new EntityAIAvoidEntity(this, new Predicate() {
				public boolean func_179958_a(Entity p_179958_1_) {
					return p_179958_1_ instanceof DemoEntityTameable;
				}

				@Override
				public boolean apply(Object p_apply_1_) {
					return this.func_179958_a((Entity) p_apply_1_);
				}
			}, Config.entityHostileAvoidDemoEntityTameableRange, Config.entityHostileAvoidDemoEntityTameableFarSpeed,
					Config.entityHostileAvoidDemoEntityTameableNearSpeed));
		}

		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, Config.entityHostileAIAttackOnCollideSpeed, false));
		this.tasks.addTask(1, new EntityAIWander(this, Config.entityHostileAIWanderSpeed));
		this.tasks.addTask(1,
				new EntityAIWatchClosest(this, EntityPlayer.class, Config.entityHostileAIWatchClosestDistance));
		this.tasks.addTask(1, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
	}

	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(Config.entityHostileMaxHealth);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(Config.entityHostileFollowRange);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
				.setBaseValue(Config.entityHostileKnockbackResistance);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(Config.entityHostileMovementSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(Config.entityHostileAttackDamage);
	}

	@Override
	protected void dropFewItems(boolean recentlyHitByPlayer, int lootingLevel) {
		if (recentlyHitByPlayer) {

			int j = this.rand.nextInt(Config.entityHostileDropBonus + lootingLevel) + Config.entityHostileBaseDrops;

			for (int k = 0; k < j; ++k) {
				this.dropItem(DemoMod.demoItem, 1);
			}
		}
	}
}