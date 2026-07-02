package com.startechnology.start_core.api.mystical_buddy;

import static com.startechnology.start_core.StarTCore.START_REGISTRATE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tterrag.registrate.util.entry.ItemEntry;

public final class StarTMysticalBuddyHelper {
    private static final List<ItemEntry<StarTMysticalBuddyItem>> BUDDIES = new ArrayList<>();

    private StarTMysticalBuddyHelper() {
    }

    public static ItemEntry<StarTMysticalBuddyItem> register(String name) {
        return register(StarTMysticalBuddyDefinition.of(name));
    }

    public static ItemEntry<StarTMysticalBuddyItem> register(StarTMysticalBuddyDefinition definition) {
        ItemEntry<StarTMysticalBuddyItem> entry = START_REGISTRATE
                .item(definition.registryName(), properties -> new StarTMysticalBuddyItem(properties, definition))
                .properties(properties -> properties.stacksTo(1))
                .register();
        BUDDIES.add(entry);
        return entry;
    }

    public static List<ItemEntry<StarTMysticalBuddyItem>> getBuddies() {
        return Collections.unmodifiableList(BUDDIES);
    }
}
