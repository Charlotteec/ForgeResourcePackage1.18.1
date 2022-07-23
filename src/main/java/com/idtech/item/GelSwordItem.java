package com.idtech.item;

import com.idtech.ModTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class GelSwordItem extends SwordItem {


    public static Item INSTANCE = new GelSwordItem(ItemMod.gelTier,100, 100, new Properties().tab(ModTab.INSTANCE)).setRegistryName("gelsword");

    public GelSwordItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties){
        super(tier, attackDamageIn, attackSpeedIn, properties);

    }
}
