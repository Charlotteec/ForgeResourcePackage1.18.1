package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class CustomDragonRenderer extends MobRenderer<CustomDragon, CustomDragonModel<CustomDragon>> {

    public CustomDragonRenderer(EntityRendererProvider.Context provider) {
        super(provider, new CustomDragonModel<>(provider.bakeLayer(CustomDragonModel.LAYER_LOCATION)), 0.5f);

    }

    @Override
    public ResourceLocation getTextureLocation(CustomDragon dragon) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/customdragon.png");
    }

}



