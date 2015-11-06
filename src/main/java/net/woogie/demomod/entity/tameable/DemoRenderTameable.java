package net.woogie.demomod.entity.tameable;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.woogie.demomod.Config;
import net.woogie.demomod.entity.tameable.layers.LayerDemoEntityTameableCollar;

@SideOnly(Side.CLIENT)
public class DemoRenderTameable extends RenderLiving {
	private static final ResourceLocation textures = new ResourceLocation(
			Config.MODID + ":textures/entity/" + Config.entityTameableName + ".png");
	private static final ResourceLocation tamedTextures = new ResourceLocation(
			Config.MODID + ":textures/entity/" + Config.entityTameableName + "_tame.png");
	private static final ResourceLocation anrgyTextures = new ResourceLocation(
			Config.MODID + ":textures/entity/" + Config.entityTameableName + "_angry.png");

	public DemoRenderTameable(RenderManager p_i46128_1_, ModelBase p_i46128_2_, float p_i46128_3_) {
		super(p_i46128_1_, p_i46128_2_, p_i46128_3_);
		this.addLayer(new LayerDemoEntityTameableCollar(this));
	}

	protected float func_180593_a(DemoEntityTameable p_180593_1_, float p_180593_2_) {
		return p_180593_1_.getTailRotation();
	}

	public void func_177135_a(DemoEntityTameable p_177135_1_, double p_177135_2_, double p_177135_4_,
			double p_177135_6_, float p_177135_8_, float p_177135_9_) {
		if (p_177135_1_.isWet()) {
			float f2 = p_177135_1_.getBrightness(p_177135_9_) * p_177135_1_.getShadingWhileWet(p_177135_9_);
			GlStateManager.color(f2, f2, f2);
		}

		super.doRender((EntityLiving) p_177135_1_, p_177135_2_, p_177135_4_, p_177135_6_, p_177135_8_, p_177135_9_);
	}

	protected ResourceLocation getEntityTexture(DemoEntityTameable entity) {
		return entity.isTamed() ? tamedTextures : (entity.isAngry() ? anrgyTextures : textures);
	}

	public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		this.func_177135_a((DemoEntityTameable) entity, x, y, z, p_76986_8_, partialTicks);
	}

	protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_) {
		return this.func_180593_a((DemoEntityTameable) p_77044_1_, p_77044_2_);
	}

	public void doRender(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		this.func_177135_a((DemoEntityTameable) entity, x, y, z, p_76986_8_, partialTicks);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((DemoEntityTameable) entity);
	}

	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		this.func_177135_a((DemoEntityTameable) entity, x, y, z, p_76986_8_, partialTicks);
	}
}