package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonPhaseInstance;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhaseManager;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class CustomDragon extends Mob implements Enemy {

    //TYPE
    public static EntityType<CustomDragon> TYPE = (EntityType<CustomDragon>)
            EntityType.Builder.of(CustomDragon::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("customdragon").setRegistryName(BaseMod.MODID, "customdragon");
    //EGG
    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0xb00101, 0xacbf1f);

    private int growlTime = 100;
    public float oFlapTime;
    public float flapTime;
    public final double[][] positions = new double[64][3];
    public int posPointer = -1;
    private Vec3 targetLocation;
    public float yRotA;
    private boolean inWall;

    private final CustomDragonPart[] subEntities;
    public final CustomDragonPart head;
    private final CustomDragonPart neck;
    private final CustomDragonPart body;
    private final CustomDragonPart tail1;
    private final CustomDragonPart tail2;
    private final CustomDragonPart tail3;
    private final CustomDragonPart wing1;
    private final CustomDragonPart wing2;
//    private boolean dragonFight;

    public CustomDragon(EntityType<? extends Mob> customDragon, Level level) {
        super(TYPE, level);
        this.head = new CustomDragonPart(this, "head", 1.0F, 1.0F);
        this.neck = new CustomDragonPart(this, "neck", 3.0F, 3.0F);
        this.body = new CustomDragonPart(this, "body", 5.0F, 3.0F);
        this.tail1 = new CustomDragonPart(this, "tail", 2.0F, 2.0F);
        this.tail2 = new CustomDragonPart(this, "tail", 2.0F, 2.0F);
        this.tail3 = new CustomDragonPart(this, "tail", 2.0F, 2.0F);
        this.wing1 = new CustomDragonPart(this, "wing", 4.0F, 2.0F);
        this.wing2 = new CustomDragonPart(this, "wing", 4.0F, 2.0F);
        this.subEntities = new CustomDragonPart[]{this.head, this.neck, this.body, this.tail1, this.tail2, this.tail3, this.wing1, this.wing2};

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 200.0D);
    }

    protected void registerGoals() {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }
    public double[] getLatencyPos(int p_31102_, float p_31103_) {
        if (this.isDeadOrDying()) {
            p_31103_ = 0.0F;
        }

        p_31103_ = 1.0F - p_31103_;
        int i = this.posPointer - p_31102_ & 63;
        int j = this.posPointer - p_31102_ - 1 & 63;
        double[] adouble = new double[3];
        double d0 = this.positions[i][0];
        double d1 = Mth.wrapDegrees(this.positions[j][0] - d0);
        adouble[0] = d0 + d1 * (double)p_31103_;
        d0 = this.positions[i][1];
        d1 = this.positions[j][1] - d0;
        adouble[1] = d0 + d1 * (double)p_31103_;
        adouble[2] = Mth.lerp((double)p_31103_, this.positions[i][2], this.positions[j][2]);
        return adouble;
    }
    public float getTurnSpeed() {
        float f = (float)this.getDeltaMovement().horizontalDistance() + 1.0F;
        float f1 = Math.min(f, 40.0F);
        return 0.7F / f1 / f;
    }
    private float rotWrap(double p_31165_) {
        return (float)Mth.wrapDegrees(p_31165_);
    }
    public void aiStep() {
        super.aiStep();
        this.processFlappingMovement();
        if (this.level.isClientSide) {
            this.setHealth(this.getHealth());
            if (!this.isSilent() && --this.growlTime < 0) {
                this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ENDER_DRAGON_GROWL, this.getSoundSource(), 2.5F, 0.8F + this.random.nextFloat() * 0.3F, false);
                this.growlTime = 200 + this.random.nextInt(200);
            }
        }
        this.oFlapTime = this.flapTime;
        if (this.isDeadOrDying()) {
            float f8 = (this.random.nextFloat() - 0.5F) * 8.0F;
            float f9 = (this.random.nextFloat() - 0.5F) * 4.0F;
            float f10 = (this.random.nextFloat() - 0.5F) * 8.0F;
            this.level.addParticle(ParticleTypes.EXPLOSION, this.getX() + (double)f8, this.getY() + 2.0D + (double)f9, this.getZ() + (double)f10, 0.0D, 0.0D, 0.0D);

        }else{
            Vec3 vec3 = this.getDeltaMovement();
            float f = 0.2F / ((float)vec3.horizontalDistance() * 10.0F + 1.0F);
            f *= (float)Math.pow(2.0D, vec3.y);
            if (this.inWall) {
                this.flapTime += f * 0.5F;
            } else {
                this.flapTime += f;
            }
        }

        this.setYRot(Mth.wrapDegrees(this.getYRot()));
        if (this.isNoAi()) {
            this.flapTime = 0.5F;
        } else {
            if (this.posPointer < 0) {
                for (int i = 0; i < this.positions.length; ++i) {
                    this.positions[i][0] = (double) this.getYRot();
                    this.positions[i][1] = this.getY();
                }
            }

            if (++this.posPointer == this.positions.length) {
                this.posPointer = 0;
            }

            this.positions[this.posPointer][0] = (double) this.getYRot();
            this.positions[this.posPointer][1] = this.getY();
            if (this.level.isClientSide) {
                if (this.lerpSteps > 0) {
                    double d7 = this.getX() + (this.lerpX - this.getX()) / (double) this.lerpSteps;
                    double d0 = this.getY() + (this.lerpY - this.getY()) / (double) this.lerpSteps;
                    double d1 = this.getZ() + (this.lerpZ - this.getZ()) / (double) this.lerpSteps;
                    double d2 = Mth.wrapDegrees(this.lerpYRot - (double) this.getYRot());
                    this.setYRot(this.getYRot() + (float) d2 / (float) this.lerpSteps);
                    this.setXRot(this.getXRot() + (float) (this.lerpXRot - (double) this.getXRot()) / (float) this.lerpSteps);
                    --this.lerpSteps;
                    this.setPos(d7, d0, d1);
                    this.setRot(this.getYRot(), this.getXRot());

                }
                //do client tick (which is nothing in this case)
            }else {
                //do server tick
                if (this.targetLocation == null) {
                    this.targetLocation = this.position();
                }

                Vec3 vec31 = targetLocation;
                if (vec31 != null) {
                    double d8 = vec31.x - this.getX();
                    double d9 = vec31.y - this.getY();
                    double d10 = vec31.z - this.getZ();
                    double d3 = d8 * d8 + d9 * d9 + d10 * d10;
                    float f5 = 1.0F;
                    double d4 = Math.sqrt(d8 * d8 + d10 * d10);
                    if (d4 > 0.0D) {
                        d9 = Mth.clamp(d9 / d4, (double) (-f5), (double) f5);
                    }

                    this.setDeltaMovement(this.getDeltaMovement().add(0.0D, d9 * 0.01D, 0.0D));
                    this.setYRot(Mth.wrapDegrees(this.getYRot()));
                    Vec3 vec32 = vec31.subtract(this.getX(), this.getY(), this.getZ()).normalize();
                    Vec3 vec33 = (new Vec3((double) Mth.sin(this.getYRot() * ((float) Math.PI / 180F)), this.getDeltaMovement().y, (double) (-Mth.cos(this.getYRot() * ((float) Math.PI / 180F))))).normalize();
                    float f6 = Math.max(((float) vec33.dot(vec32) + 0.5F) / 1.5F, 0.0F);
                    if (Math.abs(d8) > (double) 1.0E-5F || Math.abs(d10) > (double) 1.0E-5F) {
                        double d5 = Mth.clamp(Mth.wrapDegrees(180.0D - Mth.atan2(d8, d10) * (double) (180F / (float) Math.PI) - (double) this.getYRot()), -50.0D, 50.0D);
                        this.yRotA *= 0.8F;
                        this.yRotA = (float) ((double) this.yRotA + d5 * (double) getTurnSpeed());
                        this.setYRot(this.getYRot() + this.yRotA * 0.1F);
                    }

                    float f18 = (float) (2.0D / (d3 + 1.0D));
                    float f7 = 0.06F;
                    this.moveRelative(0.06F * (f6 * f18 + (1.0F - f18)), new Vec3(0.0D, 0.0D, -1.0D));
                    if (this.inWall) {
                        this.move(MoverType.SELF, this.getDeltaMovement().scale((double) 0.8F));
                    } else {
                        this.move(MoverType.SELF, this.getDeltaMovement());
                    }

                    Vec3 vec34 = this.getDeltaMovement().normalize();
                    double d6 = 0.8D + 0.15D * (vec34.dot(vec33) + 1.0D) / 2.0D;
                    this.setDeltaMovement(this.getDeltaMovement().multiply(d6, (double) 0.91F, d6));
                }
            }
            this.yBodyRot = this.getYRot();
            Vec3[] avec3 = new Vec3[this.subEntities.length];

            for(int j = 0; j < this.subEntities.length; ++j) {
                avec3[j] = new Vec3(this.subEntities[j].getX(), this.subEntities[j].getY(), this.subEntities[j].getZ());
            }

            float f11 = (float)(this.getLatencyPos(5, 1.0F)[1] - this.getLatencyPos(10, 1.0F)[1]) * 10.0F * ((float)Math.PI / 180F);
            float f12 = Mth.cos(f11);
            float f1 = Mth.sin(f11);
            float f13 = this.getYRot() * ((float)Math.PI / 180F);
            float f2 = Mth.sin(f13);
            float f14 = Mth.cos(f13);
            this.tickPart(this.body, (double)(f2 * 0.5F), 0.0D, (double)(-f14 * 0.5F));
            this.tickPart(this.wing1, (double)(f14 * 4.5F), 2.0D, (double)(f2 * 4.5F));
            this.tickPart(this.wing2, (double)(f14 * -4.5F), 2.0D, (double)(f2 * -4.5F));
            if (!this.level.isClientSide && this.hurtTime == 0) {
                this.knockBack(this.level.getEntities(this, this.wing1.getBoundingBox().inflate(4.0D, 2.0D, 4.0D).move(0.0D, -2.0D, 0.0D), EntitySelector.NO_CREATIVE_OR_SPECTATOR));
                this.knockBack(this.level.getEntities(this, this.wing2.getBoundingBox().inflate(4.0D, 2.0D, 4.0D).move(0.0D, -2.0D, 0.0D), EntitySelector.NO_CREATIVE_OR_SPECTATOR));
                this.hurt(this.level.getEntities(this, this.head.getBoundingBox().inflate(1.0D), EntitySelector.NO_CREATIVE_OR_SPECTATOR));
                this.hurt(this.level.getEntities(this, this.neck.getBoundingBox().inflate(1.0D), EntitySelector.NO_CREATIVE_OR_SPECTATOR));
            }

            float f3 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F) - this.yRotA * 0.01F);
            float f15 = Mth.cos(this.getYRot() * ((float)Math.PI / 180F) - this.yRotA * 0.01F);
            float f4 = this.getHeadYOffset();
            this.tickPart(this.head, (double)(f3 * 6.5F * f12), (double)(f4 + f1 * 6.5F), (double)(-f15 * 6.5F * f12));
            this.tickPart(this.neck, (double)(f3 * 5.5F * f12), (double)(f4 + f1 * 5.5F), (double)(-f15 * 5.5F * f12));
            double[] adouble = this.getLatencyPos(5, 1.0F);

            for(int k = 0; k < 3; ++k) {
                CustomDragonPart enderdragonpart = null;
                if (k == 0) {
                    enderdragonpart = this.tail1;
                }

                if (k == 1) {
                    enderdragonpart = this.tail2;
                }

                if (k == 2) {
                    enderdragonpart = this.tail3;
                }

                double[] adouble1 = this.getLatencyPos(12 + k * 2, 1.0F);
                float f16 = this.getYRot() * ((float)Math.PI / 180F) + this.rotWrap(adouble1[0] - adouble[0]) * ((float)Math.PI / 180F);
                float f17 = Mth.sin(f16);
                float f19 = Mth.cos(f16);
                float f20 = 1.5F;
                float f21 = (float)(k + 1) * 2.0F;
                this.tickPart(enderdragonpart, (double)(-(f2 * 1.5F + f17 * f21) * f12), adouble1[1] - adouble[1] - (double)((f21 + 1.5F) * f1) + 1.5D, (double)((f14 * 1.5F + f19 * f21) * f12));
            }

            if (!this.level.isClientSide) {
                this.inWall = this.checkWalls(this.head.getBoundingBox()) | this.checkWalls(this.neck.getBoundingBox()) | this.checkWalls(this.body.getBoundingBox());
//                    if (this.dragonFight != null) {
//                        this.dragonFight.updateDragon(this);
//                    }
            }

            for(int l = 0; l < this.subEntities.length; ++l) {
                this.subEntities[l].xo = avec3[l].x;
                this.subEntities[l].yo = avec3[l].y;
                this.subEntities[l].zo = avec3[l].z;
                this.subEntities[l].xOld = avec3[l].x;
                this.subEntities[l].yOld = avec3[l].y;
                this.subEntities[l].zOld = avec3[l].z;
            }

        }
    }
    private void tickPart(CustomDragonPart p_31116_, double p_31117_, double p_31118_, double p_31119_) {
        p_31116_.setPos(this.getX() + p_31117_, this.getY() + p_31118_, this.getZ() + p_31119_);
    }
    public boolean isFlapping() {
        float f = Mth.cos(this.flapTime * ((float)Math.PI * 2F));
        float f1 = Mth.cos(this.oFlapTime * ((float)Math.PI * 2F));
        return f1 <= -0.3F && f >= -0.3F;
    }

    public void onFlap() {
        if (this.level.isClientSide && !this.isSilent()) {
            this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ENDER_DRAGON_FLAP, this.getSoundSource(), 5.0F, 0.8F + this.random.nextFloat() * 0.3F, false);
        }

    }
    private float getHeadYOffset() {
        return -1.0F;
    }

    private void knockBack(List<Entity> p_31132_) {
        double d0 = (this.getBoundingBox().minX + this.getBoundingBox().maxX) / 2.0D;
        double d1 = (this.getBoundingBox().minZ + this.getBoundingBox().maxZ) / 2.0D;

        for(Entity entity : p_31132_) {
            if (entity instanceof LivingEntity) {
                double d2 = entity.getX() - d0;
                double d3 = entity.getZ() - d1;
                double d4 = Math.max(d2 * d2 + d3 * d3, 0.1D);
                entity.push(d2 / d4 * 4.0D, (double)0.2F, d3 / d4 * 4.0D);
                if (((LivingEntity)entity).getLastHurtByMobTimestamp() < entity.tickCount - 2) {
                    entity.hurt(DamageSource.mobAttack(this), 5.0F);
                    this.doEnchantDamageEffects(this, entity);
                }
            }
        }

    }
    private void hurt(List<Entity> p_31142_) {
        for(Entity entity : p_31142_) {
            if (entity instanceof LivingEntity) {
                entity.hurt(DamageSource.mobAttack(this), 10.0F);
                this.doEnchantDamageEffects(this, entity);
            }
        }

    }

    private boolean checkWalls(AABB p_31140_) {
        int i = Mth.floor(p_31140_.minX);
        int j = Mth.floor(p_31140_.minY);
        int k = Mth.floor(p_31140_.minZ);
        int l = Mth.floor(p_31140_.maxX);
        int i1 = Mth.floor(p_31140_.maxY);
        int j1 = Mth.floor(p_31140_.maxZ);
        boolean flag = false;
        boolean flag1 = false;

        for(int k1 = i; k1 <= l; ++k1) {
            for(int l1 = j; l1 <= i1; ++l1) {
                for(int i2 = k; i2 <= j1; ++i2) {
                    BlockPos blockpos = new BlockPos(k1, l1, i2);
                    BlockState blockstate = this.level.getBlockState(blockpos);
                    if (!blockstate.isAir() && blockstate.getMaterial() != Material.FIRE) {
                        if (net.minecraftforge.common.ForgeHooks.canEntityDestroy(this.level, blockpos, this) && !BlockTags.DRAGON_IMMUNE.contains(blockstate.getBlock())) {
                            flag1 = this.level.removeBlock(blockpos, false) || flag1;
                        } else {
                            flag = true;
                        }
                    }
                }
            }
        }

        if (flag1) {
            BlockPos blockpos1 = new BlockPos(i + this.random.nextInt(l - i + 1), j + this.random.nextInt(i1 - j + 1), k + this.random.nextInt(j1 - k + 1));
            this.level.levelEvent(2008, blockpos1, 0);
        }

        return flag;
    }

    public boolean hurt(CustomDragonPart p_31121_, DamageSource p_31122_, float p_31123_) {
       {
//            p_31123_ = this.phaseManager.getCurrentPhase().onHurt(p_31122_, p_31123_);
            if (p_31121_ != this.head) {
                p_31123_ = p_31123_ / 4.0F + Math.min(p_31123_, 1.0F);
            }

            if (p_31123_ < 0.01F) {
                return false;
            } else {
                if (p_31122_.getEntity() instanceof Player || p_31122_.isExplosion()) {
                    float f = this.getHealth();
                    this.reallyHurt(p_31122_, p_31123_);
                    if (this.isDeadOrDying()) {
                        this.setHealth(1.0F);
                    }

//                    if (this.phaseManager.getCurrentPhase().isSitting()) {
//                        this.sittingDamageReceived = (int)((float)this.sittingDamageReceived + (f - this.getHealth()));
//                        if ((float)this.sittingDamageReceived > 0.25F * this.getMaxHealth()) {
//                            this.sittingDamageReceived = 0;
//                            this.phaseManager.setPhase(EnderDragonPhase.TAKEOFF);
//                        }
//                    }
                }

                return true;
            }
        }
    }

    public boolean hurt(DamageSource p_31113_, float p_31114_) {
        if (p_31113_ instanceof EntityDamageSource && ((EntityDamageSource)p_31113_).isThorns()) {
            this.hurt(this.body, p_31113_, p_31114_);
        }

        return false;
    }

    protected boolean reallyHurt(DamageSource p_31162_, float p_31163_) {
        return super.hurt(p_31162_, p_31163_);
    }

    public float getHeadPartYOffset(int p_31109_, double[] p_31110_, double[] p_31111_) {
        double d0;

                d0 = (double)p_31109_;
          if (p_31109_ == 6) {
                d0 = 0.0D;
            } else {
                d0 = p_31111_[1] - p_31110_[1];
            }
         {
            BlockPos blockpos = this.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION);
            double d1 = Math.max(Math.sqrt(blockpos.distSqr(this.position(), true)) / 4.0D, 1.0D);
            d0 = (double)p_31109_ / d1;
        }

        return (float)d0;
    }


}