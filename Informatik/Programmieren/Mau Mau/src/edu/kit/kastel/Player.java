package edu.kit.kastel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * This class modelises the player.
 * @author unweb
 */
public class Player {
    /**
     * List of cards on hand.
     */
    private final List<Card> hand = new ArrayList<>();
    /**
     * Constructor of the class.
     */
    public Player() {

    }
    /**
     * Getter of the list of cards on hand.
     * @return list of cards
     */
    public List<Card> getCards() {
        return hand;
    }
    /**
     * Checks if the card is on the hand of the player.
     * @param card card name
     * @return true if the card is on the hand of the player, otherwise false
     */
    public boolean cardOnHand(String card) {
        for (Card cardOnHand : hand) {
            if (cardOnHand.getCardName().equals(card)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Discard the given card.
     * @param card card name
     */
    public void discard(String card) {
        for (Card cardInHand : hand) {
            if (cardInHand.getCardName().equals(card)) {
                hand.remove(cardInHand);
                return;
            }
        }
    }
    /**
     * Add the card to hand.
     * @param card card
     */
    public void takeCards(Card card) {
        hand.add(card);
    }
    /**
     * Print the names of all the card on the player's hand.
     */
    public void showCards() {
        List<String> result = new ArrayList<>();
        for (Card card : hand) {
            result.add(card.getCardName());
        }
        result.sort(Comparator.naturalOrder());
        System.out.println(String.join(",", result));
    }
}
