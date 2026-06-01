package net.berber.berbersbrews;

import net.berber.berbersbrews.effect.ModEffects;
import net.berber.berbersbrews.config.ModConfigs;
import net.berber.berbersbrews.potion.ModPotions;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BerbersBrews.MOD_ID)
public class BerbersBrews {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "berbersbrews";
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public BerbersBrews(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (BerbersBrews) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        //NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        //Register config
        modContainer.registerConfig(ModConfig.Type.COMMON, ModConfigs.SPEC);
        //Register custom effects
        ModEffects.register(modEventBus);
        //Register potions and recipes
        ModPotions.register(modEventBus);
    }

    //Used to calculate the distance between two points
    public static double calcDistance(BlockPos entityPos, BlockPos targetPos) {
        double deltaX = entityPos.getX() - targetPos.getX();
        double deltaY = entityPos.getY() - targetPos.getY();
        double deltaZ = entityPos.getZ() - targetPos.getZ();
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }

    //Used to return the sound effect based on the config options
    public static SoundEvent getPerceptionSound(String config) {
        return switch (config) {
            case "bit" -> SoundEvents.NOTE_BLOCK_BIT.value();
            case "bass" -> SoundEvents.NOTE_BLOCK_BASS.value();
            case "snare" -> SoundEvents.NOTE_BLOCK_SNARE.value();
            case "hat" -> SoundEvents.NOTE_BLOCK_HAT.value();
            case "basedrum" -> SoundEvents.NOTE_BLOCK_BASEDRUM.value();
            case "bell" -> SoundEvents.NOTE_BLOCK_BELL.value();
            case "flute" -> SoundEvents.NOTE_BLOCK_FLUTE.value();
            case "chime" -> SoundEvents.NOTE_BLOCK_CHIME.value();
            case "guitar" -> SoundEvents.NOTE_BLOCK_GUITAR.value();
            case "xylophone" -> SoundEvents.NOTE_BLOCK_XYLOPHONE.value();
            case "iron_xylophone" -> SoundEvents.NOTE_BLOCK_IRON_XYLOPHONE.value();
            case "cow_bell" -> SoundEvents.NOTE_BLOCK_COW_BELL.value();
            case "didgeridoo" -> SoundEvents.NOTE_BLOCK_DIDGERIDOO.value();
            case "banjo" -> SoundEvents.NOTE_BLOCK_BANJO.value();
            case "pling" -> SoundEvents.NOTE_BLOCK_PLING.value();
            case "harp" -> SoundEvents.NOTE_BLOCK_HARP.value();
            default -> SoundEvents.NOTE_BLOCK_BIT.value();
        };
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
