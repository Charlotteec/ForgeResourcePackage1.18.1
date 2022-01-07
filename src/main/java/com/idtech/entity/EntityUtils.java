package com.idtech.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class EntityUtils {


    /**
     *  Returns a spawn egg item
     * @param type
     * @param primaryColor
     * @param secondaryColor
     * @return the spawn egg item with name and colors set.
     */
    public static Item buildEntitySpawnEgg(EntityType type, int primaryColor, int secondaryColor){
        return new SpawnEggItem(type, primaryColor, secondaryColor, new Item.Properties().tab(CreativeModeTab.TAB_MISC)).setRegistryName(type.getRegistryName() + "_egg");
    }


}
