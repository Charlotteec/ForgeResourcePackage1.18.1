package com.idtech.world;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class StormfieldPlainsBiome
{
    public static Biome INSTANCE = StormfieldPlainsBiome(new MobSpawnSettings.Builder(), new BiomeGenerationSettings.Builder()).setRegistryName("stormfieldplains");

    public static Biome StormfieldPlainsBiome(MobSpawnSettings.Builder mobSpawn, BiomeGenerationSettings.Builder builder)
    {
        BiomeDefaultFeatures.addGroveTrees(builder);
        BiomeDefaultFeatures.addCommonBerryBushes(builder);
        BiomeDefaultFeatures.addWaterTrees(builder);
        BiomeDefaultFeatures.addBlueIce(builder);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);

        mobSpawn.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 500, 1, 5));
        mobSpawn.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 550, 1, 5));
        mobSpawn.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 400, 1, 5));

        mobSpawn.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 500, 3, 4));
        mobSpawn.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 450, 2, 3));
        mobSpawn.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 200, 1, 2));


        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.PLAINS)
                .temperature(0F)
                .downfall(1F)
                .specialEffects((new BiomeSpecialEffects.Builder()
                        .waterColor(0x3F76E4)
                        .waterFogColor(0x050533)
                        .fogColor(0xC0D8FF)
                        .skyColor(0x00000000)
                        .build()
                ))
                .mobSpawnSettings(mobSpawn.build())
                .generationSettings(builder.build())
                .build();
    }
}
