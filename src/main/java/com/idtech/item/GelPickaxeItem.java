package com.idtech.item;

import com.idtech.BaseMod;
import com.idtech.ModTab;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class GelPickaxeItem extends PickaxeItem {


    public static Tier tier = new ForgeTier(4, 1000, 25.0F, 10.0F, 10, null, ()->{return Ingredient.of(ItemMod.STRUCTURE_GEL);});
    public static Item INSTANCE = new GelPickaxeItem(tier,100, 100, new Properties().tab(ModTab.INSTANCE)).setRegistryName(BaseMod.MODID,"gelpickaxe");

    public GelPickaxeItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties){
        super(tier, attackDamageIn, attackSpeedIn, properties);

    }
}
