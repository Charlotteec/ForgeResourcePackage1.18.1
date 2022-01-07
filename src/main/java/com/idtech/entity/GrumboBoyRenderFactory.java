package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Zombie;


public class GrumboBoyRenderFactory implements EntityRendererProvider<Zombie> {

    public static GrumboBoyRenderFactory INSTANCE = new GrumboBoyRenderFactory();

    @Override
    public EntityRenderer<Zombie> create(Context manager) {
        return new GrumboBoyRenderer(manager);
    }
}