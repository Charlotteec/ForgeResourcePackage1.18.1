package com.idtech.particle;

import com.idtech.BaseMod;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BaseMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterParticleFactories {
    /* This is what ParticleEngine.java does in the vanilla code;
    basically links our new SimpleParticleType to its visual texture
    reference (gel ore) that we set in BlueSlimeParticle.BlueSlimeProvider */
    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleMod.ITEM_BLUE_SLIME,
                BlueSlimeParticle.Provider::new);
    }
}
