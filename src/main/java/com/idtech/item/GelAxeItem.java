package com.idtech.item;

import com.idtech.ModTab;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class GelAxeItem extends AxeItem {


    public static Item INSTANCE = new GelAxeItem(ItemMod.gelTier,100, 100, new Properties().tab(ModTab.INSTANCE)).setRegistryName("gelaxe");

    public GelAxeItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties){
        super(tier, attackDamageIn, attackSpeedIn, properties);

    }
}
