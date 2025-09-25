package com.donor.enums;

public enum SleepType {
//	(Normal/Irregular, avg hours per day)

	    NORMAL("Normal sleep pattern", 7, 9),
	    IRREGULAR("Irregular sleep pattern", 0, 0),
	    SHORT_SLEEP("Short sleep (<6 hrs)", 0, 6),
	    LONG_SLEEP("Long sleep (>9 hrs)", 9, 12);

	    private final String description;
	    private final int minHours;
	    private final int maxHours;

	    SleepType(String description, int minHours, int maxHours) {
	        this.description = description;
	        this.minHours = minHours;
	        this.maxHours = maxHours;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public int getMinHours() {
	        return minHours;
	    }

	    public int getMaxHours() {
	        return maxHours;
	    }

	    public static SleepType fromAverageHours(int hours) {
	        if (hours < 6) return SHORT_SLEEP;
	        if (hours >= 6 && hours <= 9) return NORMAL;
	        if (hours > 9) return LONG_SLEEP;
	        return IRREGULAR;
	    }

}
