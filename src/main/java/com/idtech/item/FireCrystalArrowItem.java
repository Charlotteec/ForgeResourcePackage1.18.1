package com.idtech.item;

import com.idtech.entity.projectiles.FireCrystalArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FireCrystalArrowItem extends ArrowItem {

    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);
    public static Item INSTANCE = new FireCrystalArrowItem(properties).setRegistryName("firecrystalarrow");

    public FireCrystalArrowItem(Properties properties){
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level levelIn, ItemStack stack, LivingEntity shooter) {
        FireCrystalArrow arrowentity = new FireCrystalArrow(levelIn, shooter);
        return arrowentity;
    }
}
