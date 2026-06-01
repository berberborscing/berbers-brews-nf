package net.berber.berbersbrews.effect;

import net.berber.berbersbrews.BerbersBrews;
import net.berber.berbersbrews.effect.perception.*;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, BerbersBrews.MOD_ID);

    //STANDARD EFFECTS
    public static final Holder<MobEffect> TELEPORT = MOB_EFFECTS.register("teleport",
            () -> new TeleportEffect(MobEffectCategory.NEUTRAL, 9830550));

    public static final Holder<MobEffect> RECALL = MOB_EFFECTS.register("recall",
            () -> new RecallEffect(MobEffectCategory.BENEFICIAL, 13158600));

    public static final Holder<MobEffect> RETURN = MOB_EFFECTS.register("return",
            () -> new ReturnEffect(MobEffectCategory.NEUTRAL, 16777130));

    public static final Holder<MobEffect> SILENCE = MOB_EFFECTS.register("silence",
            () -> new SilenceEffect(MobEffectCategory.BENEFICIAL, 25720));

    public static final Holder<MobEffect> PRIVY = MOB_EFFECTS.register("privy",
            () -> new PrivyEffect(MobEffectCategory.BENEFICIAL, 16425120));

    public static final Holder<MobEffect> REACH = MOB_EFFECTS.register("reach",
            () -> new ReachEffect(MobEffectCategory.BENEFICIAL, 6579255)
                    .addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE,
                            ResourceLocation.fromNamespaceAndPath(BerbersBrews.MOD_ID, "reach"), 0.5f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE,
                            ResourceLocation.fromNamespaceAndPath(BerbersBrews.MOD_ID, "reach"), 0.5f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static final Holder<MobEffect> SONAR = MOB_EFFECTS.register("sonar",
            () -> new SonarEffect(MobEffectCategory.BENEFICIAL, 56495));

    public static final Holder<MobEffect> PEACE = MOB_EFFECTS.register("peace",
            () -> new PeaceEffect(MobEffectCategory.BENEFICIAL, 12618239));

    //PERCEPTION EFFECTS
    public static final Holder<MobEffect> AMETHYST_PERCEPTION = MOB_EFFECTS.register("amethystperception",
            () -> new AmethystPerceptionEffect(MobEffectCategory.BENEFICIAL, 11767539));

    public static final Holder<MobEffect> COAL_PERCEPTION = MOB_EFFECTS.register("coalperception",
            () -> new CoalPerceptionEffect(MobEffectCategory.BENEFICIAL, 3289650));

    public static final Holder<MobEffect> COPPER_PERCEPTION = MOB_EFFECTS.register("copperperception",
            () -> new CopperPerceptionEffect(MobEffectCategory.BENEFICIAL, 15301202));

    public static final Holder<MobEffect> DIAMOND_PERCEPTION = MOB_EFFECTS.register("diamondperception",
            () -> new DiamondPerceptionEffect(MobEffectCategory.BENEFICIAL, 4910553));

    public static final Holder<MobEffect> EMERALD_PERCEPTION = MOB_EFFECTS.register("emeraldperception",
            () -> new EmeraldPerceptionEffect(MobEffectCategory.BENEFICIAL, 4322180));

    public static final Holder<MobEffect> GOLD_PERCEPTION = MOB_EFFECTS.register("goldperception",
            () -> new GoldPerceptionEffect(MobEffectCategory.BENEFICIAL, 16443950));

    public static final Holder<MobEffect> IRON_PERCEPTION = MOB_EFFECTS.register("ironperception",
            () -> new IronPerceptionEffect(MobEffectCategory.BENEFICIAL, 16703176));

    public static final Holder<MobEffect> LAPIS_PERCEPTION = MOB_EFFECTS.register("lapisperception",
            () -> new LapisPerceptionEffect(MobEffectCategory.BENEFICIAL, 5931746));

    public static final Holder<MobEffect> MISC_PERCEPTION = MOB_EFFECTS.register("miscperception",
            () -> new MiscPerceptionEffect(MobEffectCategory.BENEFICIAL, 15065046));

    public static final Holder<MobEffect> NETHERITE_PERCEPTION = MOB_EFFECTS.register("netheriteperception",
            () -> new NetheritePerceptionEffect(MobEffectCategory.BENEFICIAL, 6637376));

    public static final Holder<MobEffect> REDSTONE_PERCEPTION = MOB_EFFECTS.register("redstoneperception",
            () -> new RedstonePerceptionEffect(MobEffectCategory.BENEFICIAL, 16711680));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
