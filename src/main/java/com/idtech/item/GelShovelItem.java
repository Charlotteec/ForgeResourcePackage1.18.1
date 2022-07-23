package com.idtech.item;

import com.idtech.ModTab;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class GelShovelItem extends ShovelItem {
    public static Item INSTANCE = new GelShovelItem(ItemMod.gelTier, 100, 100, new Properties().tab(ModTab.INSTANCE)).setRegistryName("gelshovel");

    public GelShovelItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties){
        super(tier, attackDamageIn, attackSpeedIn, properties);

    }
}
