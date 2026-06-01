package net.berber.berbersbrews.effect;

import net.minecraft.core.Position;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class PeaceEffect extends MobEffect {
    public PeaceEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if(!entity.level().isClientSide()) {
            double maxDist = 128;
            int currentMobCount = 0;
            int revisedMobCap = 50 / (amplifier+1); //amplifier suppresses the local mob cap more
            List<Entity> nearbyList = entity.level().getEntities(entity,
                    new AABB(entity.getX()-maxDist, entity.getY()-maxDist, entity.getZ()-maxDist,
                            entity.getX()+maxDist, entity.getY()+maxDist, entity.getZ()+maxDist));
            for(Entity tmp: nearbyList) {
                if(tmp instanceof Monster) {
                    currentMobCount++;
                    //Ignore persistent mobs
                    if (!((Monster) tmp).isPersistenceRequired() && currentMobCount > revisedMobCap) {
                        double distSq = tmp.blockPosition().distToCenterSqr(entity.blockPosition().getBottomCenter());
                        //Only discard entities that are not very close to the player AND not aggroed to the target
                        if (distSq > (24*24) && ((Monster) tmp).getTarget() != entity) {
                            tmp.discard();
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int d, int p) {
        return true;
    }
}
