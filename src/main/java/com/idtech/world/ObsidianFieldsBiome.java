package com.idtech.world;

import com.idtech.entity.EvilRabbit;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ObsidianFieldsBiome
{
    public static Biome INSTANCE;

    public static Biome ObsidianFieldsBiome(MobSpawnSettings.Builder mobSpawn, BiomeGenerationSettings.Builder biomeGen)
    {
        //Adjust mobSpawn
        mobSpawn.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EvilRabbit.TYPE, 500, 1, 5));
        //Adjust biomeGen
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeGen);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeGen);
        SurfaceRuleData surface = new SurfaceRuleData();

        //Build Biome
        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.PLAINS)
                .temperature(0F)
                .downfall(0F)
                .specialEffects((new BiomeSpecialEffects.Builder()
                        .waterColor(0x3F76E4)
                        .waterFogColor(0x050533)
                        .fogColor(0xC0D8FF)
                        .skyColor(0x00000000)
                        .build()))
                .mobSpawnSettings(mobSpawn.build())
                .generationSettings(biomeGen.build())
                .build();
    }
}
