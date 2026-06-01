package net.berber.berbersbrews.effect.perception;

import net.berber.berbersbrews.BerbersBrews;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static net.berber.berbersbrews.config.ModConfigs.PERCEPTION_SOUND;

public class MiscPerceptionEffect extends MobEffect {
    public MiscPerceptionEffect(MobEffectCategory mobEffectCategory, int color) {
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

            // Create a TagKey for ores
            TagKey<Block> diamondOreTag = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "diamond_ores"));
            TagKey<Block> emeraldOreTag = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "emerald_ores"));
            TagKey<Block> goldOreTag = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "gold_ores"));
            TagKey<Block> lapisOreTag = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "lapis_ores"));
            TagKey<Block> ironOreTag = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "iron_ores"));
            TagKey<Block> redstoneOreTag = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "redstone_ores"));
            TagKey<Block> coalOreTag = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "coal_ores"));
            TagKey<Block> copperOreTag = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "copper_ores"));
            TagKey<Block> oreTag = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores"));

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
                            //The following code here is to except these ores from being detected.
                            if (block == Blocks.ANCIENT_DEBRIS) {
                            } else if (block.builtInRegistryHolder().is(diamondOreTag)) {
                            } else if (block.builtInRegistryHolder().is(emeraldOreTag)) {
                            } else if (block.builtInRegistryHolder().is(goldOreTag)) {
                            } else if (block.builtInRegistryHolder().is(lapisOreTag)) {
                            } else if (block.builtInRegistryHolder().is(ironOreTag)) {
                            } else if (block.builtInRegistryHolder().is(redstoneOreTag)) {
                            } else if (block.builtInRegistryHolder().is(coalOreTag)) {
                            } else if (block.builtInRegistryHolder().is(copperOreTag)) {
                            }
                            //This covers all non-vanilla blocks marked as ores. Includes Nether Quartz ore, evolution stone ore from Cobblemon, etc.
                            else if (block.builtInRegistryHolder().is(oreTag) || block == Blocks.CHEST) {
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