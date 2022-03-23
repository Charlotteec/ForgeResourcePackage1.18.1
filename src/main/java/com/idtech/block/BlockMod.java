package com.idtech.block;


import com.idtech.BaseMod;
import com.idtech.ModTab;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
//import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockMod {

    //Basic Block
    public static final Block CASTLE_WALL = BlockUtils.createBasicBlock("castlewall", Material.STONE);
    public static final Item CASTLE_WALL_ITEM = BlockUtils.createBlockItem(CASTLE_WALL, ModTab.INSTANCE);


    //ores must also be added to the tags file so that they can be harvested with the correct tool
    // might make sense to make a method in blockutils to make this process simpler but idk
    public static final Block GEL_ORE_BLOCK =
            new OreBlock(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F))
                    .setRegistryName(BaseMod.MODID, "geloreblock");
    public static final Item GEL_ORE_ITEM = BlockUtils.createBlockItem(GEL_ORE_BLOCK, ModTab.INSTANCE);

    public static final Block FIRE_CRYSTAL_BLOCK =
            new OreBlock(Block.Properties.of(Material.STONE)
                    .strength(0.5F, 0.6F))
                    .setRegistryName(BaseMod.MODID, "firecrystalblock");
    public static final Item FIRE_CRYSTAL_BLOCK_ITEM = BlockUtils.createBlockItem(FIRE_CRYSTAL_BLOCK, ModTab.INSTANCE);

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {

        event.getRegistry().register(CASTLE_WALL_ITEM);
        event.getRegistry().register(GEL_ORE_ITEM);
        event.getRegistry().register(FIRE_CRYSTAL_BLOCK_ITEM);

        event.getRegistry().register(TNTCannonBlock.ITEM);
        event.getRegistry().register(RubberBlock.ITEM);
        event.getRegistry().register(CreeperSurpriseBlock.ITEM);
        event.getRegistry().register(CreepingMoldBlock.ITEM);
        event.getRegistry().register(LatteBlock.ITEM);
        event.getRegistry().register(LatteMidBlock.ITEM);

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        event.getRegistry().register(CASTLE_WALL);
        event.getRegistry().register(GEL_ORE_BLOCK);
        event.getRegistry().register(FIRE_CRYSTAL_BLOCK);

        event.getRegistry().register(TNTCannonBlock.INSTANCE);
        event.getRegistry().register(RubberBlock.INSTANCE);
        event.getRegistry().register(CreeperSurpriseBlock.INSTANCE);
        event.getRegistry().register(CreepingMoldBlock.INSTANCE);
        event.getRegistry().register(LatteBlock.INSTANCE);
        event.getRegistry().register(LatteMidBlock.INSTANCE);

    }
}





