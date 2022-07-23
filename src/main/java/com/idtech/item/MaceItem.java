package com.idtech.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public class MaceItem extends SwordItem {
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);
    public static Item INSTANCE = new MaceItem(Tiers.IRON, 2, 5, properties).setRegistryName("mace");

    public MaceItem(Tier tier, int speed, float damage, Properties properties) {
        super(tier, speed, damage, properties);
    }
}
