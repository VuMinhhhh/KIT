package edu.kit.kastel;

/**
 * Das Spielfeld und seine Attribut und Methoden.
 * @author unweb
 */
public final class Playground { 
    /**
     * Das Spielfeld.
     * @author unweb
     */
    public char[][] playground; 
    
    /**
     * Wir konstruieren ein Spielfeld.
     * @author unweb
     */
    public Playground() {  
        char[][] tmp = new char[6][15];
        for (char[] zeilen : tmp) {
            for (int j = 0; j < zeilen.length; j++) {
                if (j % 2 == 0) {
                    zeilen[j] = '|';
                } else {
                    zeilen[j] = ' ';
                }
            }
        }
        playground = tmp;
    }

    /**
     * Wir überprüfen, ob ein Platz frei ist.
     * @author unweb
     * @param iPos ist Zeile
     * @param jPos ist Spalte
     * @return true wenn die Zell frei ist, false wenn umgekehrt
     */
    public boolean isTheCellAvailable(int iPos, int jPos) { 
        return (playground[iPos][jPos] == ' ');
    }

    /**
     * Wir überprüfen, ob p gewinnt.
     * @author unweb
     * @param p ist Spieler
     * @param s ist die Steinanzahl#
     * @return true wenn p gewinnt, false wenn umgekehrt
     */ 
    public boolean win(char p, int s) { 
        int xmax = playground.length;
        int ymax = playground[0].length;
        for (int i = 0; i < xmax; i++) {
            for (int j = 0; j < ymax; j++) {
                if (playground[i][j] == p) {
                    if (j <= ymax - 2 * s) {
                        boolean win = true;
                        for (int z = 1; z < s; z++) {
                            if (playground[i][j + 2 * z] != p) {
                                win = false;
                                break;
                            }
                        }
                        if (win) {
                            return true;
                        }
                    } 
                    if (i <= xmax - s) {
                        boolean win = true;
                        for (int z = 1; z < s; z++) {
                            if (playground[i + z][j] != p) {
                                win = false;
                                break;
                            }
                        }
                        if (win) {
                            return true;
                        }
                    } 
                    if (j <= ymax - 2 * s && i <= xmax - s) {
                        boolean win = true;
                        for (int z = 1; z < s; z++) {
                            if (playground[i + z][j + 2 * z] != p) {
                                win = false;
                                break;
                            }
                        }
                        if (win) {
                            return true;
                        }
                    } 
                    if (j >= 2 * s - 1 && i <= xmax - s) {
                        boolean win = true;
                        for (int z = 1; z < s; z++) {
                            if (playground[i + z][j - 2 * z] != p) {
                                win = false;
                                break;
                            }
                        }
                        if (win) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Wir überprüfen, ob das ganze Spielfeld voll ist.
     * @author unweb
     * @return true wenn das ganze Feld voll ist, falls wenn es mindestens eine Zell gibt, die frei ist
     */
    public boolean isTheFieldFull() { 
        for (int i = 0; i < playground.length; i++) {
            for (int j = 0; j < playground[0].length; j++) {
                if (isTheCellAvailable(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Wir werfen den Stein in die spalte ein.
     * @author unweb
     * @param playerNumber ist die Nummer des Spielers
     * @param column ist die Spalte
     */ 
    public void stoneInsertion(char playerNumber, int column) { 
        for (int i = playground.length - 1; i >= 0; i--) {
            if (isTheCellAvailable(i, 2 * column - 1)) {
                playground[i][2 * column - 1] = playerNumber;
                return;
            }
        }
    }

}
