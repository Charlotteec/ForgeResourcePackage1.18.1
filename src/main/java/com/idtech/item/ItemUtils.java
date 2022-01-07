package com.idtech.item;

import com.idtech.BaseMod;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.tags.Tag;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Utilities for creating new item and doing things with item
 */
public class ItemUtils {

    /**
     * Build a basic item with no added functionality. Useful for crafting materials, drops, ingots, currency, anything
     * that doesn't need extra code.
     * @param name Item registry name. All lowercase, no spaces. e.g. "meteor_ingot"
     * @param group Item group (creative tab) the item will appear in.
     * @return The built item.
     */
    public static Item buildBasicItem(String name, CreativeModeTab group){
        return new Item(new Item.Properties().tab(group)).setRegistryName(BaseMod.MODID, name);
    }

    /**
     * Create a new food item to register a food and have it appear in the game
     * @param name the name of the food item for registry and textures
     * @param food the food item itself.
     * @return
     */
    public static Item buildFoodItem(String name, FoodProperties food){
        return new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(food)).setRegistryName(BaseMod.MODID, name);
    }


    /**
     *  Create a new armor material. Used for new armor sets that use custom materials.
     * @param nameIn the name of the material, can be whatever.
     * @param maxDamageFactorIn Multiplier for durability.
     * @param damageReductionAmountArrayIn Defense points for each armor slot.
     * @param enchantabilityIn Enchantability of armor with the item.
     * @param eqiupSoundIn Sound for equipping the armor.
     * @param toughnessIn How long it takes until the armor breaks.
     * @param knockbackResistanceIn A value for knockback resistance of the armor.
     * @param repairIngredientName A registry name of the ingredient needed to repair this tool e.g. "minecraft:stick"
     *      *                       or "examplemod:meteor_ingot".
     * @return the built Armor Material
     */
    public static ArmorMaterial buildArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent eqiupSoundIn,
                                                    float toughnessIn, float knockbackResistanceIn, String repairIngredientName){
        Supplier<Ingredient> ingredientSupplier = ()-> Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation(repairIngredientName)));
        return buildArmorMaterial(nameIn, maxDamageFactorIn, damageReductionAmountArrayIn, enchantabilityIn, eqiupSoundIn, toughnessIn, knockbackResistanceIn, ingredientSupplier);

    }

    /**
     *  Alternate method to create an armor material that uses Item Tags instead of a single repair ingredient.
     *  Create a new armor material. Used for new armor sets that use custom materials.
     * @param nameIn the name of the material, can be whatever.
     * @param maxDamageFactorIn Multiplier for durability.
     * @param damageReductionAmountArrayIn Defense points for each armor slot.
     * @param enchantabilityIn Enchantability of armor with the item.
     * @param eqiupSoundIn Sound for equipping the armor.
     * @param toughnessIn How long it takes until the armor breaks.
     * @param knockbackResistanceIn A value for knockback resistance of the armor.
     * @param itemTag an Item Tag indicating a group or type of item that can repair this armor material.
     * @return the built Armor Material
     */
    public static ArmorMaterial buildArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent eqiupSoundIn,
                                                   float toughnessIn, float knockbackResistanceIn, Tag<Item> itemTag){
        Supplier<Ingredient> ingredientSupplier = () -> Ingredient.of(itemTag);
        return buildArmorMaterial(nameIn, maxDamageFactorIn, damageReductionAmountArrayIn, enchantabilityIn, eqiupSoundIn, toughnessIn, knockbackResistanceIn, ingredientSupplier);

    }

    /**
     * Builds a new custom armor material. Modelled exactly after Vanilla Mincraft armor material code.
     * @param nameIn name of the material
     * @param maxDamageFactorIn the maximum damage for the armor
     * @param damageReductionAmountArrayIn the damage reduction that each piece of armor does in an array. In order helm, chest, legs, boots.
     * @param enchantabilityIn the enchantability factor of the armor.
     * @param eqiupSoundIn the sound that the armor makes when being equipped
     * @param toughnessIn any additional toughness of the armor
     * @param knockbackResistanceIn additional knockback resistance in the armor
     * @param repairIngredientIn ingredient used to repair the armor.
     * @return
     */
    private static ArmorMaterial buildArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent eqiupSoundIn,
                                                     float toughnessIn, float knockbackResistanceIn, Supplier<Ingredient> repairIngredientIn) {

        final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};

        return new ArmorMaterial() {

            final String name = nameIn;
            final int maxDamageFactor = maxDamageFactorIn;
            final int[] damageReductionAmountArray = damageReductionAmountArrayIn;
            final int enchantability = enchantabilityIn;
            final SoundEvent soundEvent = eqiupSoundIn;
            final float toughness = toughnessIn;
            final float knockbackResistance = knockbackResistanceIn;
            final LazyLoadedValue<Ingredient>  repairMaterial = new LazyLoadedValue<>(repairIngredientIn);
            ;


        public int getDurabilityForSlot(EquipmentSlot slotIn){
            return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
        }
        public int getDefenseForSlot(EquipmentSlot slotIn) {
                return this.damageReductionAmountArray[slotIn.getIndex()];
        }

        public int getEnchantmentValue () {
            return this.enchantability;
        }

        public SoundEvent getEquipSound() {
            return this.soundEvent;
        }
        @Override
        public Ingredient getRepairIngredient () {
            return this.repairMaterial.get();
        }

        @Override
        public String getName () {
            return this.name;
        }
        @Override
        public float getToughness () {
            return this.toughness;
        }
        @Override
        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }
    };

    }


}
