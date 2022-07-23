package com.idtech.entity;

import com.idtech.entity.projectiles.ExplosionProjectile;
import com.idtech.entity.projectiles.LaunchProjectile;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityMod {

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event){
        event.getRegistry().register(GrumboBoy.TYPE);
        event.getRegistry().register(LatteChicken.TYPE);
        event.getRegistry().register(EvilRabbit.TYPE);
        event.getRegistry().register(ZomboEntity.TYPE);

        event.getRegistry().register(ExplosionProjectile.TYPE);
    }
    @SubscribeEvent
    public static void registerEntityEggs(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(GrumboBoy.EGG);
        event.getRegistry().register(LatteChicken.EGG);
        event.getRegistry().register(EvilRabbit.EGG);
        event.getRegistry().register(ZomboEntity.EGG);

    }
    @SubscribeEvent
    public static void entityRenderers(final EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(GrumboBoy.TYPE, GrumboBoyRenderFactory.INSTANCE);
        event.registerEntityRenderer(LatteChicken.TYPE, LatteChickenRenderFactory.INSTANCE);
        event.registerEntityRenderer(EvilRabbit.TYPE, EvilRabbitRenderFactory.INSTANCE);
        event.registerEntityRenderer(ZomboEntity.TYPE, ZomboRenderFactory.INSTANCE);

        event.registerEntityRenderer(ExplosionProjectile.TYPE, (m) -> { return new ThrownItemRenderer<>(m, 1.0f, true);});
        event.registerEntityRenderer(LaunchProjectile.TYPE, (m) -> { return new ThrownItemRenderer<>(m, 1.0f, true);});

    }

    // this is different than in 1.16 but everything else is the same
    // I do think this makes more sense than the other way but alas change is usually hard.
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(GrumboBoy.TYPE, GrumboBoy.createAttributes().build());
        event.put(LatteChicken.TYPE, LatteChicken.createAttributes().build());
        event.put(EvilRabbit.TYPE, EvilRabbit.createAttributes().build());
        event.put(ZomboEntity.TYPE, ZomboEntity.createAttributes().build());
    }

}
