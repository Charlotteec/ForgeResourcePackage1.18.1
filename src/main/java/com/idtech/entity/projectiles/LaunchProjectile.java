package com.idtech.entity.projectiles;

import com.idtech.item.ExplosionProjectileItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.fmllegacy.network.NetworkHooks;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class LaunchProjectile extends ThrowableItemProjectile implements ItemSupplier {

    public static EntityType<? extends ThrowableItemProjectile> TYPE = (EntityType<LaunchProjectile>)
            EntityType.Builder.<LaunchProjectile>of(LaunchProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("launchprojectile")
                    .setRegistryName("launchprojectile");

    public LaunchProjectile(EntityType<LaunchProjectile> type, Level level) {
        super(type, level);
    }

    public LaunchProjectile(Level level, LivingEntity entity) {
        super(TYPE, entity, level);
    }


    @Override
    protected Item getDefaultItem() {
        return ExplosionProjectileItem.INSTANCE;
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);

        if (entityHitResult != null) {

            Entity entity = entityHitResult.getEntity();
            if (entity instanceof LivingEntity) {
                entity.setDeltaMovement(0, 5, 0);
            }
        }

    }
}
