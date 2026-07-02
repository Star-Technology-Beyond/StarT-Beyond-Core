package com.startechnology.start_core.api.mystical_buddy;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public enum StarTMysticalBuddyRarity {
    WARY("start_core.mystical_buddy.rarity.wary", 1, 9, 1, ChatFormatting.WHITE),
    ATTUNED("start_core.mystical_buddy.rarity.attuned", 10, 19, 2, ChatFormatting.GREEN),
    DEVOTED("start_core.mystical_buddy.rarity.devoted", 20, 29, 3, ChatFormatting.AQUA),
    KINDRED("start_core.mystical_buddy.rarity.kindred", 30, 39, 4, ChatFormatting.LIGHT_PURPLE),
    SOULBOUND("start_core.mystical_buddy.rarity.soulbound", 40, 49, 5, ChatFormatting.GOLD),
    UNIFIED("start_core.mystical_buddy.rarity.unified", 50, 50, 6, ChatFormatting.RED);

    private final String translationKey;
    private final int minLevel;
    private final int maxLevel;
    private final int attributeSlots;
    private final ChatFormatting color;

    StarTMysticalBuddyRarity(String translationKey, int minLevel, int maxLevel, int attributeSlots,
                             ChatFormatting color) {
        this.translationKey = translationKey;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.attributeSlots = attributeSlots;
        this.color = color;
    }

    public int minLevel() {
        return minLevel;
    }

    public int maxLevel() {
        return maxLevel;
    }

    public int attributeSlots() {
        return attributeSlots;
    }

    public Component displayComponent() {
        return Component.translatable(translationKey)
                .withStyle(color);
    }
}
