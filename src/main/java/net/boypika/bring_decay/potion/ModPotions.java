package net.boypika.bring_decay.potion;

import net.boypika.bring_decay.Bring_Decay;
import net.boypika.bring_decay.mixin.BrewingRecipeRegistryMixin;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.entity.effect.StatusEffects;



public class ModPotions {
    public static Potion DECAY_POTION;
    public static Potion registerPotion(String name) {
        return Registry.register(Registries.POTION, new Identifier(Bring_Decay.MOD_ID, name),
                new Potion(new StatusEffectInstance(StatusEffects.WITHER, 800, 1)));
    }
    public static void registerPotions() {
        DECAY_POTION = registerPotion("decay_potion");
        if (Bring_Decay.tulipInstance.getBoolean("brewable_decay_potion")) {
            BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.WITHER_ROSE, DECAY_POTION);
        }
    }
}
