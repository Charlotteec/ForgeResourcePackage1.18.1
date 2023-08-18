package com.idtech.entity.customslime;

import com.idtech.BaseMod;
import com.idtech.entity.EntityUtils;
import com.idtech.particle.ParticleMod;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.lang.reflect.Field;

public class BlueSlimeEntity extends Slime {
    public static EntityType<BlueSlimeEntity> TYPE = (EntityType<BlueSlimeEntity>)
            EntityType.Builder.of(BlueSlimeEntity::new, MobCategory.MONSTER).sized(2.04F, 2.04F).
                    clientTrackingRange(8).build("blueslime").setRegistryName(BaseMod.MODID, "blueslime");
    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0xfef9f8 , 0x383737); // yes I know it looks like a skeleton egg but I'm too lazy to change it

    public BlueSlimeEntity(EntityType<? extends Slime> type, Level level) {
        super(type, level);
    }

    /* Copy in for registration even if no attributes are added */
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes();
    }

    /** Copy in to make blue slimes spawn custom slime particles with correct color */
    @Override
    protected ParticleOptions getParticleType() {
        return ParticleMod.ITEM_BLUE_SLIME;
    }

    /** If your Blockbench model was not the same size as the vanilla slime,
     * override Entity.java's refreshDimensions() like this to change hitbox size;
     * overriding getDimensions() only seems to affect big slimes */

    /* I couldn't think of a better way to access these private variables so
     * reflection it is */
    static Field dimensions, eyeHeight;
    static {
        try {
            dimensions = Entity.class.getDeclaredField("dimensions");
            eyeHeight = Entity.class.getDeclaredField("eyeHeight");
            dimensions.setAccessible(true);
            eyeHeight.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    public void refreshDimensions() {
        try {
            EntityDimensions entitydimensions = (EntityDimensions) dimensions.get(this);
            Pose pose = this.getPose();
            EntityDimensions entitydimensions1 = this.getDimensions(pose).scale(1.25F); /** Since my slime model is 10 pixels compared to vanilla's 8, I scaled my hitboxes here by 10/8, or 1.25 */
            net.minecraftforge.event.entity.EntityEvent.Size sizeEvent = net.minecraftforge.event.ForgeEventFactory.getEntitySizeForge(this, pose, entitydimensions, entitydimensions1, this.getEyeHeight(pose, entitydimensions1));
            entitydimensions1 = sizeEvent.getNewSize();
            dimensions.set(this, entitydimensions1);
            eyeHeight.set(this, sizeEvent.getNewEyeHeight());
            this.reapplyPosition();
            boolean flag = (double)entitydimensions1.width <= 4.0D && (double)entitydimensions1.height <= 4.0D;
            if (!this.level.isClientSide && !this.firstTick && !this.noPhysics && flag && (entitydimensions1.width > entitydimensions.width || entitydimensions1.height > entitydimensions.height)) { /* removed check for not player since it causes error */
                Vec3 vec3 = this.position().add(0.0D, (double)entitydimensions.height / 2.0D, 0.0D);
                double d0 = (double)Math.max(0.0F, entitydimensions1.width - entitydimensions.width) + 1.0E-6D;
                double d1 = (double)Math.max(0.0F, entitydimensions1.height - entitydimensions.height) + 1.0E-6D;
                VoxelShape voxelshape = Shapes.create(AABB.ofSize(vec3, d0, d1, d0));
                EntityDimensions finalEntitydimensions = entitydimensions1;
                this.level.findFreePosition(this, voxelshape, vec3, (double)entitydimensions1.width, (double)entitydimensions1.height, (double)entitydimensions1.width).ifPresent((p_185956_) -> {
                    this.setPos(p_185956_.add(0.0D, (double)(-finalEntitydimensions.height) / 2.0D, 0.0D));
                });
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
