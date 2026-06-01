package net.berber.berbersbrews.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class PrivyEffect extends MobEffect {
    public PrivyEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        amplifier++;
        double maxDist = 16 * amplifier;
        List<Entity> nearbyList = entity.level().getEntities(entity,
                new AABB(entity.getX()-maxDist, entity.getY()-maxDist, entity.getZ()-maxDist,
                        entity.getX()+maxDist, entity.getY()+maxDist, entity.getZ()+maxDist));
        for(Entity tmp: nearbyList) {
            if(tmp instanceof LivingEntity) {
                ((LivingEntity)tmp).addEffect((new MobEffectInstance(MobEffects.GLOWING, 10, 0, true, false, false)));
            }
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int d, int p) {
        return true;
    }
}
