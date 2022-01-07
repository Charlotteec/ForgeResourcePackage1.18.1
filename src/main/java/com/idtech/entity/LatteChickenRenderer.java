package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LatteChickenRenderer extends MobRenderer<LatteChicken, LatteChickenModel<LatteChicken>> {


    public LatteChickenRenderer(EntityRendererProvider.Context provider) {
        super(provider, new LatteChickenModel<>(provider.bakeLayer(ModelLayers.CHICKEN)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(LatteChicken entity) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/lattechicken.png");
    }

}
