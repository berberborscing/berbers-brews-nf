package net.berber.berbersbrews.potion;

import net.berber.berbersbrews.BerbersBrews;
import net.berber.berbersbrews.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, BerbersBrews.MOD_ID);

    public static final Holder<Potion> TELEPORT_POTION = POTIONS.register("teleport_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.TELEPORT, 1, 0)));
    public static final Holder<Potion> STRONG_TELEPORT_POTION = POTIONS.register("strong_teleport_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.TELEPORT, 1, 1)));
    public static final Holder<Potion> RECALL_POTION = POTIONS.register("recall_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.RECALL, 1, 0)));

    public static final Holder<Potion> RETURN_POTION = POTIONS.register("return_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.RETURN, 6000, 0)));
    public static final Holder<Potion> LONG_RETURN_POTION = POTIONS.register("long_return_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.RETURN, 12000, 0)));

    public static final Holder<Potion> SILENCE_POTION = POTIONS.register("silence_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.SILENCE, 3600, 0)));
    public static final Holder<Potion> LONG_SILENCE_POTION = POTIONS.register("long_silence_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.SILENCE, 9600, 0)));

    public static final Holder<Potion> PRIVY_POTION = POTIONS.register("privy_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.PRIVY, 3600, 0)));
    public static final Holder<Potion> LONG_PRIVY_POTION = POTIONS.register("long_privy_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.PRIVY, 9600, 0)));
    public static final Holder<Potion> STRONG_PRIVY_POTION = POTIONS.register("strong_privy_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.PRIVY, 1800, 1)));

    public static final Holder<Potion> REACH_POTION = POTIONS.register("reach_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.REACH, 3600, 0)));
    public static final Holder<Potion> LONG_REACH_POTION = POTIONS.register("long_reach_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.REACH, 9600, 0)));
    public static final Holder<Potion> STRONG_REACH_POTION = POTIONS.register("strong_reach_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.REACH, 1800, 1)));

    public static final Holder<Potion> SONAR_POTION = POTIONS.register("sonar_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.SONAR, 3600, 0)));
    public static final Holder<Potion> LONG_SONAR_POTION = POTIONS.register("long_sonar_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.SONAR, 9600, 0)));

    public static final Holder<Potion> PEACE_POTION = POTIONS.register("peace_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.PEACE, 3600, 0)));
    public static final Holder<Potion> LONG_PEACE_POTION = POTIONS.register("long_peace_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.PEACE, 9600, 0)));

    //Perception potions
    /*
    public static final Holder<Potion> AMETHYST_PERCEPTION_POTION = POTIONS.register("amethystperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.AMETHYST_PERCEPTION, 1800, 0)));
    public static final Holder<Potion> LONG_AMETHYST_PERCEPTION_POTION = POTIONS.register("long_amethystperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.AMETHYST_PERCEPTION, 4800, 0)));
    public static final Holder<Potion> STRONG_AMETHYST_PERCEPTION_POTION = POTIONS.register("strong_amethystperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.AMETHYST_PERCEPTION, 900, 1)));

    public static final Holder<Potion> COAL_PERCEPTION_POTION = POTIONS.register("coalperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.COAL_PERCEPTION, 1800, 0)));
    public static final Holder<Potion> LONG_COAL_PERCEPTION_POTION = POTIONS.register("long_coalperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.COAL_PERCEPTION, 4800, 0)));
    public static final Holder<Potion> STRONG_COAL_PERCEPTION_POTION = POTIONS.register("strong_coalperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.COAL_PERCEPTION, 900, 1)));

    public static final Holder<Potion> COPPER_PERCEPTION_POTION = POTIONS.register("copperperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.COPPER_PERCEPTION, 1800, 0)));
    public static final Holder<Potion> LONG_COPPER_PERCEPTION_POTION = POTIONS.register("long_copperperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.COPPER_PERCEPTION, 4800, 0)));
    public static final Holder<Potion> STRONG_COPPER_PERCEPTION_POTION = POTIONS.register("strong_copperperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.COPPER_PERCEPTION, 900, 1)));

    public static final Holder<Potion> DIAMOND_PERCEPTION_POTION = POTIONS.register("diamondperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.DIAMOND_PERCEPTION, 1800, 0)));
    public static final Holder<Potion> LONG_DIAMOND_PERCEPTION_POTION = POTIONS.register("long_diamondperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.DIAMOND_PERCEPTION, 4800, 0)));
    public static final Holder<Potion> STRONG_DIAMOND_PERCEPTION_POTION = POTIONS.register("strong_diamondperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.DIAMOND_PERCEPTION, 900, 1)));

    public static final Holder<Potion> EMERALD_PERCEPTION_POTION = POTIONS.register("emeraldperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.EMERALD_PERCEPTION, 1800, 0)));
    public static final Holder<Potion> LONG_EMERALD_PERCEPTION_POTION = POTIONS.register("long_emeraldperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.EMERALD_PERCEPTION, 4800, 0)));
    public static final Holder<Potion> STRONG_EMERALD_PERCEPTION_POTION = POTIONS.register("strong_emeraldperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.EMERALD_PERCEPTION, 900, 1)));

    public static final Holder<Potion> GOLD_PERCEPTION_POTION = POTIONS.register("goldperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.GOLD_PERCEPTION, 1800, 0)));
    public static final Holder<Potion> LONG_GOLD_PERCEPTION_POTION = POTIONS.register("long_goldperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.GOLD_PERCEPTION, 4800, 0)));
    public static final Holder<Potion> STRONG_GOLD_PERCEPTION_POTION = POTIONS.register("strong_goldperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.GOLD_PERCEPTION, 900, 1)));

    public static final Holder<Potion> IRON_PERCEPTION_POTION = POTIONS.register("ironperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.IRON_PERCEPTION, 1800, 0)));
    public static final Holder<Potion> LONG_IRON_PERCEPTION_POTION = POTIONS.register("long_ironperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.IRON_PERCEPTION, 4800, 0)));
    public static final Holder<Potion> STRONG_IRON_PERCEPTION_POTION = POTIONS.register("strong_ironperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.IRON_PERCEPTION, 900, 1)));

    public static final Holder<Potion> LAPIS_PERCEPTION_POTION = POTIONS.register("lapisperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.LAPIS_PERCEPTION, 1800, 0)));
    public static final Holder<Potion> LONG_LAPIS_PERCEPTION_POTION = POTIONS.register("long_lapisperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.LAPIS_PERCEPTION, 4800, 0)));
    public static final Holder<Potion> STRONG_LAPIS_PERCEPTION_POTION = POTIONS.register("strong_lapisperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.LAPIS_PERCEPTION, 900, 1)));

    public static final Holder<Potion> MISC_PERCEPTION_POTION = POTIONS.register("miscperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.MISC_PERCEPTION, 1800, 0)));
    public static final Holder<Potion> LONG_MISC_PERCEPTION_POTION = POTIONS.register("long_miscperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.MISC_PERCEPTION, 4800, 0)));
    public static final Holder<Potion> STRONG_MISC_PERCEPTION_POTION = POTIONS.register("strong_miscperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.MISC_PERCEPTION, 900, 1)));

    public static final Holder<Potion> NETHERITE_PERCEPTION_POTION = POTIONS.register("netheriteperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.NETHERITE_PERCEPTION, 1800, 0)));
    public static final Holder<Potion> LONG_NETHERITE_PERCEPTION_POTION = POTIONS.register("long_netheriteperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.NETHERITE_PERCEPTION, 4800, 0)));
    public static final Holder<Potion> STRONG_NETHERITE_PERCEPTION_POTION = POTIONS.register("strong_netheriteperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.NETHERITE_PERCEPTION, 900, 1)));

    public static final Holder<Potion> REDSTONE_PERCEPTION_POTION = POTIONS.register("redstoneperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.REDSTONE_PERCEPTION, 1800, 0)));
    public static final Holder<Potion> LONG_REDSTONE_PERCEPTION_POTION = POTIONS.register("long_redstoneperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.REDSTONE_PERCEPTION, 4800, 0)));
    public static final Holder<Potion> STRONG_REDSTONE_PERCEPTION_POTION = POTIONS.register("strong_redstoneperception_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.REDSTONE_PERCEPTION, 900, 1)));
    */

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
