/*
    Instructors Note: This code is a fair bit complex, especially for group 2s. Consider
    copy/pasting the raycast code from the file for students.

    KNOWN ISSUES:
    - Laser staff does not affect ender dragon or end crystals
    TODOs:
    - Add effect for the laser, so players can see a 'beam' when firing
    - Random spread value for the raycast
 */

package com.idtech.item;

import com.idtech.ModTab;
import com.idtech.entity.projectiles.ExplosionProjectile;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.CheckForNull;
import java.util.Optional;
import java.util.function.Predicate;

public class LaserStaffItem extends Item {
    private static Item.Properties properties = new Item.Properties().tab(ModTab.INSTANCE);
    public static Item INSTANCE = new LaserStaffItem(properties).setRegistryName("laserstaff");

    public LaserStaffItem(Item.Properties properties) {
        super(properties);
    }

    // Variables
    int weaponRange = 100;
    int weaponDamage = 4;
    float weaponKnock = 0.2f;

    // Raycasting
    @CheckForNull
    public static EntityHitResult getEntityLookingAt(Player player, double range)
    {
        return getEntityLookingAt(player, range, 1.0F);
    }

    @CheckForNull
    public static EntityHitResult getEntityLookingAt(Player player, double range, float ticks) {
        Level world = player.level;

        Vec3 look = player.getLookAngle();
        Vec3 start = player.getEyePosition(ticks);

        Vec3 end = new Vec3(player.getX() + look.x * range, player.getEyeY() + look.y * range, player.getZ() + look.z * range);
        ClipContext context = new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player);

        HitResult rayTraceResult = world.clip(context);
        double traceDistance = rayTraceResult.getLocation().distanceToSqr(start);

        AABB playerBox = player.getBoundingBox().expandTowards(look.scale(traceDistance)).expandTowards(1.0D, 1.0D, 1.0D);

        Predicate<Entity> filter = entity -> !entity.isSpectator() && entity.isPickable() && entity instanceof LivingEntity;
        for (Entity possible : world.getEntities(player, playerBox, filter)) {
            AABB entityBox = possible.getBoundingBox().inflate(0.3D);
            Optional<Vec3> optional = entityBox.clip(start, end);
            if (optional.isPresent()) {
                Vec3 position = optional.get();
                double distance = start.distanceToSqr(position);

                if (distance < traceDistance) {
                    return new EntityHitResult(possible, position);
                }
            }
        }
        return null;
    }

    @CheckForNull
    public static EntityHitResult traceToEntity(Player player, Entity target)
    {
        return traceToEntity(player, target, 1.0F);
    }

    @CheckForNull
    public static EntityHitResult traceToEntity(Player player, Entity target, float ticks) {
        Vec3 start = player.getEyePosition(ticks);
        Vec3 end = target.position();

        AABB targetBox = target.getBoundingBox().inflate(0.3D);
        Optional<Vec3> optional = targetBox.clip(start, end);

        return optional.map(vector3d -> new EntityHitResult(target, vector3d)).orElse(null);
    }

    // Interact action
    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {

        ItemStack itemstack = playerIn.getItemInHand(handIn);

        // Make a ray cast
        EntityHitResult result = getEntityLookingAt(playerIn, this.weaponRange);
        // If the raycast hits...
        if (result != null) {
            Entity target = result.getEntity();
            if (target instanceof LivingEntity livingEntity) {

                DamageSource damageSource = DamageSource.playerAttack(playerIn);
                playerIn.setLastHurtMob(livingEntity);

                double ratioX = Mth.sin(playerIn.getYRot() * ((float) Math.PI / 180F));
                double ratioZ = -Mth.cos(playerIn.getYRot() * ((float) Math.PI / 180F));

                livingEntity.knockback(weaponKnock, ratioX, ratioZ);
                livingEntity.hurt(damageSource, weaponDamage);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemstack, levelIn.isClientSide());

    }
}

