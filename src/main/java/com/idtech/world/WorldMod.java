package com.idtech.world;

import com.idtech.BaseMod;
import com.idtech.block.BlockMod;
import com.idtech.entity.EvilRabbit;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = BaseMod.MODID)
public class WorldMod {

    // Testing Ore Generation (https://fabricmc.net/wiki/tutorial:ores)
    private static ConfiguredFeature<?, ?> OVERWORLD_FIRE_CRYSTAL_FEATURE = new ConfiguredFeature(
            Feature.ORE, new OreConfiguration(
            OreFeatures.STONE_ORE_REPLACEABLES,
            BlockMod.FIRE_CRYSTAL_BLOCK.defaultBlockState(),
            9));

    public static PlacedFeature OVERWORLD_FIRE_CRYSTAL_PLACED_FEATURE = OVERWORLD_FIRE_CRYSTAL_FEATURE.placed(
            List.of(
                    CountPlacement.of(20), // Number of veins per chunk
                    InSquarePlacement.spread(), //Horizontal spreading
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)),
                    BiomeFilter.biome()
            ));

    // For some reason you need to create this resource key to register biomes idk
    //private static ResourceKey<Biome> BASIC_TESTER = ResourceKey.create(Registry.BIOME_REGISTRY, CustomBiomes.BASIC_TESTER.getRegistryName());
    private static ResourceKey<Biome> STORMFIELD_PLAINS = ResourceKey.create(Registry.BIOME_REGISTRY, StormfieldPlainsBiome.INSTANCE.getRegistryName());

    public static void registerBiomes(final RegistryEvent.Register<Biome> event){
        //register the biome itself
        //event.getRegistry().register(CustomBiomes.BASIC_TESTER);
        event.getRegistry().register(StormfieldPlainsBiome.INSTANCE);

    }


    public static void setupBiomes(){
////add the biome to the biome dictionary
//        //BiomeDictionary.addTypes(BASIC_TESTER, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
//        // Adding a biome to the manager and making sure it spawns regularly (weight is how likely it is to show up)
//        //BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BASIC_TESTER, 9000));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(STORMFIELD_PLAINS, 9000));
    }



    @SubscribeEvent
    public static void addFeatures(BiomeLoadingEvent event) {
        Biome.BiomeCategory biomeCategory = event.getCategory();
        BiomeGenerationSettingsBuilder biomeGen = event.getGeneration();
        MobSpawnSettingsBuilder builder = event.getSpawns();

        FeatureUtils.register("fire_crystal_feature", OVERWORLD_FIRE_CRYSTAL_FEATURE);
        PlacementUtils.register("fire_crystal_feature", OVERWORLD_FIRE_CRYSTAL_PLACED_FEATURE);

        biomeGen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_FIRE_CRYSTAL_PLACED_FEATURE);
        if(event.getCategory().equals(Biome.BiomeCategory.PLAINS))
        {
            builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EvilRabbit.TYPE, 100, 2, 4));
        }
    }
}