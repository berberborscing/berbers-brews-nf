package net.berber.berbersbrews.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfigs {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();


    public static final ModConfigSpec.BooleanValue RECIPES_ENABLED =
            BUILDER
                    .comment("Enable all potion brewing recipes")
                    .define("recipes.enabled", true);

    public static final ModConfigSpec.BooleanValue TELEPORT_RECIPES_ENABLED =
            BUILDER
                    .comment("Enable brewing recipes for Teleport, Recall, and Return potions")
                    .define("recipes.teleportrecallreturn_enabled", true);

    public static final ModConfigSpec.BooleanValue SILENCE_RECIPES_ENABLED =
            BUILDER
                    .comment("Enable brewing recipes for Silence potions")
                    .define("recipes.silence_enabled", true);

    public static final ModConfigSpec.BooleanValue CLAIRVOYANCE_RECIPES_ENABLED =
            BUILDER
                    .comment("Enable brewing recipes for Clairvoyance potions")
                    .define("recipes.clairvoyance_enabled", true);

    public static final ModConfigSpec.BooleanValue REACH_RECIPES_ENABLED =
            BUILDER
                    .comment("Enable brewing recipes for Reach potions")
                    .define("recipes.reach_enabled", true);

    public static final ModConfigSpec.BooleanValue INTUITION_RECIPES_ENABLED =
            BUILDER
                    .comment("Enable brewing recipes for Intuition potions")
                    .define("recipes.intuition_enabled", true);

    public static final ModConfigSpec.BooleanValue PEACE_RECIPES_ENABLED =
            BUILDER
                    .comment("Enable brewing recipes for Peace potions")
                    .define("recipes.peace_enabled", true);

    public static final ModConfigSpec.BooleanValue PERCEPTION_RECIPES_ENABLED =
            BUILDER
                    .comment("Enable brewing recipes for Perception potions")
                    .define("recipes.peace_enabled", true);

    public static final ModConfigSpec.ConfigValue<String> PERCEPTION_SOUND =
            BUILDER
                    .comment("Set the note block sound that plays when perception potions are used (e.g. \"harp\")")
                    .define("sound.perception", "bit");

    public static final ModConfigSpec SPEC = BUILDER.build();
}