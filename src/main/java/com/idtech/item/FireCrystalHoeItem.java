package com.idtech.item;

import com.idtech.BaseMod;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class FireCrystalHoeItem extends HoeItem
{
    public static Item INSTANCE = new FireCrystalHoeItem(ItemMod.fireCrystalTier,2, 1.2F, new Properties().tab(CreativeModeTab.TAB_MISC)).
            setRegistryName(BaseMod.MODID,"firecrystalhoe");

    public FireCrystalHoeItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        FlintAndSteelItem flint = new FlintAndSteelItem(new Properties());
        return flint.useOn(context);
    }
}
