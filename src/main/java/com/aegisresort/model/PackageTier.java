package com.aegisresort.model;

public enum PackageTier {
    DAY_TOUR("Day Tour", 1),
    OVERNIGHT("Overnight", 2),
    VIP("VIP Package", 3);

    private final String displayName;
    private final int tierLevel;

    PackageTier(String displayName, int tierLevel) {
        this.displayName = displayName;
        this.tierLevel = tierLevel;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getTierLevel() {
        return tierLevel;
    }
}
