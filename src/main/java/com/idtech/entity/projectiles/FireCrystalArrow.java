package com.idtech.entity.projectiles;

import com.idtech.item.BombArrowItem;
import com.idtech.item.FireCrystalArrowItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class FireCrystalArrow extends AbstractArrow {

    public FireCrystalArrow(Level levelIn, LivingEntity entityIn) {
        super(EntityType.ARROW, entityIn, levelIn);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(FireCrystalArrowItem.INSTANCE);
    }

}
