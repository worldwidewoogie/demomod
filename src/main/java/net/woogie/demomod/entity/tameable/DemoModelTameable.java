package net.woogie.demomod.entity.tameable;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DemoModelTameable extends ModelBase {
	public ModelRenderer headMane;
	public ModelRenderer body;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer leg3;
	public ModelRenderer leg4;
	ModelRenderer tail;
	ModelRenderer mane;
	private static final String __OBFID = "CL_00000868";

	public DemoModelTameable()
    {
        float f = 0.0F;
        float f1 = 13.5F;
        this.headMane = new ModelRenderer(this, 0, 0);
        this.headMane.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 4, f);
        this.headMane.setRotationPoint(-1.0F, f1, -7.0F);
        this.body = new ModelRenderer(this, 18, 14);
        this.body.addBox(-4.0F, -2.0F, -3.0F, 6, 9, 6, f);
        this.body.setRotationPoint(0.0F, 14.0F, 2.0F);
        this.mane = new ModelRenderer(this, 21, 0);
        this.mane.addBox(-4.0F, -3.0F, -3.0F, 8, 6, 7, f);
        this.mane.setRotationPoint(-1.0F, 14.0F, 2.0F);
        this.leg1 = new ModelRenderer(this, 0, 18);
        this.leg1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.leg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
        this.leg2 = new ModelRenderer(this, 0, 18);
        this.leg2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.leg2.setRotationPoint(0.5F, 16.0F, 7.0F);
        this.leg3 = new ModelRenderer(this, 0, 18);
        this.leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.leg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
        this.leg4 = new ModelRenderer(this, 0, 18);
        this.leg4.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.leg4.setRotationPoint(0.5F, 16.0F, -4.0F);
        this.tail = new ModelRenderer(this, 9, 18);
        this.tail.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.tail.setRotationPoint(-1.0F, 12.0F, 8.0F);
        this.headMane.setTextureOffset(16, 14).addBox(-3.0F, -5.0F, 0.0F, 2, 2, 1, f);
        this.headMane.setTextureOffset(16, 14).addBox(1.0F, -5.0F, 0.0F, 2, 2, 1, f);
        this.headMane.setTextureOffset(0, 10).addBox(-1.5F, 0.0F, -5.0F, 3, 3, 4, f);
    }

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_,
			float p_78088_6_, float p_78088_7_) {
		super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
		this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

		if (this.isChild) {
			float f6 = 2.0F;
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, 5.0F * p_78088_7_, 2.0F * p_78088_7_);
			this.headMane.renderWithRotation(p_78088_7_);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(1.0F / f6, 1.0F / f6, 1.0F / f6);
			GlStateManager.translate(0.0F, 24.0F * p_78088_7_, 0.0F);
			this.body.render(p_78088_7_);
			this.leg1.render(p_78088_7_);
			this.leg2.render(p_78088_7_);
			this.leg3.render(p_78088_7_);
			this.leg4.render(p_78088_7_);
			this.tail.renderWithRotation(p_78088_7_);
			this.mane.render(p_78088_7_);
			GlStateManager.popMatrix();
		} else {
			this.headMane.renderWithRotation(p_78088_7_);
			this.body.render(p_78088_7_);
			this.leg1.render(p_78088_7_);
			this.leg2.render(p_78088_7_);
			this.leg3.render(p_78088_7_);
			this.leg4.render(p_78088_7_);
			this.tail.renderWithRotation(p_78088_7_);
			this.mane.render(p_78088_7_);
		}
	}

	/**
	 * Used for easily adding entity-dependent animations. The second and third
	 * float params here are the same second and third as in the
	 * setRotationAngles method.
	 */
	public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
		DemoEntityTameable demoEntityTameable = (DemoEntityTameable) p_78086_1_;

		if (demoEntityTameable.isAngry()) {
			this.tail.rotateAngleY = 0.0F;
		} else {
			this.tail.rotateAngleY = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
		}

		if (demoEntityTameable.isSitting()) {
			this.mane.setRotationPoint(-1.0F, 16.0F, -3.0F);
			this.mane.rotateAngleX = ((float) Math.PI * 2F / 5F);
			this.mane.rotateAngleY = 0.0F;
			this.body.setRotationPoint(0.0F, 18.0F, 0.0F);
			this.body.rotateAngleX = ((float) Math.PI / 4F);
			this.tail.setRotationPoint(-1.0F, 21.0F, 6.0F);
			this.leg1.setRotationPoint(-2.5F, 22.0F, 2.0F);
			this.leg1.rotateAngleX = ((float) Math.PI * 3F / 2F);
			this.leg2.setRotationPoint(0.5F, 22.0F, 2.0F);
			this.leg2.rotateAngleX = ((float) Math.PI * 3F / 2F);
			this.leg3.rotateAngleX = 5.811947F;
			this.leg3.setRotationPoint(-2.49F, 17.0F, -4.0F);
			this.leg4.rotateAngleX = 5.811947F;
			this.leg4.setRotationPoint(0.51F, 17.0F, -4.0F);
		} else {
			this.body.setRotationPoint(0.0F, 14.0F, 2.0F);
			this.body.rotateAngleX = ((float) Math.PI / 2F);
			this.mane.setRotationPoint(-1.0F, 14.0F, -3.0F);
			this.mane.rotateAngleX = this.body.rotateAngleX;
			this.tail.setRotationPoint(-1.0F, 12.0F, 8.0F);
			this.leg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
			this.leg2.setRotationPoint(0.5F, 16.0F, 7.0F);
			this.leg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
			this.leg4.setRotationPoint(0.5F, 16.0F, -4.0F);
			this.leg1.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
			this.leg2.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float) Math.PI) * 1.4F * p_78086_3_;
			this.leg3.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float) Math.PI) * 1.4F * p_78086_3_;
			this.leg4.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
		}

		this.headMane.rotateAngleZ = demoEntityTameable.getInterestedAngle(p_78086_4_)
				+ demoEntityTameable.getShakeAngle(p_78086_4_, 0.0F);
		this.mane.rotateAngleZ = demoEntityTameable.getShakeAngle(p_78086_4_, -0.08F);
		this.body.rotateAngleZ = demoEntityTameable.getShakeAngle(p_78086_4_, -0.16F);
		this.tail.rotateAngleZ = demoEntityTameable.getShakeAngle(p_78086_4_, -0.2F);
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2 are
	 * used for animating the movement of arms and legs, where par1 represents
	 * the time(so that arms and legs swing back and forth) and par2 represents
	 * how "far" arms and legs can swing at most.
	 */
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_,
			float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
		super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
		this.headMane.rotateAngleX = p_78087_5_ / (180F / (float) Math.PI);
		this.headMane.rotateAngleY = p_78087_4_ / (180F / (float) Math.PI);
		this.tail.rotateAngleX = p_78087_3_;
	}
}