package com.startechnology.start_core.mixin;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.startechnology.start_core.machine.parallel.IStarTMinimumParallelBlockCache;
import com.startechnology.start_core.machine.parallel.IStarTMinimumParallelHatch;
import com.startechnology.start_core.machine.parallel.StarTAbsoluteParallelHatchMachine;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value=GTRecipeModifiers.class, remap=false)
public class GTRecipeModifiersMixin {

    @Inject(method = "hatchParallel", at = @At("HEAD"), cancellable = true)
    private static void injectHatchParallel(MetaMachine machine, GTRecipe recipe, CallbackInfoReturnable<ModifierFunction> cir) {
        if (machine instanceof IMultiController controller && controller.isFormed()) {
            var hatch = controller.getParallelHatch().orElse(null);
            if (hatch == null) {
                cir.setReturnValue(ModifierFunction.IDENTITY);
                return;
            }

            var currentParallel = hatch.getCurrentParallel();
            var minimumParallels = hatch instanceof IStarTMinimumParallelHatch minHatch ? minHatch.start_core$getMinimumParallels() : 1;
            var isAbsoluteParallel = hatch instanceof StarTAbsoluteParallelHatchMachine;

            if (minimumParallels > 1) {
                var parallelsAtMinimum = start_core$getParallelAmount(machine, recipe, minimumParallels, isAbsoluteParallel);
                if (parallelsAtMinimum < minimumParallels) {
                    start_core$markMinimumParallelBlocked(controller, recipe);
                    cir.setReturnValue(ModifierFunction.cancel(Component.translatable(
                            "start_core.parallel_hatch.jade_min_parallel",
                            minimumParallels)));
                    return;
                }
                if (currentParallel <= minimumParallels) {
                    cir.setReturnValue(start_core$buildParallelModifier(parallelsAtMinimum));
                    return;
                }
            }

            var maximumParallels = start_core$getParallelAmount(machine, recipe, currentParallel, isAbsoluteParallel);

            if (minimumParallels > 1 && maximumParallels < minimumParallels) {
                start_core$markMinimumParallelBlocked(controller, recipe);
                cir.setReturnValue(ModifierFunction.cancel(Component.translatable(
                        "start_core.parallel_hatch.jade_min_parallel",
                        minimumParallels)));
                return;
            }

            cir.setReturnValue(start_core$buildParallelModifier(maximumParallels));
        }
    }

    private static int start_core$getParallelAmount(MetaMachine machine, GTRecipe recipe, int parallelLimit, boolean withoutEU) {
        return withoutEU ?
                ParallelLogic.getParallelAmountWithoutEU(machine, recipe, parallelLimit) :
                ParallelLogic.getParallelAmount(machine, recipe, parallelLimit);
    }

    private static void start_core$markMinimumParallelBlocked(IMultiController controller, GTRecipe recipe) {
        if (controller instanceof IStarTMinimumParallelBlockCache cache) {
            cache.start_core$markMinimumParallelBlocked(recipe);
        }
    }

    private static ModifierFunction start_core$buildParallelModifier(int parallels) {
        if (parallels <= 0) {
            return ModifierFunction.NULL;
        }
        if (parallels == 1) {
            return ModifierFunction.IDENTITY;
        }

        return ModifierFunction.builder()
                .modifyAllContents(ContentModifier.multiplier(parallels))
                .eutMultiplier(parallels)
                .parallels(parallels)
                .build();
    }
}
