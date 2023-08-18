package com.idtech.entity.transformingfox;

import com.idtech.BaseMod;
import com.idtech.Utils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class CustomFox extends Fox {
    public static EntityType<CustomFox> TYPE = (EntityType<CustomFox>)
            EntityType.Builder.of(CustomFox::new, MobCategory.AMBIENT)
                    .build("customfox")
                    .setRegistryName(BaseMod.MODID, "customfox");

    public CustomFox(EntityType<? extends Fox> type, Level level) {
        super(type, level);
    }

    /* Overriden mobInteract() for transformation */
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (itemstack.getItem() == Items.CAKE) {
            CakeFox transformedFox = new CakeFox(CakeFox.TYPE, this.getLevel());
            Utils.spawnEntity(this.getLevel(), transformedFox, this.getOnPos().above());
            transformedFox.autoTame(player);
            this.setPos(this.getX(), this.getY() - 500.0, this.getZ());
            return InteractionResult.CONSUME;
        } else {
            return super.mobInteract(player, interactionHand);
        }
    }
}
