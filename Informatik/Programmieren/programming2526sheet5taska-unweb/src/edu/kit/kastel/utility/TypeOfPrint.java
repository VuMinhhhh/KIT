package edu.kit.kastel.utility;

/**
 * Enumeration defining the specific conditions and formats used for printing tasks.
 * @author unweb
 * @version 1.0
 */
public enum TypeOfPrint {
    /** Standard printing mode with no specific filters applied. */
    REGULAR,
    /** Filter mode that displays only tasks marked as incomplete. */
    INCOMPLETED_ONLY,
    /** Filter mode that displays items associated with a specific tag. */
    TAGGED_WITH,
    /** Search mode that filters items containing a specific name substring. */
    FIND,
    /** Filter mode showing tasks due strictly before a specified date. */
    BEFORE,
    /** Filter mode showing tasks due within the upcoming week relative to a date. */
    UPCOMING,
    /** Filter mode showing tasks due within a specific start and end date range. */
    BETWEEN;
}