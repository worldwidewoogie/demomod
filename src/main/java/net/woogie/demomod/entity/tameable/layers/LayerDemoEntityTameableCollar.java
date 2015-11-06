package net.woogie.demomod.entity.tameable.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.woogie.demomod.Config;
import net.woogie.demomod.entity.tameable.DemoEntityTameable;
import net.woogie.demomod.entity.tameable.DemoRenderTameable;

@SideOnly(Side.CLIENT)
public class LayerDemoEntityTameableCollar implements LayerRenderer {
	private static final ResourceLocation field_177147_a = new ResourceLocation(
			Config.MODID + ":textures/entity/" + Config.entityTameableName + "_collar.png");
	private final DemoRenderTameable field_177146_b;

	public LayerDemoEntityTameableCollar(DemoRenderTameable p_i46104_1_) {
		this.field_177146_b = p_i46104_1_;
	}

	public void func_177145_a(DemoEntityTameable p_177145_1_, float p_177145_2_, float p_177145_3_, float p_177145_4_,
			float p_177145_5_, float p_177145_6_, float p_177145_7_, float p_177145_8_) {
		if (p_177145_1_.isTamed() && !p_177145_1_.isInvisible()) {
			this.field_177146_b.bindTexture(field_177147_a);
			EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(p_177145_1_.getCollarColor().getMetadata());
			float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
			GlStateManager.color(afloat[0], afloat[1], afloat[2]);
			this.field_177146_b.getMainModel().render(p_177145_1_, p_177145_2_, p_177145_3_, p_177145_5_, p_177145_6_,
					p_177145_7_, p_177145_8_);
		}
	}

	public boolean shouldCombineTextures() {
		return true;
	}

	public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_,
			float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
		this.func_177145_a((DemoEntityTameable) p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_,
				p_177141_6_, p_177141_7_, p_177141_8_);
	}
}