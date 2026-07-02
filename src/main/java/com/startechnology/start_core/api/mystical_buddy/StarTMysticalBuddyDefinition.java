package com.startechnology.start_core.api.mystical_buddy;

import com.startechnology.start_core.StarTCore;

import net.minecraft.resources.ResourceLocation;

public record StarTMysticalBuddyDefinition(
        ResourceLocation id) {

    public static StarTMysticalBuddyDefinition of(String name) {
        return new StarTMysticalBuddyDefinition(StarTCore.resourceLocation(name));
    }

    public String registryName() {
        return id.getPath();
    }
}
