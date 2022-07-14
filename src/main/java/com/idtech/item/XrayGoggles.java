package com.idtech.item;

import com.google.common.collect.ImmutableMap;

import com.idtech.BaseMod;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class represents an Xray-Goggles item. When worn, it gives all mobs in the area the
 * glowing effect
 *
 * @author Dylan Black
 * Created 07/14/2022
 *
 * TODO: Figuring out how to control when the effect turns off
 */
public class XrayGoggles extends ArmorItem {

  // set up properties
  private final static Properties properties = new Properties().tab(CreativeModeTab.TAB_COMBAT);

  // very creative naming is a must for any minecraft mod
  private final static ArmorMaterial CUSTOM_MATERIAL = ItemUtils.buildArmorMaterial("xray_stuff",
          22,
          new int[]{5,8,6,4} ,5,
          SoundEvents.ARMOR_EQUIP_GENERIC, 4.0f, 0.3f,
          "minecraft:glass");

  // the glasses singleton
  public static final Item GOGGLES = new XrayGoggles(CUSTOM_MATERIAL, EquipmentSlot.HEAD,
          (properties)).setRegistryName(BaseMod.MODID, "xraygoggles");

  // tbh I'm not sure why this constructor isn't private since isn't HELM a singleton and is the
  // only way the object should be used? Anyways thanks for coming to my TED talk love u iD <3
  public XrayGoggles(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
    super(material, slot, properties);
  }

  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
    if(slot == EquipmentSlot.HEAD) {
      return "examplemod:textures/models/armor/xray_goggles.png";
    } else {
      // these are x-ray goggles, we can't have anything but a helmet/goggles!
      // really more of a cop-out else statement here, but like, shhhhh
      throw new IllegalArgumentException("X-Ray Goggles cannot have a helmet!");
    }
  }

  @Override
  // every tick that the armor is being worn
  public void onArmorTick(ItemStack stack, Level world, Player player) {
    // this will only work on a single player world
    if (!world.isClientSide()) {
      if (hasHelmetOn(player)) {
        // we need to only run evaluateArmorEffects() if the player is
        // wearing a helmet
        evaluateArmorEffects(player);
      }
    }
  }

  // this is where the magic happens - this is a Map that maps an ArmorMaterial to a potion
  // effect. I'm trying to figure out how to use multiple besides just a Map<ArmorMaterial,
  // List<MobEffectInstance>>, but for now it's limited to one per material
  private final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
          (new ImmutableMap.Builder<ArmorMaterial,
                  MobEffectInstance>()).put(CUSTOM_MATERIAL,
                  new MobEffectInstance(MobEffects.GLOWING, 2,
                  2)).build();

  private void evaluateArmorEffects(Player player) {
    // for every entry in the material to effect map
    for(Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
      // get the material and effect
      ArmorMaterial mapArmorMaterial = entry.getKey();
      MobEffectInstance mapStatusEffect = entry.getValue();

      // if the player has the correct helmet on, we can add the effect!
      if (hasCorrectHelmet(mapArmorMaterial, player)) {

        addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
      }
    }
  }

  /**
   * Get all the living entities in a given radius
   *
   * @param player the player
   * @param x the x position to check
   * @param z the z position to check
   * @param radius the radius to check
   * @return A list of entities in the radius with center (x,, z) from y = 0 to 255
   */
  // unfortunately, does not return living trees from Lord of the Rings
  private List<Entity> getEnts(Player player,int x, int z, int radius) {
    return player.level.getEntities(player,
            new AABB(x-radius,0,z-radius,x+radius,255,
                    z+radius)).stream()
            // this filter is here to filter out non-living entities, but it seems we still have
            // to return a List<Entity> because Java is in the business of making me sad
            // Type checking is object-oriented...right?
            .filter(entity -> entity instanceof LivingEntity)
            .collect(Collectors.toList());
  }

  /**
   * Adds the effect to every entity in the area if the player is wearing the helmet.
   *
   * @param player the player wearing the helmet
   * @param mapArmorMaterial the armor material
   * @param mapStatusEffect the status effect
   */
  private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, MobEffectInstance mapStatusEffect) {
    boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

    // second dummy check, and make sure the player doesn't ALREADY have the effect
    if(hasCorrectHelmet(mapArmorMaterial, player) && !hasPlayerEffect) {
      List<Entity> entities = this.getEnts(player, (int)player.getX(),
              (int)player.getZ(), 50);

      for(Entity e : entities) {
        // this cast makes me angry >:(
        LivingEntity livingEntity = (LivingEntity) e;

        livingEntity.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
                mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));
      }
    }
  }

  /**
   * Determines if a user is wearing a helmet (any helmet)
   *
   * @param player the player in question
   * @return true if the helmet slot is not empty, false otherwise
   */
  private boolean hasHelmetOn(Player player) {
    ItemStack helmet = player.getInventory().getArmor(3);

    return !helmet.isEmpty();
  }

  /**
   * Checks that the player is wearing a helmet of the specified material.
   *
   * @param material the material to check
   * @param player the player in question
   * @return true if the user's helmet is of the given material
   *
   */
  private boolean hasCorrectHelmet(ArmorMaterial material, Player player) {
    final int HELM_ID = 3; // gorgeous gorgeous girls don't use magic numbers
    ArmorItem helmet = (ArmorItem)player.getInventory().getArmor(HELM_ID).getItem();

    return helmet.getMaterial() == material;
  }
}
