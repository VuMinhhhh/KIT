package edu.kit.kastel;
/**
 * This class modelises card.
 * @author unweb
 */
public class Card {
    /**
     * The value of the card.
     */
    private final String cardValue;
    /**
     * The color of the card.
     */
    private final String cardColor;
    /**
     * Constructor of the class.
     * @param cardValue value of the card
     * @param cardColor color of the card
     */
    public Card(String cardValue, String cardColor) {
        this.cardValue = cardValue;
        this.cardColor = cardColor;
    }   
    /**
     * Getter of the value.
     * @return value of the card
     */
    public String getCardValue() {
        return cardValue;
    }
    /**
     * Getter of the color.
     * @return color of the class
     */
    public String getCardColor() {
        return cardColor;
    }
    /**
     * Getter of the name.
     * @return name of the card
     */
    public String getCardName() {
        return cardValue + cardColor;
    }
    /**
     * Convert a card name to a card.
     * @param card card name
     * @return a card
     */
    public static Card stringToCard(String card) {
        if (card.length() == 2) {
            String value = card.substring(0, 1);
            String color = card.substring(1, 2);
            Card newCard = new Card(value, color);
            return newCard;
        } else if (card.length() == 3) {
            String value = card.substring(0, 2);
            String color = card.substring(2, 3);
            Card newCard = new Card(value, color);
            return newCard;
        } 
        return null;
    }

    
}
