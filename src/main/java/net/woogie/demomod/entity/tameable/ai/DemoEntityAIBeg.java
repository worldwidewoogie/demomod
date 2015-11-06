package net.woogie.demomod.entity.tameable.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.woogie.demomod.entity.tameable.DemoEntityTameable;

public class DemoEntityAIBeg extends EntityAIBase {
	private DemoEntityTameable theDemoEntityTameable;
	private EntityPlayer thePlayer;
	private World worldObject;
	private float minPlayerDistance;
	private int field_75384_e;
	private static final String __OBFID = "CL_00001576";

	public DemoEntityAIBeg(DemoEntityTameable p_i1617_1_, float p_i1617_2_) {
		this.theDemoEntityTameable = p_i1617_1_;
		this.worldObject = p_i1617_1_.worldObj;
		this.minPlayerDistance = p_i1617_2_;
		this.setMutexBits(2);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		this.thePlayer = this.worldObject.getClosestPlayerToEntity(this.theDemoEntityTameable,
				(double) this.minPlayerDistance);
		return this.thePlayer == null ? false : this.hasPlayerGotBoneInHand(this.thePlayer);
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
		return !this.thePlayer.isEntityAlive() ? false
				: (this.theDemoEntityTameable.getDistanceSqToEntity(
						this.thePlayer) > (double) (this.minPlayerDistance * this.minPlayerDistance) ? false
								: this.field_75384_e > 0 && this.hasPlayerGotBoneInHand(this.thePlayer));
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.theDemoEntityTameable.func_70918_i(true);
		this.field_75384_e = 40 + this.theDemoEntityTameable.getRNG().nextInt(40);
	}

	/**
	 * Resets the task
	 */
	public void resetTask() {
		this.theDemoEntityTameable.func_70918_i(false);
		this.thePlayer = null;
	}

	/**
	 * Updates the task
	 */
	public void updateTask() {
		this.theDemoEntityTameable.getLookHelper().setLookPosition(this.thePlayer.posX,
				this.thePlayer.posY + (double) this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F,
				(float) this.theDemoEntityTameable.getVerticalFaceSpeed());
		--this.field_75384_e;
	}

	/**
	 * Gets if the Player has the Bone in the hand.
	 */
	private boolean hasPlayerGotBoneInHand(EntityPlayer p_75382_1_) {
		ItemStack itemstack = p_75382_1_.inventory.getCurrentItem();
		return itemstack == null ? false
				: (!this.theDemoEntityTameable.isTamed() && itemstack.getItem() == Items.bone ? true
						: this.theDemoEntityTameable.isBreedingItem(itemstack));
	}
}