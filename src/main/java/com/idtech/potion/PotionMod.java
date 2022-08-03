package com.idtech.potion;

import com.idtech.BaseMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.event.RegistryEvent;

public class PotionMod {

    public static Potion CUSTOM_POTION = new Potion(new MobEffectInstance(CustomEffect.INSTANCE, 200, 1000))
            .setRegistryName(BaseMod.MODID, "custompotion");

    public static void registerPotions(RegistryEvent.Register<Potion> event) {

        event.getRegistry().register(CUSTOM_POTION);
    }

    public static void registerEffects(RegistryEvent.Register<MobEffect> event){
        event.getRegistry().register(CustomEffect.INSTANCE);
    }

}
