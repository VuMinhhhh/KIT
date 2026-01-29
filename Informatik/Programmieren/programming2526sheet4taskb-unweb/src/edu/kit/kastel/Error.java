package edu.kit.kastel;

/**
 * This class contains all outputs when an error occurs.
 * @author unweb
 */
public enum Error { 
    /**
     * Invalid input format error.
     */
    INVALID_INPUT_FORMAT("Error, invalid input format."),
    /**
     * Invalid player number error.
     */
    INVALID_PLAYER_NUMBER("Error, invalid player number."),
    /**
     * Invalid card number error.
     */
    INVALID_CARD_NUMBER("Error, invalid card number."),
    /**
     * The card is not on the hand of the player error.
     */
    CARD_NOT_ON_HAND("Error, the player doesn't have this card."),
    /**
     * The game has already ended error.
     */
    INVALID_QUIT("Error, the game has already ended."),
    /**
     * Draw card error.
     */
    HAVE_CARD_TO_PLAY("Error, the player can still play without drawing a new card."),
    /**
     * Not the right turn to play error.
     */
    NO_TURN_TO_PLAY("Error, it not the player's turn to play."),
    /**
     * The game hasn't started yet error.
     */
    UNINITIALISED("Error, the game hasn't started yet."),
    /**
     * Invalid seed number error.
     */
    INVALID_SEED("Error, invalid seed number.");
    /**
     * Error message.
     */
    private final String message;
    /**
     * Constructor of the class.
     * @param message error message
     */
    Error(String message) {
        this.message = message;
    }
    /**
     * Getter for the error message.
     * @return error message
     */
    public String getMessage() {
        return message;
    } 
}
