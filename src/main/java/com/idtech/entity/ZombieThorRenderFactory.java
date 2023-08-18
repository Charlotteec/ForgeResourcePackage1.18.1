package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Zombie;

public class ZombieThorRenderFactory implements EntityRendererProvider<Zombie> {
    public static ZombieThorRenderFactory INSTANCE = new ZombieThorRenderFactory();

    @Override
    public EntityRenderer<Zombie> create(Context manager) {
        return new ZombieThorRenderer(manager);
    }
}
