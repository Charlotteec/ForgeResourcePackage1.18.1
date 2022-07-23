package com.idtech.item;

import com.idtech.ModTab;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class GelHoeItem extends HoeItem {

    public static Item INSTANCE = new GelHoeItem(ItemMod.gelTier, 100, 100, new Properties().tab(ModTab.INSTANCE)).setRegistryName("gelhoe");

    public GelHoeItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties){
        super(tier, attackDamageIn, attackSpeedIn, properties);

    }
}
