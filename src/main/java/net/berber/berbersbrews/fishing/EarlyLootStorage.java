package net.berber.berbersbrews.fishing;

import net.minecraft.world.item.ItemStack;

import java.util.List;

//The purpose of this interface is to recalculate fishing loot at the moment the bobber enters the water, not on reel-in.
public interface EarlyLootStorage {
    void berbers_brews$setEarlyLoot(List<ItemStack> itemStacks);
    List<ItemStack> berbers_brews$getEarlyLoot();
    boolean berbers_brews$hasEarlyLoot();
}
