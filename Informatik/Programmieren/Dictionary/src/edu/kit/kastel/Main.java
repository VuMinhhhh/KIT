package edu.kit.kastel;

/**
 * Main, wo die Spiel stattfindet.
 * @author unweb
 */
public final class Main {
    private Main() {

    }
    /**
     * Game loop.
     * @author unweb
     * @param args sind argumete 
     */
    public static void main(String[] args) {
        boolean gameOver = false;
        if (args.length > 6) {
            System.out.println("ERROR: Too many players!");
            return;
        } 
        for (String tmp : args) {
            if (tmp.length() > 1) {
                System.out.println("ERROR: The symbol can take up 1 character only!");
                return;
            }
        }
        for (int i = 0; i < args.length; i++) {
            for (int j = i + 1; j < args.length; j++) {
                if (args[i].equals(args[j])) {
                    System.out.println("ERROR: The symbol " + args[i] + " already exists!");
                    return;
                }
            }
        }
        char[] players;
        if (args.length == 0) {
            players = new char[] {'x', 'o'};
        } else {
            players = new char[args.length];
            for (int i = 0; i < args.length; i++) {
                players[i] = args[i].charAt(0);
            }
        }
        int numberOfRocks = Math.min(4, (int) Math.ceil(9.0 / players.length));
        Playground playground = new Playground();
        InputAndOutput.output(playground, players);
        int zug = 1;
        while (!gameOver) {
            for (int i = 0; i < players.length; i++) {
                if (playground.isTheFieldFull()) {
                    gameOver = true;
                    System.out.println("Unentschieden!");
                    break;
                } else {
                    gameOver = !InputAndOutput.input(playground, players, zug, i + 1);
                    zug += 1;
                    if (playground.win((char) (i + '0'), numberOfRocks)) {
                        gameOver = true;
                        InputAndOutput.output(playground, players);
                        System.out.println("Sieger: Spieler " + (i + 1));
                        break;
                    }
                    if (!gameOver) {
                        InputAndOutput.output(playground, players);
                    } else {
                        break;
                    }
                } 
            }
        }
        InputAndOutput.closeScanner();
    }
}