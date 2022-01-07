//package com.idtech.world;
//
//import com.idtech.BaseMod;
//import com.idtech.block.BlockMod;
//import net.minecraft.core.Registry;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraftforge.common.BiomeDictionary;
//import net.minecraftforge.common.BiomeManager;
//import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
//import net.minecraftforge.common.world.MobSpawnInfoBuilder;
//import net.minecraftforge.event.RegistryEvent;
//import net.minecraftforge.event.world.BiomeLoadingEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//@Mod.EventBusSubscriber(modid = BaseMod.MODID)
//public class WorldMod {
//
//    // For some reason you need to create this resource key to register biomes idk
//    private static ResourceKey<Biome> BASIC_TESTER = ResourceKey.create(Registry.BIOME_REGISTRY, CustomBiomes.BASIC_TESTER.getRegistryName());
//
//    public static void registerBiomes(final RegistryEvent.Register<Biome> event){
//        //register the biome itself
//        event.getRegistry().register(CustomBiomes.BASIC_TESTER);
//
//    }
//
//    public static void setupBiomes(){
////add the biome to the biome dictionary
//        BiomeDictionary.addTypes(BASIC_TESTER, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
//        // Adding a biome to the manager and making sure it spawns regularly (weight is how likely it is to show up)
//        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BASIC_TESTER, 9000));
//
//    }
//
//
//
//    @SubscribeEvent
//    public static void addFeatures(BiomeLoadingEvent event) {
//        Biome.BiomeCategory biomeCategory = event.getCategory();
//        BiomeGenerationSettingsBuilder builder = event.getGeneration();
//        MobSpawnInfoBuilder spawner = event.getSpawns();
//
//        //WorldUtils.genOreInBiomesExcept(biomeCategory, builder, CreeperSurpriseBlock.INSTANCE.defaultBlockState(), 10, 12, 99, 250, Biome.BiomeCategory.FOREST);
//        WorldUtils.genOreInAllBiomes(biomeCategory, builder, BlockMod.GEL_ORE_BLOCK.defaultBlockState(), 9, 0, 63, 50);
//       // WorldUtils.genOreInBiomes(biomeCategory, builder, RubberBlock.INSTANCE.defaultBlockState(), 9, 0, 63, 200, Biome.BiomeCategory.FOREST);
//
////        WorldUtils.spawnMobInAllBiomes(biomeCategory, spawner, MobCategory.MONSTER, LatteChicken.TYPE, 500, 2, 10);
////        WorldUtils.spawnMobInBiomesExcept(biomeCategory, spawner, MobCategory.MONSTER, GrumboBoy.TYPE, 500, 2, 10, Biome.BiomeCategory.FOREST);
////        WorldUtils.spawnMobInBiomes(biomeCategory, spawner, MobCategory.MONSTER, EntityType.CAT, 500, 5, 10, Biome.BiomeCategory.FOREST);
//    }
//}