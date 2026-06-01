package net.berber.berbersbrews.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ReachEffect extends MobEffect {
    public ReachEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if(!entity.level().isClientSide()) {
            if(entity instanceof Player) {
                amplifier++;
                double maxDist = 4 + (amplifier * 2);
                List<Entity> nearbyList = entity.level().getEntities(entity,
                        new AABB(entity.getX()-maxDist, entity.getY()-maxDist, entity.getZ()-maxDist,
                                entity.getX()+maxDist, entity.getY()+maxDist, entity.getZ()+maxDist));
                for(Entity tmp: nearbyList) {
                    if(tmp instanceof ItemEntity) { tmp.playerTouch((Player) entity); }
                    if(tmp instanceof ExperienceOrb) { tmp.playerTouch((Player) entity); }
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
