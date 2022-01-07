package com.idtech.block;

import com.idtech.BaseMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

/**
 * Utilities specific to creating block and doing things with block.
 */
public class BlockUtils {

    /**
     * If you need a basic block with no extra functionality, you can use this function to create one.
     * @param name the name of the block. Must be all lowercase and contain no spaces.
     * @param material the block's material
     * @return the constructe block with parameters
     */

    public static Block createBasicBlock(String name, Material material){
        return new Block(Block.Properties.of(material)).setRegistryName(BaseMod.MODID, name);
    }

    public static Block createBasicBlock(String name, Material material, float strength){
        return new Block(Block.Properties.of(material).strength(strength)).setRegistryName(BaseMod.MODID, name);
    }


    /**
     * Create a block item so that players can hold the block in their inventory.
     * @param block The block to create an item of
     * @param group The item group (creative tab) the block should go into.
     * @return The constructed block item
     */
    public static Item createBlockItem(Block block, CreativeModeTab group){
        return new BlockItem(block, new Item.Properties().tab(group)).setRegistryName(block.getRegistryName());
    }
}
