package com.idtech.enchantment;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class WitherTouchEnchantment extends Enchantment {

    // Set up Enchantment Instance
    public static Enchantment INSTANCE = new WitherTouchEnchantment(Rarity.UNCOMMON, EquipmentSlot.MAINHAND).setRegistryName("wither_touch");

    // Constructor
    protected WitherTouchEnchantment(Rarity rarityIn, EquipmentSlot... slots) {
        super(rarityIn, EnchantmentCategory.WEAPON, slots);
    }


    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinCost(int enchantmentLevel) {
        return 30 + 10 * (enchantmentLevel - 1);
    }
    public int getMaxCost(int enchantmentLevel) {
        return super.getMinCost(enchantmentLevel) + 50;
    }
    // Above are the minimum and maximum enchantibility levels needed on a weapon to enchant an item with Wither Touch enchantment
    // This currently works similar to the enchantability levels of efficiency, based on the current enchantment level of the player

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 4;
    }


    /**
     * Called whenever a mob is damaged with an item that has this enchantment on it.
     */
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        // When a weapon with this enchantment is used to hit a mob, it will give the mob the wither effect
        if (target instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)target;

            // Give the mob the Wither effect (higher level enchantments give longer durations)
            int i = level * 50;
            livingentity.addEffect(new MobEffectInstance(MobEffects.WITHER, i, 1));

        }
    }


}