package com.idtech.block;

import com.idtech.ModTab;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.IPlantable;

public class LatteBlock extends Block {

    private static Properties properties = Properties.of(Material.DIRT).strength(0.5f).destroyTime(0.2f);

    //static instances for registration
    public static Block INSTANCE = new LatteBlock(properties).setRegistryName("latteblock");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, ModTab.INSTANCE);


    public LatteBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return true;
    }

}
