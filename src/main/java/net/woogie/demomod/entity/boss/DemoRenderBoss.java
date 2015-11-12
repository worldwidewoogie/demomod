package net.woogie.demomod.entity.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import net.woogie.demomod.Config;

public class DemoRenderBoss extends RenderLiving {

	public DemoRenderBoss(RenderManager renderManager, ModelBase modelBase, float someFloat) {
		super(renderManager, modelBase, someFloat);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Config.MODID + ":textures/entity/" + Config.entityBossName + ".png");

	}

	public void doRender(DemoEntityBoss entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		BossStatus.setBossStatus(entity, true);
		super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		this.doRender((DemoEntityBoss) entity, x, y, z, p_76986_8_, partialTicks);
	}

	@Override
	public void doRender(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		this.doRender((DemoEntityBoss) entity, x, y, z, p_76986_8_, partialTicks);
	}

}
