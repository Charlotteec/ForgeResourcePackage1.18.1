package com.idtech.block;

import com.idtech.BaseMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class RubberBlock extends Block {
    private static Properties properties = Properties.of(Material.STONE).strength(0.5f).destroyTime(0.2f);

    //static instances for registration
    public static Block INSTANCE = new RubberBlock(properties).setRegistryName(BaseMod.MODID,"rubber");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, CreativeModeTab.TAB_MISC);

    //constructor
    public RubberBlock(Properties properties){
        super(properties);
    }


    @Override
    public void stepOn(Level levelIn, BlockPos posIn, BlockState blockStateIn, Entity entityIn) {
        super.stepOn(levelIn, posIn, blockStateIn, entityIn);

        // Add momentum to the entity rather than removing
        var delta = entityIn.getDeltaMovement();

        entityIn.setDeltaMovement(delta.x, 5, delta.z);

    }

    @Override
    public void fallOn( Level levelIn, BlockState blockStateIn, BlockPos posIn, Entity entityIn, float distance ) {
        // Just by overriding this method and not calling super we prevent all forms of fall damage.
    }
}
