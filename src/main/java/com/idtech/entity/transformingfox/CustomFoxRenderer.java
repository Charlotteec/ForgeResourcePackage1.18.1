package com.idtech.entity.transformingfox;

import com.idtech.BaseMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Fox;

public class CustomFoxRenderer extends FoxRenderer {
    public CustomFoxRenderer(EntityRendererProvider.Context provider) {
        super(provider);
    }

    @Override
    public ResourceLocation getTextureLocation(Fox entity) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/customfox.png");
    }
}
