package net.berber.berbersbrews.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.UUID;

public class ReturnEffect extends MobEffect {
    private static final HashMap<UUID, PositionData> originalPositions = new HashMap<>();

    public ReturnEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    private static class PositionData {
        public final Vec3 position;
        public final ResourceKey<Level> dimension;

        public PositionData(Vec3 position, ResourceKey<Level> dimension) {
            this.position = position;
            this.dimension = dimension;
        }
    }
    
    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
            UUID entityId = entity.getUUID();
            PositionData originalData = originalPositions.get(entityId);

            // If there's no stored original position, it means it's the first application
            if (originalData == null) {
                // Store original position and dimension
                originalPositions.put(entityId, new PositionData(entity.position(), entity.level().dimension()));

                // Teleport entity to spawn
                BlockPos blockPos = entity.level().getSharedSpawnPos();
                Vec3 vec3d = blockPos.getBottomCenter();
                if (entity instanceof ServerPlayer serverPlayerEntity) {
                    entity.changeDimension(serverPlayerEntity.findRespawnPositionAndUseSpawnBlock(false, DimensionTransition.DO_NOTHING));
                } else {
                    vec3d = entity.adjustSpawnLocation((ServerLevel) entity.level(), blockPos).getBottomCenter();
                    entity.randomTeleport(vec3d.x, vec3d.y, vec3d.z, false);
                }
                entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PORTAL_TRAVEL, SoundSource.PLAYERS, 0.25f, 2f);
                entity.resetFallDistance();
            }

            // Check for expiration
            if (entity.getEffect(ModEffects.RETURN) != null && entity.getEffect(ModEffects.RETURN).getDuration() <= 1) {
                // Teleport back to the original position on effect expiration
                if (originalData != null && entity instanceof ServerPlayer serverPlayerEntity) {
                    ServerLevel targetWorld = serverPlayerEntity.getServer().getLevel(originalData.dimension);

                    if (targetWorld != null) {
                        serverPlayerEntity.teleportTo(targetWorld, originalData.position.x, originalData.position.y, originalData.position.z, serverPlayerEntity.getYRot(), serverPlayerEntity.getXRot());
                        targetWorld.playSound(null, originalData.position.x, originalData.position.y, originalData.position.z, SoundEvents.PORTAL_TRAVEL, SoundSource.PLAYERS, 0.25f, 2f);
                    }
                }
                // Clean up the stored position data after teleporting
                originalPositions.remove(entityId);
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
