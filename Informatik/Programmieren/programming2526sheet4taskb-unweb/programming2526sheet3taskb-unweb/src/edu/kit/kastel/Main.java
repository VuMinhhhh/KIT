package edu.kit.kastel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * main. where the operations take place.
 * @author unweb
 */
public final class Main {
    private Main() {

    }
    /**
     * Execute opening files, operate the dictionary.
     * @author unweb
     * @param args is the arguments
     */
    public static void main(String[] args) {
        List<String> lines;
        HashMap<String, ArrayList<String>> tmp = new HashMap<>();
        if (args.length != 1) {
            System.out.println("ERROR: the argument is wrong!");
        } else {
            Path path = Paths.get(args[0]);
            if (Files.notExists(path)) {
                System.out.println("ERROR: The file path is wrong!");
            } else {
                try {
                    if (Files.size(path) == 0) {
                        System.out.println("ERROR: The file is empty!");
                    }
                } catch (Exception e) {
                    System.out.println("ERROR: Could not read file size!");
                }
                try {
                    lines = Files.readAllLines(path);
                    for (String eachLine : lines) {
                        String[] words = eachLine.split(" - ");
                        if (words.length != 2) {
                            System.out.println("ERROR: Invalid words and translation!");
                            break;
                        }
                        if (tmp.containsKey(words[0])) {
                            if (tmp.get(words[0]).contains(words[1])) {
                                System.out.println("ERROR: Invalid words and translation!");
                                break;
                            } else {
                                tmp.get(words[0]).add(words[1]);
                            }
                        } else {
                            ArrayList<String> translations = new ArrayList<>();
                            translations.add(words[1]);
                            tmp.put(words[0], translations);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("ERROR: Could not read file!");
                    return;
                }
            }
        }
        Command dictionary = new Command(tmp);
        try (Scanner input = new Scanner(System.in)) {
            boolean quit = false;
            while (!quit) {
                String command = input.nextLine();
                if (command.equals("quit")) {
                    quit = true;
                } else {
                    dictionary.operate(command);
                }
            }
            input.close();
        } catch (Exception e) {
            System.out.println("ERROR: Could not read input!");
        }
    }
}
