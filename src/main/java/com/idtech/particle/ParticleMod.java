package com.idtech.particle;

import com.idtech.BaseMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ParticleMod {
    /* Create a new SimpleParticleType so we can change which item texture
    * it gets its color from; we want it to be the color of gel ore instead
    * of a slimeball for the blue slimes */
    /** File location here should point to the model file in
     * resources/assets/examplemod/particles */
    public static final SimpleParticleType ITEM_BLUE_SLIME = (SimpleParticleType) new SimpleParticleType(true).setRegistryName(BaseMod.MODID, "item_blue_slime");

    /* Registration just like ItemMod.java */
    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) {
        event.getRegistry().register(ITEM_BLUE_SLIME);
    }
}
