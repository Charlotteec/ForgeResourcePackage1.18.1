//package com.idtech.world;
//
//import net.minecraft.util.Mth;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.MobCategory;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.biome.BiomeGenerationSettings;
//import net.minecraft.world.level.biome.BiomeSpecialEffects;
//import net.minecraft.world.level.biome.MobSpawnSettings;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.levelgen.GenerationStep;
//import net.minecraft.world.level.levelgen.VerticalAnchor;
//import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
//import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
//import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class WorldUtils {
//
//    /**
//     * Function copy-pasted from VanillaBiomes to help calculate sky color of biomes.
//     * @param skyColor a float indicating the skycolor
//     * @return a rgb sky color
//     */
//    public static int calculateSkyColor(float skyColor) {
//        float f = skyColor / 3.0F;
//        f = Mth.clamp(f, -1.0F, 1.0F);
//        return Mth.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
//    }
//
//    /**
//     * Function to help create biomes more easily. Look into the VanillaBiomes class for more information on how all this works and examples of how things are done.
//     * @param category the category of the biome to be created
//     * @param topLayer the top layer of the biome (usually just one block tall)
//     * @param secondLayer the second layer of the biome (usually a few blocks tall)
//     * @param thirdLayer the third layer of the biome
//     * @param mobspawnsettings$builder a mob spawn settings builder used to add spawners. You will instantiate this when you create the biome instance in WorldMod
//     * @param biomegenerationsettings$builder a biom generation settings builder used to add spawners. You will instantiate this when you create the biome instance in WorldMod
//     * @param depth the depth of the biome (how tall it is)
//     * @param scale the scale of the biome (how big mountains can be)
//     * @param temp the temperature (has to do with what can grow etc)
//     * @param downfall what kind of weather the biome will have
//     * @param waterColor the color of the water in the biome.
//     * @return A built biome with all the parameters set and any additional mandatory parameters taken care of
//     */
//    public static Biome baseBiome(Biome.BiomeCategory category, BlockState topLayer, BlockState secondLayer, BlockState thirdLayer, MobSpawnSettings.Builder mobspawnsettings$builder, BiomeGenerationSettings.Builder biomegenerationsettings$builder, float depth, float scale, float temp, float downfall, int waterColor) {
//        //in 1.17 you have to create this surface configuration to set the blocks but its sort of nedlessly complicated so we will take care of that here.
//        SurfaceBuilderBaseConfiguration surface = new SurfaceBuilderBaseConfiguration(topLayer, secondLayer, thirdLayer);
//        biomegenerationsettings$builder.surfaceBuilder(SurfaceBuilder.DEFAULT.configured(surface));
//        //you also have to use a builder, so this is a builder set up with the basics of all the essential stuff - if students really
//        // wanna customize past this they can by copy pasting this whole thing and just manually setting everything in here (but its a pain)
//        return (new Biome.BiomeBuilder())
//                .precipitation(Biome.Precipitation.RAIN)
//                .biomeCategory(category)
//                .depth(depth)
//                .scale(scale)
//                .temperature(temp)
//                .downfall(downfall)
//                .specialEffects((new BiomeSpecialEffects.Builder())
//                        .waterColor(waterColor)
//                        .waterFogColor(waterColor)
//                        .fogColor(waterColor)
//                        .skyColor(WorldUtils.calculateSkyColor(0.7f))
//                        .build())
//                .mobSpawnSettings(mobspawnsettings$builder.build())
//                .generationSettings(biomegenerationsettings$builder.build())
//                .build();
//    }
//
//
//    /**
//     * Function to help create biomes more easily. Look into the VanillaBiomes class for more information on how all this works and examples of how things are done.
//     * @param category the category of the biome to be created
//     * @param topLayer the top layer of the biome (usually just one block tall)
//     * @param secondLayer the second layer of the biome (usually a few blocks tall)
//     * @param thirdLayer the third layer of the biome
//     * @param mobspawnsettings$builder a mob spawn settings builder used to add spawners. You will instantiate this when you create the biome instance in WorldMod
//     * @param biomegenerationsettings$builder a biome generation settings builder used to add spawners. You will instantiate this when you create the biome instance in WorldMod
//     * @param depth the depth of the biome (how tall it is)
//     * @param scale the scale of the biome (how big mountains can be)
//     * @param temp the temperature (has to do with what can grow etc)
//     * @param downfall what kind of weather the biome will have
//     * @param waterColor the color of the water in the biome.
//     * @param waterFogColor the color of the water fog in the biome.
//     * @param fogColor the color of the fog in the biome.
//     * @param skyColor the color of the sky - this will be used in calculateSkyColor.
//     * @return A built biome with all the parameters set and any additional mandatory parameters taken care of
//     */
//    public static Biome baseBiome(Biome.BiomeCategory category, BlockState topLayer, BlockState secondLayer, BlockState thirdLayer, MobSpawnSettings.Builder mobspawnsettings$builder, BiomeGenerationSettings.Builder biomegenerationsettings$builder, float depth, float scale, float temp, float downfall, int waterColor, int waterFogColor, int fogColor, float skyColor) {
//        //in 1.17 you have to create this surface configuration to set the blocks but its sort of nedlessly complicated so we will take care of that here.
//        SurfaceBuilderBaseConfiguration surface = new SurfaceBuilderBaseConfiguration(topLayer, secondLayer, thirdLayer);
//        biomegenerationsettings$builder.surfaceBuilder(SurfaceBuilder.DEFAULT.configured(surface));
//        //you also have to use a builder, so this is a builder set up with the basics of all the essential stuff - if students really
//        // wanna customize past this they can by copy pasting this whole thing and just manually setting everything in here (but its a pain)
//        return (new Biome.BiomeBuilder())
//                .precipitation(Biome.Precipitation.RAIN)
//                .biomeCategory(category)
//                .depth(depth)
//                .scale(scale)
//                .temperature(temp)
//                .downfall(downfall)
//                .specialEffects((new BiomeSpecialEffects.Builder())
//                        .waterColor(waterColor)
//                        .waterFogColor(waterFogColor)
//                        .fogColor(fogColor)
//                        .skyColor(WorldUtils.calculateSkyColor(skyColor))
//                        .build())
//                .mobSpawnSettings(mobspawnsettings$builder.build())
//                .generationSettings(biomegenerationsettings$builder.build())
//                .build();
//    }
//
//    /**
//     * Method to be used in biome loading event to generate an ores in all biomes
//     * @param category category of biome from loading event
//     * @param builder builder of biome from loading event
//     * @param blockstate the block/ore that is being added
//     * @param veinSize how many blocks should be generated at a time
//     * @param bottom lowest block to start vein at
//     * @param top highest block to start vein at
//     * @param count amount of total blocks in area (??)
//     */
//    public static void genOreInAllBiomes(Biome.BiomeCategory category, BiomeGenerationSettings.Builder builder, BlockState blockstate, int veinSize, int bottom, int top, int count) {
//
//        if (category != Biome.BiomeCategory.THEEND && category != Biome.BiomeCategory.NETHER) {
//            genOre(builder, blockstate, veinSize, bottom, top, count);
//        }
//
//    }
//
//    /**
//     * Method to be used in biome loading event to generate an ore in a list of biomes
//     * @param category category of biome from loading event
//     * @param builder builder of biome from loading event
//     * @param blockstate the block/ore that is being added
//     * @param veinSize how many blocks should be generated at a time
//     * @param bottom lowest block to start vein at
//     * @param top highest block to start vein at
//     * @param count amount of total blocks in area (??)
//     * @param biomeCategories categories of biomes to be generated in
//     */
//    public static void genOreInBiomes(Biome.BiomeCategory category, BiomeGenerationSettings.Builder builder, BlockState blockstate, int veinSize, int bottom, int top, int count, Biome.BiomeCategory...biomeCategories) {
//        List<Biome.BiomeCategory> biomeList = Arrays.asList(biomeCategories);
//        genOreInBiomes(category, builder, blockstate, veinSize, bottom, top, count, biomeList);
//    }
//
//    /**
//     * Private method used internally to help generate an ore in a list of biomes
//     * @param category the category of the biome being added
//     * @param builder the builder of the biome
//     * @param blockstate the block/ore that is being added
//     * @param veinSize how many blocks should be generated at a time
//     * @param bottom lowest block to start vein at
//     * @param top highest block to start vein at
//     * @param count amount of total blocks in area (??)
//     * @param biomeCategories categories of biomes to be generated in
//     */
//    private static void genOreInBiomes(Biome.BiomeCategory category, BiomeGenerationSettings.Builder builder, BlockState blockstate, int veinSize, int bottom, int top, int count, List<Biome.BiomeCategory> biomeCategories) {
//
//        biomeCategories.forEach(cat -> {
//            if(category == cat) {
//                genOre(builder, blockstate, veinSize, bottom, top, count);
//            }
//        });
//    }
//    /**
//     * Method to be used in a biome loading event to generate an ore in all biomes except a list of biomes
//     * @param category the category of the biome being added
//     * @param builder the builder of the biome
//     * @param blockstate the block/ore that is being added
//     * @param veinSize how many blocks should be generated at a time
//     * @param bottom lowest block to start vein at
//     * @param top highest block to start vein at
//     * @param count amount of total blocks in area (??)
//     * @param biomeCategories categories of biomes to NOT be generated in
//     */
//    public static void genOreInBiomesExcept(Biome.BiomeCategory category, BiomeGenerationSettings.Builder builder, BlockState blockstate, int veinSize, int bottom, int top, int count, Biome.BiomeCategory...biomeCategories) {
//        List<Biome.BiomeCategory> biomeList = Arrays.asList(biomeCategories);
//        genOreInBiomesExcept(category, builder, blockstate, veinSize, bottom, top, count, biomeList);
//    }
//
//    /**
//     * Private method used internally to help generate an ore in all biomes except a list of biomes
//     * @param category the category of the biome being added
//     * @param builder the builder of the biome
//     * @param blockstate the block/ore that is being added
//     * @param veinSize how many blocks should be generated at a time
//     * @param bottom lowest block to start vein at
//     * @param top highest block to start vein at
//     * @param count amount of total blocks in area (??)
//     * @param biomeCategories categories of biomes to NOT be generated in
//     */
//    private static void genOreInBiomesExcept(Biome.BiomeCategory category, BiomeGenerationSettings.Builder builder, BlockState blockstate, int veinSize, int bottom, int top, int count, List<Biome.BiomeCategory> biomeCategories) {
//            if (!biomeCategories.contains(category)) {     //conditional only permits overworld spawning
//                genOre(builder, blockstate, veinSize, bottom, top, count);
//            }
//    }
//
//    /**
//     * Method used to generate an ore in a biome.
//     * @param builder the builder of the biome
//     * @param blockstate the block/ore that is being added
//     * @param veinSize how many blocks should be generated at a time
//     * @param bottom lowest block to start vein at
//     * @param top highest block to start vein at
//     * @param count amount of total blocks in area (??)
//     */
//    private static void genOre(BiomeGenerationSettings.Builder builder, BlockState blockstate, int veinSize, int bottom, int top, int count) {
//        ConfiguredFeature<?,?> feature = makeFeature(blockstate, veinSize, bottom, top, count);
//        GenerationStep.Decoration genStep = GenerationStep.Decoration.UNDERGROUND_ORES;
//        builder.addFeature(genStep, feature);
//    }
//
//    /**
//     * Method to create a custom ore feature to be used in ore generation
//     * @param blockstate the block/ore that is being added
//     * @param veinSize how many blocks should be generated at a time
//     * @param bottom lowest block to start vein at
//     * @param top highest block to start vein at
//     * @param count amount of total blocks in area (??)
//     * @return the ConfiguredFeature ore configuration so that it can be used in other methods.
//     */
//    private static ConfiguredFeature<?,?> makeFeature(BlockState blockstate, int veinSize, int bottom, int top, int count){
//        ConfiguredFeature<?, ?> ORE_CANDIANITE = (Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, blockstate, veinSize))).rangeUniform(VerticalAnchor.absolute(bottom), VerticalAnchor.absolute(top)).squared().count(count);
//        return ORE_CANDIANITE;
//    }
//
//    /**
//     * Method to be used in biome loading event to spawn an entity in all biomes
//     * @param category category of biome from loading event
//     * @param spawner the spawner of the biome from loading event
//     * @param mobCategory the category of the mob to be spawned
//     * @param entityType the entity that will be spawned
//     * @param weight how likely that entity is to be spawned
//     * @param min the min amount of entities in a group
//     * @param max the max amount of entities in a group
//     */
//    public static void spawnMobInAllBiomes(Biome.BiomeCategory category, MobSpawnSettings.Builder spawner, MobCategory mobCategory, EntityType entityType, int weight, int min, int max) {
//
//        if (category != Biome.BiomeCategory.THEEND && category != Biome.BiomeCategory.NETHER) {
//            addSpawn(spawner, mobCategory, entityType, weight, min, max);
//        }
//
//    }
//
//    /**
//     * Method to be used in biome loading event to spawn a mob in a list of biomes
//     * @param category category of biome from loading event
//     * @param spawner the spawner of the biome from loading event
//     * @param mobCategory the category of the mob to be spawned
//     * @param entityType the entity that will be spawned
//     * @param weight how likely that entity is to be spawned
//     * @param min the min amount of entities in a group
//     * @param max the max amount of entities in a group
//     * @param biomeCategories categories of biomes to be spawned in
//     */
//    public static void spawnMobInBiomes(Biome.BiomeCategory category, MobSpawnSettings.Builder spawner, MobCategory mobCategory, EntityType entityType, int weight, int min, int max, Biome.BiomeCategory...biomeCategories) {
//        List<Biome.BiomeCategory> biomeList = Arrays.asList(biomeCategories);
//        spawnMobInBiomes(category, spawner, mobCategory, entityType, weight, min, max, biomeList);
//    }
//
//    /**
//     * Private method used internally to help spawn a mob in a list of biomes
//     * @param category category of biome from loading event
//     * @param spawner the spawner of the biome from loading event
//     * @param mobCategory the category of the mob to be spawned
//     * @param entityType the entity that will be spawned
//     * @param weight how likely that entity is to be spawned
//     * @param min the min amount of entities in a group
//     * @param max the max amount of entities in a group
//     * @param biomeCategories categories of biomes to be spawned in
//     */
//    private static void spawnMobInBiomes(Biome.BiomeCategory category, MobSpawnSettings.Builder spawner, MobCategory mobCategory, EntityType entityType, int weight, int min, int max, List<Biome.BiomeCategory> biomeCategories) {
//        biomeCategories.forEach(cat -> {
//            if(category == cat) {
//                addSpawn(spawner, mobCategory, entityType, weight, min, max);
//            }
//        });
//    }
//    /**
//     * Method to be used in biome loading event to spawn a mob in all biomes except a list of biomes
//     * @param category category of biome from loading event
//     * @param spawner the spawner of the biome from loading event
//     * @param mobCategory the category of the mob to be spawned
//     * @param entityType the entity that will be spawned
//     * @param weight how likely that entity is to be spawned
//     * @param min the min amount of entities in a group
//     * @param max the max amount of entities in a group
//     * @param biomeCategories categories of biomes to NOT be spawned in
//     */
//    public static void spawnMobInBiomesExcept(Biome.BiomeCategory category, MobSpawnSettings.Builder spawner, MobCategory mobCategory, EntityType entityType, int weight, int min, int max, Biome.BiomeCategory...biomeCategories) {
//        List<Biome.BiomeCategory> biomeList = Arrays.asList(biomeCategories);
//        spawnMobInBiomesExcept(category, spawner, mobCategory, entityType, weight, min, max, biomeList);
//    }
//
//    /**
//     * Private method used internally to help spawn a mob in all biomes except a list of biomes
//     * @param category category of biome from loading event
//     * @param spawner the spawner of the biome from loading event
//     * @param mobCategory the category of the mob to be spawned
//     * @param entityType the entity that will be spawned
//     * @param weight how likely that entity is to be spawned
//     * @param min the min amount of entities in a group
//     * @param max the max amount of entities in a group
//     * @param biomeCategories categories of biomes to NOT be spawned in
//     */
//    private static void spawnMobInBiomesExcept(Biome.BiomeCategory category, MobSpawnSettings.Builder spawner, MobCategory mobCategory, EntityType entityType, int weight, int min, int max, List<Biome.BiomeCategory> biomeCategories){
//        if (!biomeCategories.contains(category)) {     //conditional only permits overworld spawning
//            addSpawn(spawner, mobCategory, entityType, weight, min, max);
//        }
//    }
//    /**
//     * Method used to spawn an entity in a biome.
//     * @param mobCategory the category of the mob to be spawned
//     * @param entityType the entity that will be spawned
//     * @param weight how likely that entity is to be spawned
//     * @param min the min amount of entities in a group
//     * @param max the max amount of entities in a group
//     */
//    private static void addSpawn(MobSpawnSettings.Builder spawner, MobCategory mobCategory, EntityType entityType, int weight, int min, int max) {
//        MobSpawnSettings.SpawnerData spawnerData = new MobSpawnSettings.SpawnerData(entityType, weight, min, max);
//        spawner.addSpawn(mobCategory, spawnerData);
//    }
//
//}
//
