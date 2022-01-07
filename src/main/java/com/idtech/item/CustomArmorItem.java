package com.idtech.item;

import com.idtech.BaseMod;
import com.idtech.ModTab;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;

public class CustomArmorItem extends ArmorItem {
    private static Properties properties = new Properties().tab(ModTab.INSTANCE);

//creating a custom armor material - this is almost identical to the old way
    private static ArmorMaterial customMaterial = ItemUtils.buildArmorMaterial("gelore", 22, new int[]{10,10,10,10} ,5,
            SoundEvents.ARMOR_EQUIP_IRON, 4.0f, 0.3f,"examplemod:gelore");

    //create instances for each piece of armor
    public static final Item CUSTOM_HELM = new CustomArmorItem(customMaterial, EquipmentSlot.HEAD, (properties)).setRegistryName(BaseMod.MODID, "custom_helm");
    public static final Item CUSTOM_CHEST = new CustomArmorItem(customMaterial, EquipmentSlot.CHEST, (properties)).setRegistryName(BaseMod.MODID, "custom_chest");
    public static final Item CUSTOM_LEGS = new CustomArmorItem(customMaterial, EquipmentSlot.LEGS, (properties)).setRegistryName(BaseMod.MODID, "custom_legs");
    public static final Item CUSTOM_BOOTS = new CustomArmorItem(customMaterial, EquipmentSlot.FEET, (properties)).setRegistryName(BaseMod.MODID, "custom_boots");

//constructor
    public CustomArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
    }

    //same as always
    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if(slot == EquipmentSlot.LEGS){
            return "examplemod:textures/models/armor/custom_armor_layer_2.png";
        }else{
            return "examplemod:textures/models/armor/custom_armor_layer_1.png";
        }
    }
}
