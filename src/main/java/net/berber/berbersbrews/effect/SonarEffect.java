package net.berber.berbersbrews.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SonarEffect extends MobEffect {
    public SonarEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    //All of the code of this effect is in FishingMixin.
}
