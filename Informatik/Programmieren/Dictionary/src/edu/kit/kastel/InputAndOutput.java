package edu.kit.kastel;

import java.util.Scanner;

/**
 * Here definiert man Ein- und Ausgabe.
 * @author unweb
 */
public final class InputAndOutput { 
    private static final Scanner INPUT = new Scanner(System.in); 

    private InputAndOutput() {

    }
    /**
    * Hier geben wir Werte über.
    * @author unweb
    * @param playground ist das Spielfeld
    * @param player ist die Menge aller Spieler
    * @param zug is Zug
    * @param playerNumber ist die Zahl des Spielers
    * @return false wenn "quit" oder die Eingabe nicht gültig ist, sonst return true
    */
    public static boolean input(Playground playground, char[] player, int zug, int playerNumber) { 
        System.out.println(zug + ". Zug, Spieler " + playerNumber + ":");
        String tmp = INPUT.nextLine();
        if (tmp.equals("quit")) {
            return false;
        }
        if (tmp.length() != 1) {
            System.out.println("ERROR: Die Eingabe ist nicht gültig!");
            return input(playground, player, zug, playerNumber);
        }
        int num = (int) tmp.charAt(0) - 48;
        if (num < 1 || num > 7) {
            System.out.println("ERROR: Die eingegebene Nummer ist nicht gültig!");
            return input(playground, player, zug, playerNumber);
        } else {
            for (int i = playground.playground.length - 1; i >= 0; i--) {
                if (playground.isTheCellAvailable(i, 2 * num - 1)) {
                    playground.playground[i][2 * num - 1] = (char) (playerNumber - 1 + '0');
                    return true;
                }
            }
            System.out.println("ERROR: Die Spalte ist schon voll!");
            return input(playground, player, zug, playerNumber);
        }
    }
    
    /**
     * Wir geben das Feld aus.
     * @author unweb
     * @param playground ist das Spielfeld
     * @param player ist die Menge aller Spieler
     */
    public static void output(Playground playground, char[] player) { 
        for (char[] tmp : playground.playground) {
            for (char a : tmp) {
                if (Character.isDigit(a)) {
                    System.out.print(player[a - '0']);
                } else {
                    System.out.print(a);
                }
            }
            System.out.print("\n");
        }
    }
    
    /**
     * Wir schließen Scanner.
     * @author unweb
     */
    public static void closeScanner() { 
        INPUT.close();
    }
}
