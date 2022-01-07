package com.idtech.item;

import com.idtech.BaseMod;
import com.idtech.ModTab;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class GelSwordItem extends SwordItem {


    public static Tier tier = new ForgeTier(4, 1000, 25.0F, 10.0F, 10, Tags.Blocks.ORES, ()->{return Ingredient.of(ItemMod.STRUCTURE_GEL);});
    public static Item INSTANCE = new GelSwordItem(tier,100, 100, new Properties().tab(ModTab.INSTANCE)).setRegistryName(BaseMod.MODID,"gelsword");

    public GelSwordItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties){
        super(tier, attackDamageIn, attackSpeedIn, properties);

    }
}
