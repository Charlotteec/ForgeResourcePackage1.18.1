package com.idtech.item;

import com.idtech.BaseMod;
import com.idtech.ModTab;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.ToolAction;

public class GelPaxelItem extends HoeItem {
    public static Tier tier = new ForgeTier(5, 1000, 25.0F, 10.0F, 10, null, ()->{return Ingredient.of(ItemMod.STRUCTURE_GEL);});
    public static Item INSTANCE = new GelPaxelItem(tier, 100, 100, new Properties().tab(ModTab.INSTANCE)).setRegistryName(BaseMod.MODID, "gelpaxel");

    public GelPaxelItem( Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }

    // Let's just tell minecraft that this tool is a tool that can perform any action.
    @Override
    public boolean canPerformAction( ItemStack stack, ToolAction toolAction ) {
        return true;
    }

    // We want to ensure that no matter what the block is, we have the same destroy speed.
    @Override
    public float getDestroySpeed( ItemStack stack, BlockState state ) {
        return this.speed;
    }

    // Almost identical logic to the digger item implementation EXCEPT for we ignore what blocks
    // are considered harvestable via tool.
    // This basically makes the paxel do everything a tool can do.
    // The only exception is shears. It won't shear sheep, but it will break blocks like shears.
//    @Override
//    public boolean isCorrectToolForDrops( ItemStack stack, BlockState state ) {
//        return net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(getTier(), state);
//    }


    // This version of the isCorrectToolForDrops lets us control a what the tool is a combination of a little better.
    @Override
    public boolean isCorrectToolForDrops( ItemStack stack, BlockState state ) {
        // Check if the tool is the correct tier for harvesting the block.
        if(!net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(getTier(), state))
            return false;

        // Feel free to comment out any of these if statements in order to control whether this tool is a mix of axe/pickaxe or shovel/hoe etc...
        if(state.is(BlockTags.MINEABLE_WITH_AXE))
            return true;

        if(state.is(BlockTags.MINEABLE_WITH_PICKAXE))
            return true;

        if(state.is(BlockTags.MINEABLE_WITH_HOE))
            return true;

        if(state.is(BlockTags.MINEABLE_WITH_SHOVEL))
            return true;
        return false;
    }
}
