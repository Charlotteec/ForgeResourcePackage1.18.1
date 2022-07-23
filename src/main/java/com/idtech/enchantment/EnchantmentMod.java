package com.idtech.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;

public class EnchantmentMod {

    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().register(WitherTouchEnchantment.INSTANCE);

    }

}