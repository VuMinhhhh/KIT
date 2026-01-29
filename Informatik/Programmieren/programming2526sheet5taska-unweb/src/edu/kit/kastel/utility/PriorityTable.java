package edu.kit.kastel.utility;

import java.util.Map;

/**
 * Utility class providing a mapping from string-based priorities to integer values.
 * @author unweb
 * @version 1.0
 */
public final class PriorityTable {
    /**
     * Immutable map containing the supported priorities and their numeric values.
     */
    public static final Map<String, Integer> PRIORITY_MAP = Map.of(
        "HI", 1,
        "MD", 2,
        "LO", 3
    );

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private PriorityTable() {
    }

    /**
     * Retrieves the integer value associated with a priority string.
     * @param priority The priority string to look up.
     * @return The priority value, or 4 if the priority is null or not found.
     */
    public static int getPriorityValue(String priority) {
        return PRIORITY_MAP.getOrDefault(priority, 4);
    }
}