package com.idtech.item;

import com.idtech.BaseMod;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;

public class FireCrystalAxeItem extends AxeItem
{
    public static Item INSTANCE = new FireCrystalAxeItem(ItemMod.fireCrystalTier,2, 1.2F, new Properties().tab(CreativeModeTab.TAB_MISC)).
            setRegistryName(BaseMod.MODID,"firecrystalaxe");

    public FireCrystalAxeItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        FlintAndSteelItem flint = new FlintAndSteelItem(new Properties());
        return flint.useOn(context);
    }
}
