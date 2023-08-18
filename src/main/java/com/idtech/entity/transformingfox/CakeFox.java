package com.idtech.entity.transformingfox;

import com.idtech.BaseMod;
import com.idtech.entity.EntityUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/* I inherited from wolf instead of fox just so we can extend TamableAnimal and not
* have to worry about taming code too much; will have to override sounds etc. though.
* You are free to extend Fox if you don't want taming; and if you do that just don't
* copy in any of this code past the constructor since the rest makes taming work. */
public class CakeFox extends Wolf {
    public static EntityType<CakeFox> TYPE = (EntityType<CakeFox>)
            EntityType.Builder.of(CakeFox::new, MobCategory.AMBIENT).sized(0.6F, 1.95F).clientTrackingRange(8).build("cakefox").setRegistryName(BaseMod.MODID, "cakefox");
    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0xfef9f8 , 0x383737);

    public CakeFox(EntityType<? extends Wolf> entityIn, Level levelIn) {
        super(entityIn, levelIn);
        this.autoTame(null);
    }

    /** Copy in to automatically tame mob when transformed */
    public void autoTame(Player player) {
        if (player != null) {
            this.tame(player);
            this.navigation.stop();
            this.setTarget((LivingEntity)null);
            this.setOrderedToSit(true);
            this.level.broadcastEntityEvent(this, (byte)7);
            this.setSitting(true);
        }
    }

    /** From Kaupenjoe - copy in to make sure cake is the only thing it can
     * eat & breed with (if breedable/tamable) */
    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.getItem() == Items.CAKE;
    }

    /* If inheriting from wolf, make sure sounds still use fox's */
    @Override
    public void playAmbientSound() {
        SoundEvent soundevent = this.getAmbientSound();
        if (soundevent == SoundEvents.FOX_SCREECH) {
            this.playSound(soundevent, 2.0F, this.getVoicePitch());
        } else {
            super.playAmbientSound();
        }
    }

    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        if (this.isAngry()) {
            return SoundEvents.FOX_AGGRO;
        } else if (this.isSleeping()) {
            return SoundEvents.FOX_SLEEP;
        } else {
            if (!this.level.isDay() && this.random.nextFloat() < 0.1F) {
                List<Player> list = this.level.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(16.0D, 16.0D, 16.0D), EntitySelector.NO_SPECTATORS);
                if (list.isEmpty()) {
                    return SoundEvents.FOX_SCREECH;
                }
            }

            return SoundEvents.FOX_AMBIENT;
        }
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource p_28548_) {
        return SoundEvents.FOX_HURT;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.FOX_DEATH;
    }

    @Override
    public SoundEvent getEatingSound(ItemStack p_28540_) {
        return SoundEvents.FOX_EAT;
    }

    /* From Kaupenjoe - if inheriting from wolf, makes sure breeding produces the right species */
    @Override
    public Wolf getBreedOffspring(ServerLevel serverLevel, AgeableMob mob) {
        CakeFox cakeFox = TYPE.create(serverLevel);
        UUID uuid = this.getOwnerUUID();
        if (uuid != null) {
            cakeFox.setOwnerUUID(uuid);
            cakeFox.setTame(true);
        }

        // hopefully this works to make the animal a baby and thus render smol
        cakeFox.setAge(-24000);

        return cakeFox;
    }

    /* The rest is from Kaupenjoe - if inheriting from wolf, boilerplate stuff
    and allows sitting/standing data to be saved when quitting game */
    private static final EntityDataAccessor<Boolean> SITTING =
            SynchedEntityData.defineId(CakeFox.class, EntityDataSerializers.BOOLEAN);

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setSitting(tag.getBoolean("isSitting"));
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("isSitting", this.isSitting());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SITTING, false);
    }

    public void setSitting(boolean sitting) {
        this.entityData.set(SITTING, sitting);
        this.setOrderedToSit(sitting);
    }

    public boolean isSitting() {
        return this.entityData.get(SITTING);
    }
}
