package edu.kit.kastel.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import edu.kit.kastel.Task;

/**
 * Utility class providing static methods for date validation and comparison operations.
 * @author unweb
 * @version 1.0
 */
public final class Date {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final int UPCOMING_DAYS = 7;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private Date() {

    }
    /**
     * Validates if the provided string adheres to the strict yyyy-MM-dd format.
     * @param dateStr the date string to validate
     * @return true if the date is valid and strict; false otherwise
     */
    public static boolean validateDeadline(String dateStr) {
        if (dateStr == null) {
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);

        try {
            LocalDate.parse(dateStr.trim(), formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if a task's deadline is within the next 7 days relative to the current date.
     * @param currentDate the reference date string
     * @param task the task to check
     * @return true if deadline is strictly after current date and before the limit date
     */
    public static boolean upcoming(String currentDate, Task task) {
        LocalDate currDate = java.time.LocalDate.parse(currentDate, FMT);
        LocalDate taskDate = java.time.LocalDate.parse(task.getDeadline(), FMT);
        LocalDate limitDate = currDate.plusDays(UPCOMING_DAYS);
        return taskDate.isAfter(currDate) && taskDate.isBefore(limitDate);
    }

    /**
     * Checks if a task's deadline is strictly before the specified current date.
     * @param currentDate the reference date string
     * @param task the task to check
     * @return true if task deadline is before current date
     */
    public static boolean before(String currentDate, Task task) {
        LocalDate currDate = java.time.LocalDate.parse(currentDate, FMT);
        LocalDate taskDate = java.time.LocalDate.parse(task.getDeadline(), FMT);
        return taskDate.isBefore(currDate);
    }

    /**
     * Checks if a task's deadline falls strictly between a start date and an end date.
     * @param startDate the exclusive start date
     * @param endDate the exclusive end date
     * @param task the task to check
     * @return true if deadline is after startDate and before endDate
     */
    public static boolean between(String startDate, String endDate, Task task) {
        LocalDate start = java.time.LocalDate.parse(startDate, FMT);
        LocalDate end = java.time.LocalDate.parse(endDate, FMT);
        LocalDate taskDate = java.time.LocalDate.parse(task.getDeadline(), FMT);
        return taskDate.isAfter(start) && taskDate.isBefore(end);
    }
}