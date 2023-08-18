package com.idtech.entity;

import com.idtech.entity.customslime.BlueSlimeEntity;
import com.idtech.entity.customslime.BlueSlimeRenderer;
import com.idtech.entity.projectiles.ExplosionProjectile;
import com.idtech.entity.projectiles.LaunchProjectile;
import com.idtech.entity.transformingfox.CakeFox;
import com.idtech.entity.transformingfox.CakeFoxRenderFactory;
import com.idtech.entity.transformingfox.CustomFox;
import com.idtech.entity.transformingfox.CustomFoxRenderFactory;
import com.idtech.entity.zombiethor.ZombieThor;
import com.idtech.entity.zombiethor.ZombieThorRenderFactory;
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
        event.getRegistry().register(CustomDragon.TYPE);
        event.getRegistry().register(ZombieThor.TYPE);
        event.getRegistry().register(CustomFox.TYPE);
        event.getRegistry().register(CakeFox.TYPE);
        event.getRegistry().register(BlueSlimeEntity.TYPE);

        event.getRegistry().register(ExplosionProjectile.TYPE);
    }
    @SubscribeEvent
    public static void registerEntityEggs(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(GrumboBoy.EGG);
        event.getRegistry().register(LatteChicken.EGG);
        event.getRegistry().register(EvilRabbit.EGG);
        event.getRegistry().register(ZomboEntity.EGG);
        event.getRegistry().register(ZombieThor.EGG);
        event.getRegistry().register(CakeFox.EGG);
        event.getRegistry().register(BlueSlimeEntity.EGG);

        event.getRegistry().register(CustomDragon.EGG);

    }
    @SubscribeEvent
    public static void entityRenderers(final EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(GrumboBoy.TYPE, GrumboBoyRenderFactory.INSTANCE);
        event.registerEntityRenderer(LatteChicken.TYPE, LatteChickenRenderFactory.INSTANCE);
        event.registerEntityRenderer(EvilRabbit.TYPE, EvilRabbitRenderFactory.INSTANCE);
        event.registerEntityRenderer(ZomboEntity.TYPE, ZomboRenderFactory.INSTANCE);
        event.registerEntityRenderer(CustomDragon.TYPE, CustomDragonRenderFactory.INSTANCE);
        event.registerEntityRenderer(ZombieThor.TYPE, ZombieThorRenderFactory.INSTANCE);
        event.registerEntityRenderer(CustomFox.TYPE, CustomFoxRenderFactory.INSTANCE);
        event.registerEntityRenderer(CakeFox.TYPE, CakeFoxRenderFactory.INSTANCE);
        /* Use ::new instead of render factory for custom slime
        to avoid issues with casting in render factory */
        event.registerEntityRenderer(BlueSlimeEntity.TYPE, BlueSlimeRenderer::new);

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
        event.put(CustomDragon.TYPE, CustomDragon.createAttributes().build());
        event.put(ZombieThor.TYPE, ZombieThor.createAttributes().build());
        event.put(CustomFox.TYPE, CustomFox.createAttributes().build());
        event.put(CakeFox.TYPE, CakeFox.createAttributes().build());
        event.put(BlueSlimeEntity.TYPE, BlueSlimeEntity.createAttributes().build());
    }

}
