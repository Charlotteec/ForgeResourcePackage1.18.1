package com.idtech.events;

import com.idtech.BaseMod;
import com.idtech.Utils;
import com.idtech.entity.transformingfox.CustomFox;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BaseMod.MODID)
public class EventMod {
    /* Replace all foxes with custom foxes which are identical to normal foxes,
    except they can transform into snow fox with overridden method */
    @SubscribeEvent
    public static void transformingFox(EntityJoinWorldEvent event) {
        if (event.getEntity().getType() == EntityType.FOX) {
            CustomFox newFox = new CustomFox(CustomFox.TYPE, event.getWorld());
            Utils.spawnEntity(event.getWorld(), newFox, event.getEntity().getOnPos().above());
            /* Send old mobs 500 blocks down into the void (hopefully) because I couldn't figure
            how to make them disappear otherwise without the death animation (setRemoved() seems buggy) */
            event.getEntity().setPos(event.getEntity().getX(), event.getEntity().getY() - 500.0, event.getEntity().getZ());
        }
    }
}
