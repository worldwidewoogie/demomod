package net.woogie.demomod.entity.boss;

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
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.woogie.demomod.Config;
import net.woogie.demomod.DemoMod;
import net.woogie.demomod.entity.tameable.DemoEntityTameable;

public class DemoEntityBoss extends EntityMob implements IBossDisplayData {
	public DemoEntityBoss(World worldIn) {
		super(worldIn);
		this.experienceValue = Config.entityBossExperience;

		if (Config.entityBossCanSwim) {
			this.tasks.addTask(0, new EntityAISwimming(this));
		}

		// avoid DemoEntityTameable
		if (Config.entityBossAvoidDemoEntityTameable) {
			this.tasks.addTask(1, new EntityAIAvoidEntity(this, new Predicate() {
				public boolean func_179958_a(Entity p_179958_1_) {
					return p_179958_1_ instanceof DemoEntityTameable;
				}

				@Override
				public boolean apply(Object p_apply_1_) {
					return this.func_179958_a((Entity) p_apply_1_);
				}
			}, Config.entityBossAvoidDemoEntityTameableRange, Config.entityBossAvoidDemoEntityTameableFarSpeed,
					Config.entityBossAvoidDemoEntityTameableNearSpeed));
		}

		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, Config.entityBossAIAttackOnCollideSpeed, false));
		this.tasks.addTask(1, new EntityAIWander(this, Config.entityBossAIWanderSpeed));
		this.tasks.addTask(1,
				new EntityAIWatchClosest(this, EntityPlayer.class, Config.entityBossAIWatchClosestDistance));
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(Config.entityBossMaxHealth);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(Config.entityBossFollowRange);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
				.setBaseValue(Config.entityBossKnockbackResistance);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(Config.entityBossMovementSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(Config.entityBossAttackDamage);
	}

	@Override
	protected void dropFewItems(boolean recentlyHitByPlayer, int lootingLevel) {
		if (recentlyHitByPlayer) {
			this.dropItem(DemoMod.demoGiantSword, 1);
		}
	}
}