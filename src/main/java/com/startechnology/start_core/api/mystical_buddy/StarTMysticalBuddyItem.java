package com.startechnology.start_core.api.mystical_buddy;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class StarTMysticalBuddyItem extends Item {
    private final StarTMysticalBuddyDefinition definition;

    public StarTMysticalBuddyItem(Properties properties, StarTMysticalBuddyDefinition definition) {
        super(properties);
        this.definition = definition;
    }

    public StarTMysticalBuddyDefinition getDefinition() {
        return definition;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        StarTMysticalBuddyData.initialize(stack);
        StarTMysticalBuddyRarity rarity = StarTMysticalBuddyData.getRarity(stack);

        tooltip.add(Component.translatable("item.start_core.mystical_buddy.rarity",
                rarity.displayComponent()).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.start_core.mystical_buddy.level",
                StarTMysticalBuddyData.getLevel(stack), rarity.maxLevel()).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.start_core.mystical_buddy.hunger",
                StarTMysticalBuddyData.getHunger(stack)).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.start_core.mystical_buddy.happiness",
                StarTMysticalBuddyData.getHappiness(stack)).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.start_core.mystical_buddy.attribute_slots",
                rarity.attributeSlots()).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.start_core.mystical_buddy.terrarium_hint")
                .withStyle(ChatFormatting.DARK_GRAY));
    }
}
