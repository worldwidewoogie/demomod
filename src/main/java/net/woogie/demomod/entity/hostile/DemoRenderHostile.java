package net.woogie.demomod.entity.hostile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.woogie.demomod.Config;

public class DemoRenderHostile extends RenderLiving {

	public DemoRenderHostile(RenderManager renderManager, ModelBase modelBase, float someFloat) {
		super(renderManager, modelBase, someFloat);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Config.MODID + ":textures/entity/" + Config.entityHostileName + ".png");

	}

}
