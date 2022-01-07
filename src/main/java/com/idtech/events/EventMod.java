//package com.idtech.events;
//
//
//import com.idtech.BaseMod;
//import com.idtech.item.CustomWeapon;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.effect.MobEffects;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
//import net.minecraftforge.event.entity.player.PlayerInteractEvent;
//import net.minecraftforge.event.world.BlockEvent;
//import net.minecraftforge.eventbus.api.Event;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//@Mod.EventBusSubscriber(modid = BaseMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
//public class EventMod
//{
//
//    @SubscribeEvent
//    public static void onItemInHand(final LivingEquipmentChangeEvent event){
//
//        if(event.getTo().getItem() instanceof CustomWeapon){
//            event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 999999999));
//        }else{
//            event.getEntityLiving().removeAllEffects();
//        }
//    }
//
//    //to add an event use @SubscribeEvent
//    // to create the event object you can type Event (the net.minecraftforge.eventbus.api.Event) and right click to implementations
//    // See the list of events and pick one (any one)
//    // then based on that event do stuff. Pretty easy pretty cool.
//
//
//}
