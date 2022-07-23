package com.idtech.item;


import com.idtech.ModTab;
import com.idtech.entity.projectiles.BombArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BombArrowItem extends ArrowItem {

    //typical item setup
    private static Properties properties = new Properties().tab(ModTab.INSTANCE);
    public static Item INSTANCE = new BombArrowItem(properties).setRegistryName("bombarrow");

    //constructor
    public BombArrowItem(Properties properties){
        super(properties);
    }

    //this function is called when the arrow impacts an entity or surface
    //this is where a custom effect can be added
    //onEntityHit can also be used but will only trigger on mobs
    @Override
    public AbstractArrow createArrow(Level levelIn, ItemStack stack, LivingEntity shooter) {
        BombArrow arrowentity = new BombArrow(levelIn, shooter);
        return arrowentity;
    }
}
