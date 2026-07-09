package com.startechnology.start_core.machine.parallel;

import com.gregtechceu.gtceu.api.recipe.GTRecipe;

public interface IStarTMinimumParallelBlockCache {
    void start_core$markMinimumParallelBlocked(GTRecipe recipe);

    boolean start_core$wasMinimumParallelBlocked(GTRecipe recipe);

    boolean start_core$isMinimumParallelRetryCoolingDown();

    void start_core$clearMinimumParallelBlocks();
}
