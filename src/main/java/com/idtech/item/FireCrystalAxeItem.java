package com.idtech.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;

public class FireCrystalAxeItem extends AxeItem
{
    public static Item INSTANCE = new FireCrystalAxeItem(ItemMod.fireCrystalTier,2, 1.2F, new Properties().tab(CreativeModeTab.TAB_MISC)).
            setRegistryName("firecrystalaxe");

    public FireCrystalAxeItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        FlintAndSteelItem flint = new FlintAndSteelItem(new Properties());
        return flint.useOn(context);
    }
}
