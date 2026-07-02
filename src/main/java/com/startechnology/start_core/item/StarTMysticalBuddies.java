package com.startechnology.start_core.item;

import java.util.List;

import com.startechnology.start_core.api.mystical_buddy.StarTMysticalBuddyHelper;
import com.startechnology.start_core.api.mystical_buddy.StarTMysticalBuddyItem;
import com.tterrag.registrate.util.entry.ItemEntry;

public class StarTMysticalBuddies {
    public static final ItemEntry<StarTMysticalBuddyItem> LIGHT = StarTMysticalBuddyHelper.register(
            "light_buddy");

    public static final List<ItemEntry<StarTMysticalBuddyItem>> BUDDIES = StarTMysticalBuddyHelper.getBuddies();

    public static void init() {
    }
}
