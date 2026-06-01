package net.berber.berbersbrews.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class RecallEffect extends MobEffect {
    public RecallEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
    //Tipped arrow effect
    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        if(!entity.level().isClientSide() && !(entity instanceof EnderDragon)) {
            BlockPos blockPos = entity.level().getSharedSpawnPos();
            Vec3 vec3d = blockPos.getBottomCenter();
            if (entity instanceof ServerPlayer serverPlayerEntity) {
                entity.changeDimension(serverPlayerEntity.findRespawnPositionAndUseSpawnBlock(false, DimensionTransition.DO_NOTHING));
            } else if (entity instanceof TamableAnimal tameableEntity && tameableEntity.isTame()) {
                //If the affected pet isn't sitting, make it sit so it doesn't bug out the teleportation
                if(!tameableEntity.isInSittingPose()) { tameableEntity.setOrderedToSit(true); }
                ServerPlayer owner = (ServerPlayer) tameableEntity.getOwner();
                //Prevents a null pointer crash. Can only recall pets if their owner is currently online.
                if(owner != null)
                    entity.changeDimension(owner.findRespawnPositionAndUseSpawnBlock(false, DimensionTransition.DO_NOTHING));
            } else {
                vec3d = entity.adjustSpawnLocation((ServerLevel) entity.level(), blockPos).getBottomCenter();
                entity.randomTeleport(vec3d.x, vec3d.y, vec3d.z, false);
            }
            entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PORTAL_TRAVEL, SoundSource.PLAYERS, 0.25f, 2f);
            entity.resetFallDistance();
        }
    }

    //Potion effect
    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity entity, int amplifier, double proximity) {
        //This potion should not work on the Ender Dragon.
        if(!entity.level().isClientSide() && !(entity instanceof EnderDragon)) {
            BlockPos blockPos = entity.level().getSharedSpawnPos();
            Vec3 vec3d = blockPos.getBottomCenter();
            if (entity instanceof ServerPlayer serverPlayerEntity) {
                entity.changeDimension(serverPlayerEntity.findRespawnPositionAndUseSpawnBlock(false, DimensionTransition.DO_NOTHING));
            } else if (entity instanceof TamableAnimal tameableEntity && tameableEntity.isTame()) {
                //If the affected pet isn't sitting, make it sit so it doesn't bug out the teleportation
                if(!tameableEntity.isInSittingPose()) { tameableEntity.setOrderedToSit(true); }
                ServerPlayer owner = (ServerPlayer) tameableEntity.getOwner();
                if(owner != null)
                    entity.changeDimension(owner.findRespawnPositionAndUseSpawnBlock(false, DimensionTransition.DO_NOTHING));
            } else {
                vec3d = entity.adjustSpawnLocation((ServerLevel) entity.level(), blockPos).getBottomCenter();
                entity.randomTeleport(vec3d.x, vec3d.y, vec3d.z, false);
            }
            entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PORTAL_TRAVEL, SoundSource.PLAYERS, 0.25f, 2f);
            entity.resetFallDistance();
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }
}
