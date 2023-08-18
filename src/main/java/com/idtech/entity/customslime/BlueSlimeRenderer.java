package com.idtech.entity.customslime;

import com.idtech.BaseMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Slime;

/* Mostly copied from SlimeRenderer */
/* There is a rather confusing mix of Slime, BlueSlime, LivingEntity etc. in the generics and methods here;
 * follow these exactly to avoid literal object-oriented hell */
public class BlueSlimeRenderer extends MobRenderer<Slime, SlimeModel<Slime>> {
    /** Make sure this file location and name is right */
    private static final ResourceLocation BLUE_SLIME_LOCATION = new ResourceLocation(BaseMod.MODID, "textures/entity/blueslime.png");

    public BlueSlimeRenderer(EntityRendererProvider.Context provider) {
        /* This bakes inner layer; outer layer is baked in BlueSlimeOuterLayer.java */
        super(provider, new BlueSlimeModel<>(provider.bakeLayer(BlueSlimeModel.INNER_LAYER_LOCATION)), 0.25F);
        this.addLayer(new BlueSlimeOuterLayer<>(this, provider.getModelSet()));
    }

    public void render(Slime p_115976_, float p_115977_, float p_115978_, PoseStack p_115979_, MultiBufferSource p_115980_, int p_115981_) {
        this.shadowRadius = 0.25F * (float)p_115976_.getSize();
        super.render(p_115976_, p_115977_, p_115978_, p_115979_, p_115980_, p_115981_);
    }

    protected void scale(Slime p_115983_, PoseStack p_115984_, float p_115985_) {
        float f = 0.999F;
        p_115984_.scale(0.999F, 0.999F, 0.999F);
        p_115984_.translate(0.0D, (double)0.001F, 0.0D);
        float f1 = (float)p_115983_.getSize();
        float f2 = Mth.lerp(p_115985_, p_115983_.oSquish, p_115983_.squish) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        p_115984_.scale(f3 * f1,  1.0F / f3 * f1, f3 * f1);
    }

    public ResourceLocation getTextureLocation(Slime p_115974_) {
        return BLUE_SLIME_LOCATION;
    }
}
