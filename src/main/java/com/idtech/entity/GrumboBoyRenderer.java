package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class GrumboBoyRenderer extends ZombieRenderer {

    //at some point figure out the better way of doing rendering i think there is a better way but also maybe there isnt
    // next entity test is to make a monster with a pig model or chicken model or something

    public GrumboBoyRenderer(EntityRendererProvider.Context provider) {
        super(provider);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie entity) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/zombo.png");
    }

}
