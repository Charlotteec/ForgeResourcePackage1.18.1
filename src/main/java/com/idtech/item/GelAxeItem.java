package com.idtech.item;

import com.idtech.ModTab;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class GelAxeItem extends AxeItem {
    //Tiers are completely different - this works but I Do think theres some weird stuff with the rank and tags that I also haven't quite sussed out yet.
    // If you wanna test what changing the level does for this and if it actually changes what blocks can be broken
    // also this is related to the ore theres something funky goin.
    public static Tier tier = new ForgeTier(5, 1000, 25.0F, 10.0F, 10, null, ()->{return Ingredient.of(ItemMod.STRUCTURE_GEL);});
    public static Item INSTANCE = new GelAxeItem(tier,100, 100, new Properties().tab(ModTab.INSTANCE)).setRegistryName("gelaxe");

    public GelAxeItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties){
        super(tier, attackDamageIn, attackSpeedIn, properties);

    }
}
