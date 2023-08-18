package com.idtech.entity.transformingfox;

import com.idtech.entity.transformingfox.CustomFoxRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.monster.Zombie;


public class CustomFoxRenderFactory implements EntityRendererProvider<Fox> {

    public static CustomFoxRenderFactory INSTANCE = new CustomFoxRenderFactory();

    @Override
    public EntityRenderer<Fox> create(Context manager) {
        return new CustomFoxRenderer(manager);
    }
}