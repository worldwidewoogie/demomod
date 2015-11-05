package net.woogie.demomod.entity.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.woogie.demomod.Config;

public class DemoRenderMob extends RenderLiving {

	public DemoRenderMob(RenderManager renderManager, ModelBase modelBase, float someFloat) {
		super(renderManager, modelBase, someFloat);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Config.MODID + ":textures/entity/" + Config.entityMobName + ".png");

	}

}
