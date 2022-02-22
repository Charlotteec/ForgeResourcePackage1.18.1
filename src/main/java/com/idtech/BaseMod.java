package com.idtech;

import com.idtech.block.*;
import com.idtech.enchantment.EnchantmentMod;
import com.idtech.entity.*;
import com.idtech.item.*;

//import com.idtech.world.WorldMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

/**
 * The BaseMod class holds any static variables your mod needs and runs all registry events. You'll add registry lines
 * in this file for all of your block, item, entities, etc. that you add to Minecraft
 */
@Mod(BaseMod.MODID)
//@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class BaseMod {

    // Change your modid here. Whenever modid is needed, use BaseMod.MODID
    public static final String MODID = "examplemod";
    private static final Logger LOGGER = LogManager.getLogger(BaseMod.MODID);

    public BaseMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Setup step after all other registry events - if you need to do anything after everything else has loaded, put it here.
     *
     * @param event event info
     */
    public void setup(FMLCommonSetupEvent event) {
        // Do any mod setup steps here. Occurs after all registry events.
        // Put biome manager registry stuff here.
        BaseMod.LOGGER.info("Mod Setup Step");
//        WorldMod.setupBiomes();
       // TierSortingRegistry.registerTier(ItemMod.GEL_TIER, new ResourceLocation(MODID, "gelore"), List.of(Tiers.NETHERITE), List.of());

        BaseMod.LOGGER.info("Command registration here hopefully.");
//        MinecraftForge.EVENT_BUS.register(CustomEvent.class);
//        MinecraftForge.EVENT_BUS.addListener(EventMod::isHoldingEvent);
        //Adds the RegisterCommandEvent as an event and sets a listener for it during FMLCommonSetup
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo(MODID, "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

        // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
//        @SubscribeEvent
//        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
//            // register a new block here
//            LOGGER.info("HELLO from Register Block");
//        }

        /**
         * Registers block during mod setup
         *
         * @param event RegistryEvent to access the block registry
         */
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            LOGGER.info("Registering Blocks");


//            event.getRegistry().register(BlockMod.CASTLE_WALL);
            BlockMod.registerBlocks(event);

        }


        /**
         * Registers item during mod setup
         *
         * @param event RegistryEvent to access the item registry
         */
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            LOGGER.info("Registering Items");
            // Add item registry calls here.
            // event.getRegistry.register(<item variable>)

            ItemMod.registerItems(event);
            BlockMod.registerBlockItems(event);
            EntityMod.registerEntityEggs(event);

        }


        /**
         * Registers entities during mod setup
         *
         * @param event RegistryEvent to access the entity registry
         */
        @SubscribeEvent
        public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
            BaseMod.LOGGER.info("Registering Entities");
            // Add item registry calls here.
            // event.getRegistry.register(<entity type>)
            // also register the entity attributes with:
            // GlobalEntityTypeAttributes.put(<entity type>, <entity attribute method>.func_233813_a_());
            EntityMod.registerEntities(event);

        }

        @SubscribeEvent
        public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
            BaseMod.LOGGER.info("Registering Biomes");
            // Add biome registry calls here
            // event.getRegistry.register(<biome variable>)
//            WorldMod.registerBiomes(event);

        }

        @SubscribeEvent
        public static void entityRenderers(final EntityRenderersEvent.RegisterRenderers event){
            EntityMod.entityRenderers(event);
        }
        @SubscribeEvent
        public static void attributeRegister(EntityAttributeCreationEvent event) {
            EntityMod.onAttributeCreate(event);
        }

        @SubscribeEvent
        public static void registerEnchantments(final RegistryEvent.Register<Enchantment> event){
            EnchantmentMod.registerEnchantments(event);
        }

        @SubscribeEvent
        public static void clientSetup(EntityRenderersEvent.RegisterLayerDefinitions event)
        {
            event.registerLayerDefinition(EvilRabbitModel.LAYER_LOCATION, EvilRabbitModel::createBodyLayer);
        }

    }
}


