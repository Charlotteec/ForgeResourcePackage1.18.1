package com.idtech.entity.customslime;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;

/* Mostly copied from SlimeOuterLayer */
/* There is a rather confusing mix of Slime, BlueSlime, LivingEntity etc. in the generics
and methods here; follow these exactly to avoid literal object-oriented hell */
public class BlueSlimeOuterLayer<T extends LivingEntity> extends RenderLayer<T, SlimeModel<T>> {
    private final EntityModel<T> model;

    public BlueSlimeOuterLayer(RenderLayerParent<T, SlimeModel<T>> p_174536_, EntityModelSet p_174537_) {
        super(p_174536_);
        /* This bakes the outer layer; inner layer is baked in BlueSlimeRenderer.java */
        this.model = new BlueSlimeModel<>(p_174537_.bakeLayer(BlueSlimeModel.OUTER_LAYER_LOCATION));
    }

    public void render(PoseStack p_117470_, MultiBufferSource p_117471_, int p_117472_, T p_117473_, float p_117474_, float p_117475_, float p_117476_, float p_117477_, float p_117478_, float p_117479_) {
        Minecraft minecraft = Minecraft.getInstance();
        boolean flag = minecraft.shouldEntityAppearGlowing(p_117473_) && p_117473_.isInvisible();
        if (!p_117473_.isInvisible() || flag) {
            VertexConsumer vertexconsumer;
            if (flag) {
                vertexconsumer = p_117471_.getBuffer(RenderType.outline(this.getTextureLocation(p_117473_)));
            } else {
                vertexconsumer = p_117471_.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(p_117473_)));
            }

            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(p_117473_, p_117474_, p_117475_, p_117476_);
            this.model.setupAnim(p_117473_, p_117474_, p_117475_, p_117477_, p_117478_, p_117479_);
            this.model.renderToBuffer(p_117470_, vertexconsumer, p_117472_, LivingEntityRenderer.getOverlayCoords(p_117473_, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
