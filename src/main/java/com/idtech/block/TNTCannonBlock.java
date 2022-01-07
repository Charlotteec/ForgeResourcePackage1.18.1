package com.idtech.block;

import com.idtech.BaseMod;
import com.idtech.ModTab;
import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class TNTCannonBlock extends Block {

    //I make the resistance higher because it has the opportunity to blow itself up quite easily
    private static Properties properties = Properties.of(Material.HEAVY_METAL).sound(SoundType.ANVIL).randomTicks();

    public static Block INSTANCE = new TNTCannonBlock(properties).setRegistryName(BaseMod.MODID,"tntcannon");

    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, ModTab.INSTANCE);

    public TNTCannonBlock(Properties properties){
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState blockState, Entity entityIn) {
        super.stepOn(level, pos, blockState, entityIn);

        //The setFire is used here to make sure the tnt gets lit
        entityIn.setSecondsOnFire(1);

        //I use the Math.random() here as an additional bit to keep the code interesting, it makes it so the tnt can be launched
        //in any direction, however it still works well with manually set x and z values.
        int max = 10;
        int min = -10;
        int X = (int)Math.floor(Math.random()*(max-min+1)+min);
        int Z = (int)Math.floor(Math.random()*(max-min+1)+min);

        //This is what launches the TNT, not sure why the TNT is considered to "Walk" on the block but it is.
        entityIn.setDeltaMovement(X,3,Z);

    }
    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos pos, Random rand) {
        super.tick( blockState,  level,  pos,  rand);

       PrimedTnt tnt = new PrimedTnt(EntityType.TNT, level);
        //I make sure to spawn the TNT above the block so I add 1 to the y value of the BlockPos
        Utils.spawnEntity(level, tnt, pos.above());
    }
}
