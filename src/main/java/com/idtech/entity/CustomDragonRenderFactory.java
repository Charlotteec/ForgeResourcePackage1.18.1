package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class CustomDragonRenderFactory implements EntityRendererProvider<CustomDragon> {

    public static CustomDragonRenderFactory INSTANCE = new CustomDragonRenderFactory();

    @Override
    public EntityRenderer<CustomDragon> create(Context manager) {
        return new CustomDragonRenderer(manager);
    }
}
