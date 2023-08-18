package com.idtech.entity.transformingfox;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class CakeFoxRenderFactory implements EntityRendererProvider<CakeFox> {

    public static CakeFoxRenderFactory INSTANCE = new CakeFoxRenderFactory();

    @Override
    public EntityRenderer<CakeFox> create(Context manager) {
        return new CakeFoxRenderer(manager);
    }
}