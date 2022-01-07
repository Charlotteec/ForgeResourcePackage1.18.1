//package com.idtech.world;
//
//import com.idtech.BaseMod;
//import com.idtech.block.LatteBlock;
//import com.idtech.block.LatteMidBlock;
//import net.minecraft.data.worldgen.BiomeDefaultFeatures;
//import net.minecraft.data.worldgen.Features;
//import net.minecraft.data.worldgen.StructureFeatures;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.MobCategory;
//import net.minecraft.world.level.biome.*;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.levelgen.GenerationStep;
//
//
//public class CustomBiomes {
//    // ALL biomes can be created in this file - its modeled after the vanilla biomes file so if you want to see how other biomes
//    // are created and steal code that would be a good place to look!
//
//
//    // biome instance for registration
//    public static Biome BASIC_TESTER = basicTesterBiome(new MobSpawnSettings.Builder(), new BiomeGenerationSettings.Builder()).setRegistryName(BaseMod.MODID, "basic_tester");
//
//    // create a biome you can name it what you want!
//    public static Biome basicTesterBiome(MobSpawnSettings.Builder mobspawnsettings$builder, BiomeGenerationSettings.Builder biomegenerationsettings$builder) {
//
//        // Setting up the spawners to have creatures spawn in the biome
//        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 500, 1, 5));
//        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.BEE, 500, 1, 5));
//        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 500, 1, 5));
//
//        //Adding features and decorations - this is fun and also i went a little crazy
//        BiomeDefaultFeatures.addBerryBushes(biomegenerationsettings$builder);
//        BiomeDefaultFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
//        BiomeDefaultFeatures.addForestFlowers(biomegenerationsettings$builder);
//        BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
//        BiomeDefaultFeatures.addDefaultExtraVegetation(biomegenerationsettings$builder);
//        BiomeDefaultFeatures.addMossyStoneBlock(biomegenerationsettings$builder);
//        BiomeDefaultFeatures.addFerns(biomegenerationsettings$builder);
//        BiomeDefaultFeatures.addDefaultFlowers(biomegenerationsettings$builder);
//
//        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.FOREST_FLOWER_TREES);
//        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.FLOWER_FOREST);
//        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.RED_MUSHROOM_NORMAL);
//        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.MUSHROOM_FIELD_VEGETATION);
//        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, Features.GLOWSTONE_EXTRA);
//        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.LAKES, Features.LAKE_WATER);
//
//        biomegenerationsettings$builder.addStructureStart(StructureFeatures.MINESHAFT);
//
//        //this uses a base biome to set up some of the general stuff and to avoid making this extra confusing.
//        // You can see what goes on in the WorldUtils file - you can replace this return statement with that but you will
//        // have to create your own surface builder so This is probably the simplest.
//        return WorldUtils.baseBiome(Biome.BiomeCategory.FOREST,
//                LatteBlock.INSTANCE.defaultBlockState(),
//                LatteMidBlock.INSTANCE.defaultBlockState(),
//                LatteMidBlock.INSTANCE.defaultBlockState(),
//                mobspawnsettings$builder, biomegenerationsettings$builder,
//                0.8f, 1.0f, 0.7f, 0.6f, 4159204);
//    }
//}
//
