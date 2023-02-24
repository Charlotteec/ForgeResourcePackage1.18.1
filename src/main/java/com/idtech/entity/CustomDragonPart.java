package com.idtech.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;

public class CustomDragonPart extends net.minecraftforge.entity.PartEntity<CustomDragon> {

    public final CustomDragon parentMob;
    public final String name;
    private final EntityDimensions size;

    public CustomDragonPart(CustomDragon p_31014_, String p_31015_, float p_31016_, float p_31017_) {
        super(p_31014_);
        this.size = EntityDimensions.scalable(p_31016_, p_31017_);
        this.refreshDimensions();
        this.parentMob = p_31014_;
        this.name = p_31015_;
    }


    protected void defineSynchedData() {
    }

    protected void readAdditionalSaveData(CompoundTag p_31025_) {
    }

    protected void addAdditionalSaveData(CompoundTag p_31028_) {
    }

    public boolean isPickable() {
        return true;
    }

//    public boolean hurt(DamageSource p_31020_, float p_31021_) {
//        return this.isInvulnerableTo(p_31020_) ? false : this.parentMob.hurt(this, p_31020_, p_31021_);
//    }

    public boolean is(Entity p_31031_) {
        return this == p_31031_ || this.parentMob == p_31031_;
    }

    public Packet<?> getAddEntityPacket() {
        throw new UnsupportedOperationException();
    }

    public EntityDimensions getDimensions(Pose p_31023_) {
        return this.size;
    }

    public boolean shouldBeSaved() {
        return false;
    }
}
