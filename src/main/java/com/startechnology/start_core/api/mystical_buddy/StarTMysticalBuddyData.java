package com.startechnology.start_core.api.mystical_buddy;

import java.util.Locale;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public final class StarTMysticalBuddyData {
    private static final String LEVEL = "Level";
    private static final String EXPERIENCE = "Experience";
    private static final String RARITY = "Rarity";
    private static final String HUNGER = "Hunger";
    private static final String HAPPINESS = "Happiness";

    private static final int DEFAULT_LEVEL = 1;
    private static final int DEFAULT_EXPERIENCE = 0;
    private static final int DEFAULT_HUNGER = 100;
    private static final int DEFAULT_HAPPINESS = 100;
    private static final StarTMysticalBuddyRarity DEFAULT_RARITY = StarTMysticalBuddyRarity.WARY;

    private StarTMysticalBuddyData() {
    }

    public static void initialize(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains(LEVEL)) {
            tag.putInt(LEVEL, DEFAULT_LEVEL);
        }
        if (!tag.contains(EXPERIENCE)) {
            tag.putInt(EXPERIENCE, DEFAULT_EXPERIENCE);
        }
        if (!tag.contains(RARITY)) {
            tag.putString(RARITY, DEFAULT_RARITY.name());
        }
        if (!tag.contains(HUNGER)) {
            tag.putInt(HUNGER, DEFAULT_HUNGER);
        }
        if (!tag.contains(HAPPINESS)) {
            tag.putInt(HAPPINESS, DEFAULT_HAPPINESS);
        }
    }

    public static int getLevel(ItemStack stack) {
        initialize(stack);
        return stack.getOrCreateTag().getInt(LEVEL);
    }

    public static int getExperience(ItemStack stack) {
        initialize(stack);
        return stack.getOrCreateTag().getInt(EXPERIENCE);
    }

    public static StarTMysticalBuddyRarity getRarity(ItemStack stack) {
        initialize(stack);
        String name = stack.getOrCreateTag().getString(RARITY);
        try {
            return StarTMysticalBuddyRarity.valueOf(name.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ignored) {
            setRarity(stack, DEFAULT_RARITY);
            return DEFAULT_RARITY;
        }
    }

    public static int getHunger(ItemStack stack) {
        initialize(stack);
        return stack.getOrCreateTag().getInt(HUNGER);
    }

    public static int getHappiness(ItemStack stack) {
        initialize(stack);
        return stack.getOrCreateTag().getInt(HAPPINESS);
    }

    public static void setRarity(ItemStack stack, StarTMysticalBuddyRarity rarity) {
        stack.getOrCreateTag().putString(RARITY, rarity.name());
    }
}
