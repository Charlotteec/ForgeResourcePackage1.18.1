package com.idtech.item;

import com.idtech.ModTab;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;

import java.util.Optional;

public class Big3DSword extends SwordItem
{
    private static Properties properties = new Properties().tab(ModTab.INSTANCE);
    public static Item INSTANCE = new Big3DSword(Tiers.DIAMOND, 2, 8, properties).setRegistryName("bigsword");

    public Big3DSword(Tier tier, int speed, float damage, Properties properties) {
        super(tier, speed, damage, properties);
    }
}
