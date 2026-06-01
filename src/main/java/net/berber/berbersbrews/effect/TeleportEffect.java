package net.berber.berbersbrews.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

public class TeleportEffect extends MobEffect {
    public TeleportEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
    
    private Vector3d tryTeleport(LivingEntity entity, int amplifier) {
        int maxDist = 64 * amplifier;
        double d = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * maxDist;
        double f = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * maxDist;
        double e = 0;
        //If applied in a cave or underground environment, will send you to the surface.
        // Only applies to dimensions with sky light. This means it won't work in the Nether or the End.
        if(!entity.level().canSeeSky((entity.blockPosition())) && entity.level().dimensionType().hasSkyLight()) {
            for (int i = (int) entity.getY() + 1; e == 0 || i >= ((ServerLevel)entity.level()).getLogicalHeight(); i++) {
                if(entity.level().canSeeSky(new BlockPos((int)d, i, (int)f))) {
                    e = i + 1;
                }
            }
        }
        //Otherwise it will just randomly teleport.
        else {
            e = Math.clamp(
                    entity.getY() + (double)(entity.getRandom().nextInt(maxDist) - (maxDist/2)),
                    (double)entity.level().getMinBuildHeight(),
                    (double)(entity.level().getMinBuildHeight() + ((ServerLevel)entity.level()).getLogicalHeight() - 1)
            );
        }
        return new Vector3d(d, e, f);
    }

    //Tipped arrow effect
    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        //This potion should not work on the Ender Dragon.
        if(!entity.level().isClientSide() && !(entity instanceof EnderDragon)) {
            amplifier++;
            boolean teleported = false;
            //The effect will try 50 times before it takes desperate measures.
            for(int j = 0; j < 50; j++) {
                Vector3d triedPos = tryTeleport(entity, amplifier);

                if (entity.isPassenger()) {
                    entity.stopRiding();
                }

                Vec3 vec3d = entity.position();
                if (entity.randomTeleport(triedPos.x, triedPos.y, triedPos.z, true)) {
                    //entity.level().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(entity));
                    SoundSource soundSource;
                    SoundEvent soundEvent;
                    if (entity instanceof Fox) {
                        soundEvent = SoundEvents.FOX_TELEPORT;
                        soundSource = SoundSource.NEUTRAL;
                    } else {
                        soundEvent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                        soundSource = SoundSource.PLAYERS;
                    }

                    entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), soundEvent, soundSource);
                    entity.resetFallDistance();
                    teleported = true;
                    break;
                }
            }
            //It will try 10 more times with increasing desperation before failing.
            if(!teleported) {
                for(int k = 1; k <= 10; k++) {
                    Vector3d triedPos2 = tryTeleport(entity, amplifier + k);

                    if (entity.isPassenger()) {
                        entity.stopRiding();
                    }

                    Vec3 vec3d = entity.position();
                    if (entity.randomTeleport(triedPos2.x, triedPos2.y, triedPos2.z, true)) {
                        //entity.level().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(entity));
                        SoundSource soundSource;
                        SoundEvent soundEvent;
                        if (entity instanceof Fox) {
                            soundEvent = SoundEvents.FOX_TELEPORT;
                            soundSource = SoundSource.NEUTRAL;
                        } else {
                            soundEvent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                            soundSource = SoundSource.PLAYERS;
                        }

                        entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), soundEvent, soundSource);
                        entity.resetFallDistance();
                        teleported = true;
                        break;
                    }
                }
            }
        }
    }

    //Potion effect
    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity entity, int amplifier, double proximity) {
        //This potion should not work on the Ender Dragon.
        if(!entity.level().isClientSide() && !(entity instanceof EnderDragon)) {
            amplifier++;
            boolean teleported = false;
            //The effect will try 50 times before it takes desperate measures.
            for(int j = 0; j < 50; j++) {
                Vector3d triedPos = tryTeleport(entity, amplifier);

                if (entity.isPassenger()) {
                    entity.stopRiding();
                }

                Vec3 vec3d = entity.position();
                if (entity.randomTeleport(triedPos.x, triedPos.y, triedPos.z, true)) {
                    //entity.level().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(entity));
                    SoundSource soundSource;
                    SoundEvent soundEvent;
                    if (entity instanceof Fox) {
                        soundEvent = SoundEvents.FOX_TELEPORT;
                        soundSource = SoundSource.NEUTRAL;
                    } else {
                        soundEvent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                        soundSource = SoundSource.PLAYERS;
                    }

                    entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), soundEvent, soundSource);
                    entity.resetFallDistance();
                    teleported = true;
                    break;
                }
            }
            //It will try 10 more times with increasing desperation before failing.
            if(!teleported) {
                for(int k = 1; k <= 10; k++) {
                    Vector3d triedPos2 = tryTeleport(entity, amplifier + k);

                    if (entity.isPassenger()) {
                        entity.stopRiding();
                    }

                    Vec3 vec3d = entity.position();
                    if (entity.randomTeleport(triedPos2.x, triedPos2.y, triedPos2.z, true)) {
                        //entity.level().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(entity));
                        SoundSource soundSource;
                        SoundEvent soundEvent;
                        if (entity instanceof Fox) {
                            soundEvent = SoundEvents.FOX_TELEPORT;
                            soundSource = SoundSource.NEUTRAL;
                        } else {
                            soundEvent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                            soundSource = SoundSource.PLAYERS;
                        }

                        entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), soundEvent, soundSource);
                        entity.resetFallDistance();
                        teleported = true;
                        break;
                    }
                }
            }
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
