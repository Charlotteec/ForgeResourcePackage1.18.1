package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class EvilRabbitRenderFactory implements EntityRendererProvider<EvilRabbit> {

    public static EvilRabbitRenderFactory INSTANCE = new EvilRabbitRenderFactory();

    @Override
    public EntityRenderer<EvilRabbit> create(Context manager) {
        return new EvilRabbitRenderer(manager);
    }
}