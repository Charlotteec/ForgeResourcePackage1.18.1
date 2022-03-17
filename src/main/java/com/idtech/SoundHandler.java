package com.idtech;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SoundHandler
{
    // Add sounds variables here
    public static SoundEvent test;
    public static SoundEvent mySound;

    static {
        test = addSoundInfo("test");
        mySound = addSoundInfo("mysound");
    }

    private static SoundEvent addSoundInfo(String soundPath)
    {
        ResourceLocation location = new ResourceLocation(BaseMod.MODID, soundPath);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(location);
        return event;
    }

    @SubscribeEvent
    public void registerSounds(RegistryEvent.Register<SoundEvent> event)
    {
        event.getRegistry().register(test);
        event.getRegistry().register(mySound);
    }
}
