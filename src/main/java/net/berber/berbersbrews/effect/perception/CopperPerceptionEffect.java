package net.berber.berbersbrews.effect.perception;

import net.berber.berbersbrews.BerbersBrews;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import static net.berber.berbersbrews.config.ModConfigs.PERCEPTION_SOUND;

public class CopperPerceptionEffect extends MobEffect {
    public CopperPerceptionEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) {
            Level world = entity.level();
            BlockPos playerPos = entity.blockPosition();
            amplifier++;
            amplifier = Math.min(2, amplifier);

            // Adjust range as needed (3 here means 3 blocks away)
            int range = 8 * amplifier;

            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    for (int z = -range; z <= range; z++) {
                        BlockPos targetPos = playerPos.offset(x, y, z);
                        //Get the distance between player and this block. Don't bother running anything if > 16.
                        double distance = BerbersBrews.calcDistance(entity.blockPosition(), targetPos);
                        if(distance <= 16) {
                            Block block = world.getBlockState(targetPos).getBlock();
                            float pitch = 2 - ((float) distance / 10f);
                            // Get the block's ID and check against the ore tag
                            ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
                            //This includes ALL copper blocks, including those variants only generated in Trial Chambers.
                            if (blockId.getPath().contains("copper")) {
                                //Ping the ore faster the closer it is
                                if (entity.level().getGameTime() % ((int) distance + 2) == 0) {
                                    //Use the configured sound
                                    world.playSound(entity, targetPos, BerbersBrews.getPerceptionSound(String.valueOf(PERCEPTION_SOUND)), SoundSource.BLOCKS, 1f, pitch);
                                }
                            }
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