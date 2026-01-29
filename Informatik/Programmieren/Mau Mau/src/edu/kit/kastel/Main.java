package edu.kit.kastel;

/**
 * Main class.
 * @author unweb
 */
public final class Main {
    /**
     * Constructor of the class.
     */
    private Main() {

    }
    /**
     * Where the programm takes place.
     * @param args arguments
     */
    public static void main(String[] args) {
        Game.GAME_INSTANCE.operate();
    }
}
