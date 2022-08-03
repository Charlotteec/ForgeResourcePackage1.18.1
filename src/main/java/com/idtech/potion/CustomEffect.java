package com.idtech.potion;

import com.idtech.BaseMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.extensions.IForgeMobEffect;

public class CustomEffect extends MobEffect implements IForgeMobEffect {

    public static MobEffect INSTANCE = new CustomEffect(MobEffectCategory.HARMFUL, 8356723)
            .setRegistryName(BaseMod.MODID, "customeffect");
    protected CustomEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int p_19468_) {
        super.applyEffectTick(entity, p_19468_);
        entity.setSecondsOnFire(3);
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
