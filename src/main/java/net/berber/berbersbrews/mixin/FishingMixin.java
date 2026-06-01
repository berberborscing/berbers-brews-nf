package net.berber.berbersbrews.mixin;

import net.berber.berbersbrews.effect.ModEffects;
import net.berber.berbersbrews.fishing.EarlyLootStorage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(FishingHook.class)
public abstract class FishingMixin extends Entity implements EarlyLootStorage {

    @Shadow private int nibble;
    @Shadow private int timeUntilHooked;

    @Shadow @Nullable public abstract Player getPlayerOwner();

    @Unique
    private List<ItemStack> earlyLoot = List.of();

    @Unique
    boolean flag = false; //Used to prevent repeated loot generation; only randomize loot once

    //Cycle iterates both i and deployCounter every 20 ticks (1 second).
    //i is used to display multiple items. deployCounter is the potion's pity mechanic
    @Unique
    int i, cycle, deployCounter = 0;

    @Override
    public void berbers_brews$setEarlyLoot(List<ItemStack> loot) {
        this.earlyLoot = loot;
    }

    @Override
    public List<ItemStack> berbers_brews$getEarlyLoot() {
        return earlyLoot;
    }

    @Override
    public boolean berbers_brews$hasEarlyLoot() {
        return !earlyLoot.isEmpty();
    }

    public FishingMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    //This is where most of the effect's code is
    @Inject(method="tick", at = @At("HEAD"))
    private void onFishingTick(CallbackInfo ci) {
        //
        if (this instanceof EarlyLootStorage lootStorage && !lootStorage.berbers_brews$hasEarlyLoot()) {
            if (!this.level().isClientSide()) {
                berbers_brews_nf$generateLootEarly((FishingHook) (Object) this, lootStorage);
            }
        }
        if(this instanceof EarlyLootStorage lootStorage && lootStorage.berbers_brews$hasEarlyLoot() && this.getPlayerOwner() != null) {
            cycle++;
            if(cycle > 20) { //iterate i and deploycounter
                i = i + 1;
                deployCounter = deployCounter + 1;
                cycle = 0;
            }
            if(i >= lootStorage.berbers_brews$getEarlyLoot().size()) {
                i = 0; //prevents game crash
            }
            this.setCustomName(Component.literal(lootStorage.berbers_brews$getEarlyLoot().get(i).getHoverName().getString()));
            if(this.getPlayerOwner().hasEffect(ModEffects.SONAR)) { //only applies when sonar effect is active
                if(this.timeUntilHooked > 0 || this.nibble > 0 || deployCounter > 4) {
                    //used for debugging
                    //this.getPlayerOwner().sendMessage(Text.literal("i: " + i + ", cycle: " + cycle + ", deployCounter: " + deployCounter + ", earlyLootSize: " + berbers_brews$getEarlyLoot().size()), true);
                    this.setCustomNameVisible(true); //only show what's on the line when the little particles are coming OR when 5 seconds have passed
                }
            } else { //Immediately make the name tag invisible when it wears off
                this.setCustomNameVisible(false);
            }
        }
        //Reroll loot if the bite is missed
        if (this.nibble <= 0) {
            if (this instanceof EarlyLootStorage lootStorage) {
                if(!flag) {
                    berbers_brews_nf$generateLootEarly((FishingHook) (Object) this, lootStorage);
                    deployCounter = 0; //Reset the pity mechanic
                    this.setCustomNameVisible(false); //Fixes a bug where the deployCounter isn't obeyed when the line isn't pulled
                    flag = true;
                }
            }
        } else { //Reset this flag when something bites and there is still time
            flag = false;
        }
    }

    //method used to roll loot on command (exactly as done in vanilla)
    @Unique
    private void berbers_brews_nf$generateLootEarly(FishingHook bobber, EarlyLootStorage lootStorage) {
        ServerLevel world = null; ServerPlayer player = null;
        if(bobber.level() instanceof ServerLevel) {
            world = (ServerLevel) bobber.level();
        } else if (player == null) { return; }
        if(bobber.getPlayerOwner() instanceof ServerPlayer) {
            player = (ServerPlayer) bobber.getPlayerOwner();
        } else if (world == null) { return; }

        if (player == null) return;
        //Get the fishing rod from either the main hand or the offhand
        ItemStack tool = player.getMainHandItem();

        if (!(tool.getItem() instanceof FishingRodItem)) {
            tool = player.getOffhandItem();
        }

        // Build the LootContextParameterSet first
        LootParams.Builder paramBuilder = new LootParams.Builder(world)
                .withParameter(LootContextParams.ORIGIN, bobber.position())
                .withParameter(LootContextParams.TOOL, tool)
                .withParameter(LootContextParams.THIS_ENTITY, bobber)
                .withLuck(player.getLuck());

        LootParams paramSet = paramBuilder.create(LootContextParamSets.FISHING);
        LootTable fishingLootTable = world.getServer().reloadableRegistries().getLootTable(BuiltInLootTables.FISHING);
        List<ItemStack> drops = fishingLootTable.getRandomItems(paramSet);

        if (!drops.isEmpty()) {
            lootStorage.berbers_brews$setEarlyLoot(drops.stream() //Store ALL items, enables mod functionality like Cobblemon's Poke Rod templates
                    .map(ItemStack::copy)
                    .toList());

            this.setCustomName(Component.literal(lootStorage.berbers_brews$getEarlyLoot().get(0).getHoverName().getString())); //Start by displaying the first item on the line
        }
    }

    //Overwrite the ItemStack at reel-in with the pregenerated one
    @ModifyArg(
            method = "retrieve",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/item/ItemEntity;<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V"
            ),
            index = 4 //ItemStack arg
    )
    private ItemStack replaceCaughtItem(ItemStack original) {
        FishingHook bobber = (FishingHook) (Object) this;

        if (bobber instanceof EarlyLootStorage storage && storage.berbers_brews$hasEarlyLoot()) {
            List<ItemStack> loot = storage.berbers_brews$getEarlyLoot();

            return loot.get(0).copy(); //only replace the first item
        }

        return original; //fallback to vanilla loot
    }

    //Mod compatibility (i.g., Cobblemon Smithing Templates)
    @Inject(
            method = "retrieve",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z",
                    shift = At.Shift.AFTER
            )
    )
    private void dropExtraLoot(ItemStack usedItem, CallbackInfoReturnable<Integer> cir) {
        FishingHook self = (FishingHook)(Object)this;

        if (self instanceof EarlyLootStorage storage && storage.berbers_brews$hasEarlyLoot()) {
            List<ItemStack> loot = storage.berbers_brews$getEarlyLoot();
            storage.berbers_brews$setEarlyLoot(List.of());

            //skip first item since it was used in replaceCaughtItem
            for (int i = 1; i < loot.size(); i++) {
                ItemEntity extra = new ItemEntity(self.level(), self.getX(), self.getY(), self.getZ(), loot.get(i).copy());
                if (self.getPlayerOwner() != null) {
                    double dx = self.getPlayerOwner().getX() - self.getX();
                    double dy = self.getPlayerOwner().getEyeY() - self.getY();
                    double dz = self.getPlayerOwner().getZ() - self.getZ();
                    double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
                    double velocityFactor = 1;

                    extra.setDeltaMovement(dx / dist * velocityFactor,
                            dy / dist * velocityFactor,
                            dz / dist * velocityFactor);
                }
                self.level().addFreshEntity(extra);
            }
        }
    }
}