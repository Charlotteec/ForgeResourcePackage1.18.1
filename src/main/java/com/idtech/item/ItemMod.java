package com.idtech.item;

import com.idtech.ModTab;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ItemMod {

    //BASIC ITEMS
    public static final Item STRUCTURE_GEL = ItemUtils.buildBasicItem("structuregel", ModTab.INSTANCE);
    public static final Item END_ORE = ItemUtils.buildBasicItem("endore", ModTab.INSTANCE);
    public static final Item GEL_ORE = ItemUtils.buildBasicItem("gelore", ModTab.INSTANCE);
    public static final Item FIRE_CRYSTAL = ItemUtils.buildBasicItem("firecrystal", CreativeModeTab.TAB_MISC);

    //FOODS
    public static FoodProperties yummyFood = (new FoodProperties.Builder().nutrition(5).saturationMod(1.4f).effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 500, 1), 1.0f).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 1), 1.0F).alwaysEat().build());
    public static Item yummyFoodItem = ItemUtils.buildFoodItem("yummyfood", yummyFood);

    //TOOLS
    public static Tier fireCrystalTier = new ForgeTier(5, 100, 25.0F, 10.0F, 10, null, ()->{return Ingredient.of(ItemMod.FIRE_CRYSTAL);});

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        //BASIC ITEMS
        event.getRegistry().register(STRUCTURE_GEL);
        event.getRegistry().register(END_ORE);
        event.getRegistry().register(GEL_ORE);
        event.getRegistry().register(FIRE_CRYSTAL);

        // ITEMS
        event.getRegistry().register(LightningHammerItem.INSTANCE);
        event.getRegistry().register(TeleportRodItem.INSTANCE);
        event.getRegistry().register(FireballWandItem.INSTANCE);

        event.getRegistry().register(FireCrystalPickaxeItem.INSTANCE);
        event.getRegistry().register(FireCrystalSwordItem.INSTANCE);
        event.getRegistry().register(FireCrystalAxeItem.INSTANCE);
        event.getRegistry().register(FireCrystalShovelItem.INSTANCE);
        event.getRegistry().register(FireCrystalHoeItem.INSTANCE);

        // TOOLS
        event.getRegistry().register(GelPickaxeItem.INSTANCE);
        event.getRegistry().register(GelSwordItem.INSTANCE);
        event.getRegistry().register(GelAxeItem.INSTANCE);
        event.getRegistry().register(CustomWeapon.INSTANCE);
        event.getRegistry().register(ZooSwordItem.INSTANCE);
        event.getRegistry().register(Big3DSword.INSTANCE);
        event.getRegistry().register(MaceItem.INSTANCE);
        event.getRegistry().register(SpikedShieldItem.INSTANCE);

        // FOOD
        event.getRegistry().register(yummyFoodItem);

        // ARMOR
        event.getRegistry().register(CustomArmorItem.CUSTOM_HELM);
        event.getRegistry().register(CustomArmorItem.CUSTOM_CHEST);
        event.getRegistry().register(CustomArmorItem.CUSTOM_LEGS);
        event.getRegistry().register(CustomArmorItem.CUSTOM_BOOTS);

        event.getRegistry().register(FireCrystalArmor.FIRECRYSTAL_HELM);
        event.getRegistry().register(FireCrystalArmor.FIRECRYSTAL_CHEST);
        event.getRegistry().register(FireCrystalArmor.FIRECRYSTAL_LEGS);
        event.getRegistry().register(FireCrystalArmor.FIRECRYSTAL_BOOTS);

        //PROJECTILES
        event.getRegistry().register(BombArrowItem.INSTANCE);
        event.getRegistry().register(FireCrystalArrowItem.INSTANCE);
        event.getRegistry().register(ExplosionProjectileItem.INSTANCE);
        event.getRegistry().register(LaunchProjectileItem.INSTANCE);

    }
}
