package com.idtech.world;

import com.idtech.BaseMod;
import com.idtech.block.BlockMod;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = BaseMod.MODID)
public class WorldMod {

    // Needed to create "replaceables" for ore feature.
    public static final RuleTest ENDSTONE = new BlockMatchTest(Blocks.END_STONE);

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

    public static ConfiguredFeature<?, ?> END_END_ORE_FEATURE = new ConfiguredFeature(
            Feature.ORE, new OreConfiguration(
            ENDSTONE,
            BlockMod.END_ORE_BLOCK.defaultBlockState(),
            9));

    public static PlacedFeature END_END_ORE_PLACED_FEATURE = END_END_ORE_FEATURE.placed(
            List.of(
                    CountPlacement.of(10),
                    InSquarePlacement.spread(),
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


    public static void setupBiomes()
    {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(STORMFIELD_PLAINS, 9999));
    }



    @SubscribeEvent
    public static void addFeatures(BiomeLoadingEvent event) {
        Biome.BiomeCategory biomeCategory = event.getCategory();
        BiomeGenerationSettingsBuilder biomeGen = event.getGeneration();
        MobSpawnSettingsBuilder builder = event.getSpawns();

        FeatureUtils.register("fire_crystal_feature", OVERWORLD_FIRE_CRYSTAL_FEATURE);
        PlacementUtils.register("fire_crystal_feature", OVERWORLD_FIRE_CRYSTAL_PLACED_FEATURE);

        FeatureUtils.register("end_ore_feature", END_END_ORE_FEATURE);
        PlacementUtils.register("end_ore_feature", END_END_ORE_PLACED_FEATURE);

        if(event.getName().getPath().equals(STORMFIELD_PLAINS.location().getPath()))
        {
            biomeGen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_FIRE_CRYSTAL_PLACED_FEATURE);
        }
        else if (event.getCategory() == Biome.BiomeCategory.THEEND)
        {
            // Spawn end ore
            biomeGen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, END_END_ORE_PLACED_FEATURE);
        }

        if(event.getCategory().equals(Biome.BiomeCategory.BEACH))
        {

        }
    }
}