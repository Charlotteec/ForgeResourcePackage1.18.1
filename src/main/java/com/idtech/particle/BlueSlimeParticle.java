package com.idtech.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/* Mostly copied from BreakingItemParticle.java */
public class BlueSlimeParticle extends TextureSheetParticle {

    protected BlueSlimeParticle(ClientLevel level, double xCoord, double yCoord, double zCoord,
                               SpriteSet spriteSet, double xd, double yd, double zd) {
        /* This is how BreakingItemParticle.java initializes stuff; since we can't seem
        * to override it (because of how the constructor's setSprite() stuff works),
        * we must imitate its behavior as much as possible */
        super(level, xCoord, yCoord, zCoord, 0.0, 0.0, 0.0);
        this.xd *= 0.1;
        this.yd *= 0.1;
        this.zd *= 0.1;
        this.xd += xd;
        this.yd += yd;
        this.zd += zd;

        /* Not in BreakingItemParticle.java (which uses setSprite)
        * but needed to work according to Kaupenjoe */
        this.setSpriteFromAge(spriteSet);

        /* Make sure colors show correctly, according to Kaupenjoe */
        this.rCol = 1F;
        this.gCol = 1F;
        this.bCol = 1F;
    }

    /* Unfortunately using ParticleRenderType.TERRAIN_SHEET like
    BreakingItemParticle.java doesn't work */
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new BlueSlimeParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
