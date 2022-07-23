package com.idtech.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class HotCoalsBlock extends Block {

    private static Properties properties = Properties.of(Material.STONE);

    //static instances for registration
    public static Block INSTANCE = new HotCoalsBlock(properties).setRegistryName("hotcoals");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, CreativeModeTab.TAB_MISC);

    public HotCoalsBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level levelIn, BlockPos posIn, BlockState blockStateIn, Entity entityIn) {
        super.stepOn(levelIn, posIn, blockStateIn, entityIn);

        entityIn.hurt(DamageSource.HOT_FLOOR, 1);
        entityIn.setSecondsOnFire(2);

    }
}
