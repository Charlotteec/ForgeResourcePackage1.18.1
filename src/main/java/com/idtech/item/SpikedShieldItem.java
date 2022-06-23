package com.idtech.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;

public class SpikedShieldItem extends ShieldItem
{
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);
    public static Item INSTANCE = new SpikedShieldItem(properties).setRegistryName("spikedshield");

    public SpikedShieldItem(Properties properties) {
        super(properties);
    }
}
