package com.idtech.item;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class CustomWeapon extends AxeItem {


    // PICK numbers here for your tier:

    // first the level (5 means it can mine anything and everything)
    static int level = 5;
    // uses is how many times you can use it before breaking
    static int uses = 1000;
    // speed is how fast it can break blocks
    static float speed = 25.0F;
    // this is a bonus for attack damage - there is another attack damage so this is a bonus for the tier not the tool itself
    static float attackDamageBonus = 10.0F;
    // how easy-to-enchant it is
    static int enchantmentValue = 10;
    // and finally you can pick an ingredient for
    static Item ingredient = ItemMod.STRUCTURE_GEL;
    // the attack damage
    static int attackDamage = 100;
    // the attack speed
    static int attackSpeed = 100;


    //Creating the weapon. Don't touch below this line.
    public static Tier tier = new ForgeTier(level, uses, speed, attackDamageBonus, enchantmentValue, null, ()->{return Ingredient.of(ingredient);});

    public static Item INSTANCE = new CustomWeapon(tier,attackDamage, attackSpeed, new Properties().tab(CreativeModeTab.TAB_COMBAT)).setRegistryName("customsword");

    public CustomWeapon(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties){
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }

}
