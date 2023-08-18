/**
 * Steps for ZombieThor:
 *
 * 1. Make your Lightning Hammer and Zombo/Thor as usual, following all the steps on Gameplan.
 *     * Use the reference code here to equip the Zombo with a lightning hammer on spawn.
 *     * The reference code here also includes code to prevent damage from its own lightning, and makes it not burn in the day.
 * 2. Add a new method to LightningHammerItem.java that allows passing in of a non-player LivingEntity and also summons lightning at the Zombo's attack target instead of where it's looking (reference code in this repo).
 * 3. Create a custom behavior goal for Thor and copy the reference code from ZombieThorAttackGoal.java. This new goal simulates right-click by calling that new method in LightningHammerItem.java we made, and also prevents Thor from being limited to melee range with the lightning.
 * 4. Override addBehaviorGoals() in Thor (reference code also included) to use our new goal instead of the original ZombieAttackGoal.
 */

package com.idtech.entity;

import com.idtech.BaseMod;
import com.idtech.Utils;
import com.idtech.item.ItemUtils;
import com.idtech.item.LightningHammerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.lang.reflect.Field;

public class ZombieThor extends Zombie {
    public ZombieThor(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
        /* Equip lightning hammer on spawn */
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(LightningHammerItem.INSTANCE));
    }

    public static EntityType<ZombieThor> TYPE = (EntityType<ZombieThor>)
            EntityType.Builder.of(ZombieThor::new, MobCategory.MONSTER)
                    .build("zombiethor")
                    .setRegistryName(BaseMod.MODID, "zombiethor");

    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0xb00101, 0xacbf1f);

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE)
                .add(Attributes.MAX_HEALTH, 35.0)
                .add(Attributes.FOLLOW_RANGE, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23)
                .add(Attributes.ATTACK_DAMAGE, 5.0);
    }

    /* Override to add custom goal */
    protected void addBehaviourGoals() {
        // custom goal that allows zombie thor to simulate right-clicking on Mjolnir (calling thorUse() which mimics use() but for non-player parameter)
        this.goalSelector.addGoal(2, new ZombieThorAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    /* make it not burn in the day or hide from the sun */
    public boolean isSunBurnTick() {
        return false;
    }

    protected boolean isSunSensitive() {
        return false;
    }

    /* takes damage only from players so it doesn't kill itself with lightning */
    public boolean hurt(DamageSource damageSource, float damage) {
        if (damageSource.getEntity() != null && damageSource.getEntity().getType() == EntityType.PLAYER || damageSource == DamageSource.OUT_OF_WORLD) {
            return super.hurt(damageSource, damage);
        }

        return false;
    }

    /* custom goal that allows zombie thor to simulate right-clicking on Mjolnir
    (calling thorUse() which mimics use() but for non-player parameter and aiming
    at attack target) */
    class ZombieThorAttackGoal extends ZombieAttackGoal {
        // reflection is needed since this is a private variable in MeleeAttackGoal which ZombieAttackGoal extends
        // probably copy-paste this code in since it's not a concept we would cover, just an unfortunate necessity
        private static Field ticksUntilNextAttack;

        static {
            try {
                ticksUntilNextAttack = MeleeAttackGoal.class.getDeclaredField("ticksUntilNextAttack");
                ticksUntilNextAttack.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        public ZombieThorAttackGoal(Zombie zombie, double speedModifier, boolean followingTargetEvenIfNotSeen) {
            super(zombie, speedModifier, followingTargetEvenIfNotSeen);
        }

        /* can attack from further away too (removed check for distance
        so not limited to melee range with hammer) */
        @Override
        protected void checkAndPerformAttack(LivingEntity target, double distanceSqr) {
            try {
                if (ticksUntilNextAttack.getInt(this) <= 0) { // more finicky reflection stuff
                    this.resetAttackCooldown();
                    this.mob.swing(InteractionHand.MAIN_HAND);
                    Item weapon = this.mob.getMainHandItem().getItem();
                    if (weapon instanceof LightningHammerItem && target.getType() == EntityType.PLAYER) {
                        ((LightningHammerItem) weapon).thorUse(this.mob.getLevel(), this.mob, target, InteractionHand.MAIN_HAND); // make the thor zombie simulate right click
                    } else {
                        this.mob.doHurtTarget(target);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
