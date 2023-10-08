package rockpaperscissors;

import rockpaperscissors.rockpaperscissors.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

public class Helper {

    private static final Path path = Path.of("rating.txt");
    public static int findIndex(int[] arr, int t) {
        if (arr == null) {
            return -1;
        }
        int len = arr.length;
        int i = 0;
        while (i < len) {
            if (Objects.equals(arr[i], t)) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }

    public static int findIndex(String[] arr, String t) {
        if (arr == null) {
            return -1;
        }
        int len = arr.length;
        int i = 0;
        while (i < len) {
            if (Objects.equals(arr[i], t)) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }


    static Player[] getSavedPlayers() {

        File file = path.toFile();
        Player[] players = new Player[1000];

        try(Scanner scanner = new Scanner(file)) {
            int index = 0;
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                String name = line[0];
                int score = Integer.parseInt(line[1]);
                players[index] = new Player(name, score);
                index++;
            }
        } catch (FileNotFoundException e) {
            return new Player[0];
        }
        return players;
    }


    static void savePlayer(Player player) {
        final Player[] savedPlayers = getSavedPlayers();
        int index = 0;
        for (Player savedPlayer: savedPlayers) {
            if (savedPlayer != null && Objects.equals(player.name, savedPlayer.name)) {
                savedPlayer.score = player.score;
                break;
            } else {
                if (savedPlayer == null) {
                    savedPlayers[index] = player;
                    break;
                }
            }
            index++;
        }


        try(FileWriter fileWriter = new FileWriter(path.toFile())) {

            for (Player savedPlayer: savedPlayers) {
                if (savedPlayer != null) {
                    String line = savedPlayer.name + " " + savedPlayer.score + "\n";
                    fileWriter.write(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Players could be saved.");
        }
    }
}