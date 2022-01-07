package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class LatteChickenRenderFactory implements EntityRendererProvider<LatteChicken> {

    public static LatteChickenRenderFactory INSTANCE = new LatteChickenRenderFactory();

    @Override
    public EntityRenderer<LatteChicken> create(Context manager) {
        return new LatteChickenRenderer(manager);
    }
}
