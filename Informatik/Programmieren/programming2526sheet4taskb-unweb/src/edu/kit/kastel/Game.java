package edu.kit.kastel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * The class executes the game.
 * @author unweb
 */
public final class Game {
    /**
     * Number of player.
     */
    public static final int NUMBER_OF_PLAYERS = 4;
    /**
     * Number of cards eath player has in the beginning.
     */
    public static final int NUMBER_OF_CARDS_PER_PLAYER = 5; 
    /**
     * List of cards' name.
     */
    public static final String[] CARD_NAMES = new String[] {
        "7E", "8E", "9E", "10E", "BE", "DE", "KE", "AE",
        "7L", "8L", "9L", "10L", "BL", "DL", "KL", "AL",
        "7H", "8H", "9H", "10H", "BH", "DH", "KH", "AH",
        "7S", "8S", "9S", "10S", "BS", "DS", "KS", "AS"
    };
    /**
     * Game instance to run the programm.
     */
    public static final Game GAME_INSTANCE = new Game();
    /**
     * List of players.
     */
    private final Player[] players = new Player[NUMBER_OF_PLAYERS];
    /**
     * The card at the top of the discard pile.
     */
    private Card discardPile;
    /**
     * The draw pile.
     */
    private final List<Card> drawPile = new ArrayList<>();
    /**
     * Quit status.
     */
    private boolean quit = false;
    /**
     * Player's turn to play.
     */
    private int playerTurn = 1;
    /**
     * The game starts.
     */
    private boolean isStarted = false;
    /**
     * Constructor of the class.
     */
    private Game() {
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i] = new Player();
        }
    }
    /**
     * Controll the flow of the programm.
     */
    public void operate() {
        Scanner input = new Scanner(System.in);
        while (!quit) {
            try {
                String[] commands = input.nextLine().split(" ");
                if (commands.length == 0 || commands[0].isEmpty()) {
                    throw new Validity(Error.INVALID_INPUT_FORMAT.getMessage());
                }
                Command command = Command.stringToCommand(commands[0]);
                if (command == null) {
                    throw new Validity(Error.INVALID_INPUT_FORMAT.getMessage());
                }
                switch (command) {
                    case START -> {
                        validInput(commands, 2);
                        start(commands[1]);
                    } 
                    case SHOW -> {
                        compactShow(commands);
                    }
                    case DISCARD -> {
                        validInput(commands, 3);
                        discard(commands[1], commands[2]);
                    } 
                    case PICK -> {
                        validInput(commands, 2);
                        pick(commands[1]);
                    }
                    case QUIT -> {
                        validInput(commands, 1);
                        quit();
                    }
                    default -> {
                        throw new Validity(Error.INVALID_INPUT_FORMAT.getMessage());
                    }
                }
                hasWon();
            } catch (Validity e) {
                System.out.println(e.getMessage());
            }
        }
        input.close();
    }
    /**
     * Two show commands integrated into one.
     * @param commands commands
     * @throws Validity when input has the incorrect format
     */
    private void compactShow(String[] commands) throws Validity {
        if (commands.length != 2) {
            throw new Validity(Error.INVALID_INPUT_FORMAT.getMessage());
        }
        if (commands[1].equals("game")) {
            showGame();
        } else if (commands[1].matches("[1234]")) {
            showPlayer(commands[1]);
        } else {
            throw new Validity(Error.INVALID_INPUT_FORMAT.getMessage());
        }
    }
    /**
     * Start the programm and shuffle
     * @param seed for random shuffle
     * @throws Validity when the seed has the incorrect format
     */
    private void start(String seed) throws Validity {
        if (!seed.matches("[0-9]*")) {
            throw new Validity(Error.INVALID_SEED.getMessage());
        }
        drawPile.clear();
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i] = new Player();
        }
        for (String card : CARD_NAMES) {
            drawPile.add(Card.stringToCard(card));
        }
        int randomSeed = Integer.parseInt(seed);
        Collections.shuffle(drawPile, new Random(randomSeed));
        for (Player player : players) {
            for (int i = 0; i < NUMBER_OF_CARDS_PER_PLAYER; i++) {
                player.getCards().add(drawPile.removeFirst());
            }
        }
        isStarted = true;
        discardPile = drawPile.removeFirst();
        System.out.println("Player 1 takes the turn.");
    }
    /**
     * Show the current game status.
     */
    private void showGame() throws Validity {
        isGameStarted();
        System.out.println(discardPile.getCardName() + " / " + drawPile.size());
    }
    /**
     * Show the cards of the player
     * @param playerNumber player number
     * @throws Validity when the number is invalid
     */
    private void showPlayer(String playerNumber) throws Validity {
        isGameStarted();
        int number = Integer.parseInt(playerNumber);
        isValidPlayer(number);
        players[number - 1].showCards();
    }
    /**
     * Discard a card.
     * @param playerNumber player number
     * @param cardName card name
     * @throws Validity when the card name is invalid of the player number is invalid of the card cannot be stacked on discard pile
     */
    private void discard(String playerNumber, String cardName) throws Validity {
        isGameStarted();
        if (!playerNumber.matches("[0-9]*")) {
            throw new Validity(Error.INVALID_PLAYER_NUMBER.getMessage());
        }
        int number = Integer.parseInt(playerNumber);
        isValidPlayer(number);
        if (number != playerTurn) {
            throw new Validity(Error.NO_TURN_TO_PLAY.getMessage());
        }
        Player currentPlayer = players[number - 1];
        isValidCard(cardName);
        Card discardedCard = Card.stringToCard(cardName);
        if (!currentPlayer.cardOnHand(discardedCard.getCardName())) {
            throw new Validity(Error.CARD_NOT_ON_HAND.getMessage());
        }
        if (discardedCard.getCardColor().equals(discardPile.getCardColor())) {
            currentPlayer.discard(discardedCard.getCardName());
            discardPile = discardedCard;
            playerTurn = playerTurn % 4 + 1; 
            return;
        } else if (discardedCard.getCardValue().equals(discardPile.getCardValue())) {
            currentPlayer.discard(discardedCard.getCardName());
            discardPile = discardedCard;
            playerTurn = playerTurn % 4 + 1; 
            return;
        }
        System.out.println("Error, " + cardName + " cannot be stacked on " + discardPile.getCardName() + ".");
    }
    /**
     * Pick up a card.
     * @param playerNumber player number
     * @throws Validity when the player number is invalid
     */
    private void pick(String playerNumber) throws Validity {
        isGameStarted();
        if (!playerNumber.matches("[0-9]*")) {
            throw new Validity(Error.INVALID_PLAYER_NUMBER.getMessage());
        }
        int number = Integer.parseInt(playerNumber);
        isValidPlayer(number);
        if (number != playerTurn) {
            throw new Validity(Error.NO_TURN_TO_PLAY.getMessage());
        }
        Player currentPlayer = players[number - 1];
        for (Card card : currentPlayer.getCards()) {
            String value = card.getCardValue();
            String color = card.getCardColor();
            if (value.equals(discardPile.getCardValue()) || color.equals(discardPile.getCardColor())) {
                throw new Validity(Error.HAVE_CARD_TO_PLAY.getMessage());
            }
        }
        if (!drawPile.isEmpty()) {
            currentPlayer.takeCards(drawPile.removeFirst());
            playerTurn = playerTurn % 4 + 1; 
        }
        if (drawPile.isEmpty()) {
            isStarted = false;
            System.out.println("Game over: Draw.");
        }
    }
    /**
     * Quit.
     */
    private void quit() throws Validity {
        if (quit) {
            throw new Validity(Error.INVALID_QUIT.getMessage());
        }
        isStarted = false;
        quit = true;
    }
    /**
     * Checks whether a card is valid or not.
     * @param cardName name of the card
     * @throws Validity when the name of the card is invalid
     */
    private void isValidCard(String cardName) throws Validity {
        Card card = Card.stringToCard(cardName);
        if (card == null) {
            throw new Validity(Error.INVALID_CARD_NUMBER.getMessage());
        }
        String value = card.getCardValue();
        String color = card.getCardColor();
        if (!(value.matches("[7-9BDKA]") || value.matches("10"))) {
            throw new Validity(Error.INVALID_CARD_NUMBER.getMessage());
        } else if (!color.matches("[ELHS]")) {
            throw new Validity(Error.INVALID_CARD_NUMBER.getMessage());
        }
    }
    /**
     * Checks if the player is valid.
     * @param number player number
     * @throws Validity when the player number is invalid
     */
    private void isValidPlayer(int number) throws Validity {
        if (number < 1 || number > 4) {
            throw new Validity(Error.INVALID_PLAYER_NUMBER.getMessage());
        }
    }
    /**
     * Checks if the input format is valid.
     * @param commands command
     * @param numberOfParameter the number of parameters for the command
     * @throws Validity when the command has the incorrect format
     */
    private void validInput(String[] commands, int numberOfParameter) throws Validity {
        if (commands.length != numberOfParameter) {
            throw new Validity(Error.INVALID_INPUT_FORMAT.getMessage());
        }
    }
    /**
     * Checks if someone has won the game.
     */
    void hasWon() {
        if (!isStarted || quit) {
            return;
        }
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (players[i].getCards().isEmpty()) {
                isStarted = false;
                System.out.println("Game over: Player " + String.valueOf(i + 1) + " has won.");
                return;
            }
        }
    }
    /**
     * Checks if the game has started yet.
     * @throws Validity when the game hasn't started yet.
     */
    void isGameStarted() throws Validity {
        if (!isStarted) {
            throw new Validity(Error.UNINITIALISED.getMessage());
        }
    }
}
