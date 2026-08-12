package com.aegisresort.model;

public enum PackageTier {
    DAY_TOUR(1, "Day Tour"),
    OVERNIGHT(2, "Overnight Guest"),
    VIP(3, "VIP Access");

    private final int level;
    private final String displayName;

    PackageTier(int level, String displayName) {
        this.level = level;
        this.displayName = displayName;
    }

    public int getLevel() {
        return level;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean hasAccess(PackageTier requiredTier) {
        return this.level >= requiredTier.getLevel();
    }
}