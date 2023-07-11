package com.idtech.item;

import com.idtech.BaseMod;
import com.idtech.entity.projectiles.ExplosionProjectile;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ThrowablePickaxe extends PickaxeItem {


    public static Tier tier = new ForgeTier(4, 1000, 25.0F, 10.0F, 10, null, ()->{return Ingredient.of(ItemMod.STRUCTURE_GEL);});
    public static Item INSTANCE = new ThrowablePickaxe(tier,100, 100, new Properties().tab(CreativeModeTab.TAB_MISC)).setRegistryName(BaseMod.MODID,"throwablepickaxe");

    public ThrowablePickaxe(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties){
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        levelIn.playSound((Player)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F,
                0.4F / (levelIn.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!levelIn.isClientSide) {
            ExplosionProjectile projectile = new ExplosionProjectile(levelIn, playerIn);
            projectile.setItem(itemstack);
            projectile.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);
            levelIn.addFreshEntity(projectile);

        }

        if (!playerIn.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        // Remove item from inv
        itemstack.setCount(itemstack.getCount() - 1);

        return InteractionResultHolder.sidedSuccess(itemstack, levelIn.isClientSide());

    }

}
