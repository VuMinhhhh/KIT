package edu.kit.kastel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * This class covers all functions needed for the programm to execute.
 * @author unweb
 */

public final class Command {
    /**
     * The dictionary as a list of pairs of words and their translations.
     * @author unweb
     */
    HashMap<String, ArrayList<String>> dictionary;
    
    /**
     * Constructor of the class.
     * @author unweb
     * @param tmp is the given dictionary
     */
    public Command(HashMap<String, ArrayList<String>> tmp) {
        this.dictionary = tmp;
        for (String key : dictionary.keySet()) {
            sorted(dictionary.get(key));
        }
    }

    /**
     * Sort the the list of words in alphabetical order.
     * @author unweb
     * @param words is the array of given words
     */
    private void sorted(ArrayList<String> words) {
        words.sort(String.CASE_INSENSITIVE_ORDER.thenComparing(Comparator.reverseOrder()));
    }

    /**
     * add a word and its translation to the dictionary.
     * @author unweb
     * @param word is the given word
     * @param translatedWord is the translation of the word
     */
    private void add(String word, String translatedWord) {
        if (dictionary.containsKey(word)) {
            if (dictionary.get(word).contains(translatedWord)) {
                System.out.println("ERROR: The translation exists already!");
                return;
            }
            dictionary.get(word).add(translatedWord);
            sorted(dictionary.get(word));
        } else {
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(translatedWord);
            dictionary.put(word, tmp);
        }
    }

    /**
     * remove a word out of the dictionary.
     * @author unweb
     * @param word is the given word
     */
    private void remove(String word) {
        if (dictionary.containsKey(word)) {
            dictionary.remove(word);
        }
    }

    /**
     * print the entire dictionary.
     * @author unweb
     */
    private void print() {
        if (dictionary.isEmpty()) {
            System.out.println("ERROR: The dictionary is empty");
        } else {
            ArrayList<String> words = new ArrayList<>(dictionary.keySet());
            sorted(words);
            for (String key : words) {
                System.out.print(key + " - ");
                for (String translatedWord : dictionary.get(key)) {
                    System.out.print(translatedWord);
                    if (dictionary.get(key).indexOf(translatedWord) != dictionary.get(key).size() - 1) {
                        System.out.print(",");
                    }
                }
                System.out.println();
            }
        }
    }

    /**
     * print all words and their translation, in case they begin with the given letter.
     * @author unweb
     * @param letter is the given letter
     */
    private void print(char letter) {
        boolean notExist = true;
        ArrayList<String> words = new ArrayList<>(dictionary.keySet());
        sorted(words);
        for (String key : words) {
            if (Character.toLowerCase(key.charAt(0)) == Character.toLowerCase(letter)) {
                System.out.print(key + " - ");
                notExist = false;
                for (String translatedWord : dictionary.get(key)) {
                    System.out.print(translatedWord);
                    if (dictionary.get(key).indexOf(translatedWord) != dictionary.get(key).size() - 1) {
                        System.out.print(",");
                    }
                }
                System.out.println();
            }
        }
        if (notExist) {
            System.out.println("ERROR: There aren't any words beginning with " + letter + "!");
        }
    }

    /**
     * print the translation of a word.
     * @author unweb
     * @param word is the given word
     */
    private void translate(String word) {
        if (dictionary.containsKey(word)) {
            for (String translatedWord : dictionary.get(word)) {
                System.out.print(translatedWord);
                if (dictionary.get(word).indexOf(translatedWord) != dictionary.get(word).size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        } else {
            System.out.println("ERROR: The word is not available in the dictionary!");
        }
    } 

    /**
     * translate the entire sentence.
     * @author umweb
     * @param sentence is sequence of words that has yet to be translated
     * @param translation is the sequence of translated words
     */
    private void translate(String sentence, String translation) {
        String[] words = sentence.split(" ");
        if (words.length < 1) {
            System.out.println("ERROR: The word or sentence is empty!");
        } else {
            String word = words[0];
            if (words.length == 1) {
                if (dictionary.containsKey(word)) {
                    for (String translatedWord : dictionary.get(word)) {
                        if (translation.equals("")) {
                            System.out.println(translatedWord);
                        } else {
                            System.out.println(translation + " " + translatedWord);
                        }
                    }
                } else {
                    System.out.println("ERROR: The word is not available in the dictionary!");
                }
            } else {
                String rest = sentence.substring(word.length() + 1);
                if (dictionary.containsKey(word)) {
                    for (String translatedWord : dictionary.get(word)) {
                        if (translation.equals("")) {
                            translate(rest, translatedWord);
                        } else {
                            translate(rest, translation + " " + translatedWord);
                        }
                    }
                } else {
                    System.out.println("ERROR: The word is not available in the dictionary!");
                }
            }
                
        }
    }

    /**
     * check whether the command is valid or not.
     * @author unweb
     * @param command is the given command
     * @return true when the command is invalid
     */
    private boolean commandInvalid(String command) {
        if (command == null) {
            return true;
        }
        String[] operations = command.split(" ");
        return switch (operations[0]) {
            case "add" -> (operations.length != 3);   
            case "remove" -> (operations.length != 2);
            case "print" -> (operations.length != 1) && (operations.length != 2);
            case "translate" -> (operations.length < 2);
            default -> true;
        };
    }

    /**
     * operation the users' command.
     * @author unweb
     * @param command is the given command
     */
    public void operate(String command) {
        String[] operations = command.split(" ");
        if (commandInvalid(command)) {
            System.out.println("ERROR: The command is invalid! Try again!");
            return;
        }
        switch (operations[0]) {
            case "add" -> add(operations[1], operations[2]);
            case "remove" -> remove(operations[1]);
            case "print" -> {
                if (operations.length == 1) {
                    print();
                } else {
                    print(operations[1].charAt(0));
                }
            }
            case "translate" -> {
                if (operations.length == 2) {
                    translate(operations[1]);
                } else {
                    String tmp = command.substring(operations[0].length() + 1);
                    translate(tmp, "");
                }
            }
            default -> {
                return;
            }
        }
    }
}