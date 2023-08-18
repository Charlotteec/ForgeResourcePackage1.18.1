package com.idtech.entity.transformingfox;

import com.idtech.BaseMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CakeFoxRenderer extends MobRenderer<CakeFox, CakeFoxModel<CakeFox>> {
    public CakeFoxRenderer(EntityRendererProvider.Context provider) {
        super(provider, new CakeFoxModel<>(provider.bakeLayer(CakeFoxModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(CakeFox p_114482_) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/cakefox.png");
    }
}
