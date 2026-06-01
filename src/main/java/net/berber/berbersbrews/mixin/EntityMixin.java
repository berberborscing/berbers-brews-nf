package net.berber.berbersbrews.mixin;

import net.berber.berbersbrews.effect.ModEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.berber.berbersbrews.effect.ModEffects.SILENCE;

@Mixin(Entity.class)
public abstract class EntityMixin {

    //Primary silence potion effect where it suppresses sculk sensor reactions
    @Inject(method = "dampensVibrations", at = @At("TAIL"), cancellable = true)
    private void occludeVibrationSignals(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof LivingEntity living
                && living.hasEffect(ModEffects.SILENCE)) {
            cir.setReturnValue(true);
        }
    }

    //This code stifles audio from Silenced entities.
    @ModifyArg(method = "playSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"), index = 6)
    private float adjustVolume(float vol) {
        if((Object)this instanceof LivingEntity) {
            if(((LivingEntity) (Object) this).hasEffect(SILENCE)) {
                return 0f;
            } else {
                return vol;
            }
        }
        else {
            return vol;
        }
    }

    //Assigns highlighting colors for the Potion of Privy.
    @Inject(method="getTeamColor", at = @At(value= "TAIL"), cancellable = true)
    private void setTeams(CallbackInfoReturnable<Integer> cir) {
        Entity entity = (Entity)(Object)this;

        if (entity instanceof Monster) { cir.setReturnValue(0xFF0000); }
        else if (entity instanceof Hoglin) { cir.setReturnValue(0xFF0000); }
        else if (entity instanceof Animal) { cir.setReturnValue(0x00FF00); }
        else if (entity instanceof AbstractSchoolingFish) { cir.setReturnValue(0x00FF00); }
        else if (entity instanceof Shulker) { cir.setReturnValue(0xFF0000); }
        else if (entity instanceof AbstractGolem) { cir.setReturnValue(0x00FF00); }
        else if (entity instanceof Allay) { cir.setReturnValue(0x00FF00); }
        else if (entity instanceof Bat) { cir.setReturnValue(0x00FF00); }
        else if (entity instanceof FlyingMob) { cir.setReturnValue(0xFF0000); }
        else if (entity instanceof Slime) { cir.setReturnValue(0xFF0000); }
        else if (entity instanceof AbstractVillager) { cir.setReturnValue(0x00FF00); }
        else { cir.setReturnValue(0xFFFFFF); }
    }
}
