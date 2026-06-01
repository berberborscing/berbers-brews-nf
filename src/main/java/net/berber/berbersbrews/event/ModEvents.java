package net.berber.berbersbrews.event;

import net.berber.berbersbrews.BerbersBrews;
import net.berber.berbersbrews.config.ModConfigs;
import net.berber.berbersbrews.potion.ModPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = BerbersBrews.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        if(ModConfigs.RECIPES_ENABLED.get()) {
            if(ModConfigs.TELEPORT_RECIPES_ENABLED.get()) {
                builder.addMix(Potions.AWKWARD, Items.CHORUS_FRUIT, ModPotions.TELEPORT_POTION);
                builder.addMix(ModPotions.TELEPORT_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_TELEPORT_POTION);
                builder.addMix(ModPotions.TELEPORT_POTION, Items.FERMENTED_SPIDER_EYE, ModPotions.RECALL_POTION);
                builder.addMix(ModPotions.RECALL_POTION, Items.CLOCK, ModPotions.RETURN_POTION);
                builder.addMix(ModPotions.RETURN_POTION, Items.REDSTONE, ModPotions.LONG_RETURN_POTION);
            }

            if(ModConfigs.SILENCE_RECIPES_ENABLED.get()) {
                builder.addMix(Potions.AWKWARD, Items.ECHO_SHARD, ModPotions.SILENCE_POTION);
                builder.addMix(ModPotions.SILENCE_POTION, Items.REDSTONE, ModPotions.LONG_SILENCE_POTION);
            }

            if(ModConfigs.CLAIRVOYANCE_RECIPES_ENABLED.get()) {
                builder.addMix(Potions.AWKWARD, Items.GLOW_BERRIES, ModPotions.PRIVY_POTION);
                builder.addMix(ModPotions.PRIVY_POTION, Items.REDSTONE, ModPotions.LONG_PRIVY_POTION);
                builder.addMix(ModPotions.PRIVY_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_PRIVY_POTION);
            }

            if(ModConfigs.REACH_RECIPES_ENABLED.get()) {
                builder.addMix(Potions.AWKWARD, Items.ENDER_PEARL, ModPotions.REACH_POTION);
                builder.addMix(ModPotions.REACH_POTION, Items.REDSTONE, ModPotions.LONG_REACH_POTION);
                builder.addMix(ModPotions.REACH_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_REACH_POTION);
            }

            if(ModConfigs.INTUITION_RECIPES_ENABLED.get()) {
                builder.addMix(Potions.AWKWARD, Items.PRISMARINE_CRYSTALS, ModPotions.SONAR_POTION);
                builder.addMix(ModPotions.SONAR_POTION, Items.REDSTONE, ModPotions.LONG_SONAR_POTION);
            }

            if(ModConfigs.PEACE_RECIPES_ENABLED.get()) {
                builder.addMix(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.PEACE_POTION);
                builder.addMix(ModPotions.PEACE_POTION, Items.REDSTONE, ModPotions.LONG_PEACE_POTION);
            }

            if(ModConfigs.PERCEPTION_RECIPES_ENABLED.get()) {
                builder.addMix(Potions.THICK, Items.AMETHYST_SHARD, ModPotions.AMETHYST_PERCEPTION_POTION);
                builder.addMix(ModPotions.AMETHYST_PERCEPTION_POTION, Items.REDSTONE, ModPotions.LONG_AMETHYST_PERCEPTION_POTION);
                builder.addMix(ModPotions.AMETHYST_PERCEPTION_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_AMETHYST_PERCEPTION_POTION);

                builder.addMix(Potions.THICK, Items.COAL, ModPotions.COAL_PERCEPTION_POTION);
                builder.addMix(ModPotions.COAL_PERCEPTION_POTION, Items.REDSTONE, ModPotions.LONG_COAL_PERCEPTION_POTION);
                builder.addMix(ModPotions.COAL_PERCEPTION_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_COAL_PERCEPTION_POTION);


                builder.addMix(Potions.THICK, Items.RAW_COPPER, ModPotions.COPPER_PERCEPTION_POTION);
                builder.addMix(ModPotions.COPPER_PERCEPTION_POTION, Items.REDSTONE, ModPotions.LONG_COPPER_PERCEPTION_POTION);
                builder.addMix(ModPotions.COPPER_PERCEPTION_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_COPPER_PERCEPTION_POTION);


                builder.addMix(Potions.THICK, Items.DIAMOND, ModPotions.DIAMOND_PERCEPTION_POTION);
                builder.addMix(ModPotions.DIAMOND_PERCEPTION_POTION, Items.REDSTONE, ModPotions.LONG_DIAMOND_PERCEPTION_POTION);
                builder.addMix(ModPotions.DIAMOND_PERCEPTION_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_DIAMOND_PERCEPTION_POTION);

                builder.addMix(Potions.THICK, Items.EMERALD, ModPotions.EMERALD_PERCEPTION_POTION);
                builder.addMix(ModPotions.EMERALD_PERCEPTION_POTION, Items.REDSTONE, ModPotions.LONG_EMERALD_PERCEPTION_POTION);
                builder.addMix(ModPotions.EMERALD_PERCEPTION_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_EMERALD_PERCEPTION_POTION);

                builder.addMix(Potions.THICK, Items.RAW_GOLD, ModPotions.GOLD_PERCEPTION_POTION);
                builder.addMix(ModPotions.GOLD_PERCEPTION_POTION, Items.REDSTONE, ModPotions.LONG_GOLD_PERCEPTION_POTION);
                builder.addMix(ModPotions.GOLD_PERCEPTION_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_GOLD_PERCEPTION_POTION);

                builder.addMix(Potions.THICK, Items.RAW_IRON, ModPotions.IRON_PERCEPTION_POTION);
                builder.addMix(ModPotions.IRON_PERCEPTION_POTION, Items.REDSTONE, ModPotions.LONG_IRON_PERCEPTION_POTION);
                builder.addMix(ModPotions.IRON_PERCEPTION_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_IRON_PERCEPTION_POTION);

                builder.addMix(Potions.THICK, Items.LAPIS_LAZULI, ModPotions.LAPIS_PERCEPTION_POTION);
                builder.addMix(ModPotions.LAPIS_PERCEPTION_POTION, Items.REDSTONE, ModPotions.LONG_LAPIS_PERCEPTION_POTION);
                builder.addMix(ModPotions.LAPIS_PERCEPTION_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_LAPIS_PERCEPTION_POTION);

                builder.addMix(Potions.THICK, Items.QUARTZ, ModPotions.MISC_PERCEPTION_POTION);
                builder.addMix(ModPotions.MISC_PERCEPTION_POTION, Items.REDSTONE, ModPotions.LONG_MISC_PERCEPTION_POTION);
                builder.addMix(ModPotions.MISC_PERCEPTION_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_MISC_PERCEPTION_POTION);

                builder.addMix(Potions.THICK, Items.NETHERITE_SCRAP, ModPotions.NETHERITE_PERCEPTION_POTION);
                builder.addMix(ModPotions.NETHERITE_PERCEPTION_POTION, Items.REDSTONE, ModPotions.LONG_NETHERITE_PERCEPTION_POTION);
                builder.addMix(ModPotions.NETHERITE_PERCEPTION_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_NETHERITE_PERCEPTION_POTION);

                builder.addMix(Potions.THICK, Items.REDSTONE, ModPotions.REDSTONE_PERCEPTION_POTION);
                builder.addMix(ModPotions.REDSTONE_PERCEPTION_POTION, Items.REDSTONE, ModPotions.LONG_REDSTONE_PERCEPTION_POTION);
                builder.addMix(ModPotions.REDSTONE_PERCEPTION_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_REDSTONE_PERCEPTION_POTION);
            }
        }
    }
}
