package rockpaperscissors;

import rockpaperscissors.enums.GameCommand;
import rockpaperscissors.rockpaperscissors.Player;
import rockpaperscissors.rockpaperscissors.RockPaperScissors;
import rockpaperscissors.rockpaperscissors.enums.HandGesture;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    RockPaperScissors rockPaperScissors = new RockPaperScissors();

    Player player;

    HandGesture userGesture;
    HandGesture computerGesture;

    HandGesture[] handGestures;

    Game() {

    }

    public void startGame() {

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        this.initializePlayer(name);
        System.out.printf("Hello, %s\n", this.player.name);

        String inputHandGesturesString = scanner.nextLine();
        System.out.println("Okay, let's start");

        if (Objects.equals(inputHandGesturesString, "a") || inputHandGesturesString.isEmpty()) {
            this.initializeClassicGestures();
            this.classicGameLoop();
        } else {
            if (inputHandGesturesString.equals("rock,paper,scissors,lizard,spock")) {
                inputHandGesturesString = "rock,paper,scissors,lizard,spock";
            }
            this.initializeHandGestures(inputHandGesturesString);
            this.gameLoop();
        }
    }

    private void initializeHandGestures(String gesturesString) {
        String[] gesturesArrayString = gesturesString.split(",");
        for (String value: gesturesArrayString) {
            HandGesture.addGesture(value);
        }

    }

    private void initializeClassicGestures() {
        HandGesture.gestures.add(new HandGesture("rock"));
        HandGesture.gestures.add(new HandGesture("paper"));
        HandGesture.gestures.add(new HandGesture("scissors"));
    }

    void classicGameLoop() {
        try {
            while (true) {
                String input = scanner.nextLine();

                this.validateIfInputIsACommandClassic(input);
                if (Objects.equals(input, GameCommand.RATING.value)) {
                    this.printPlayerScore();
                    continue;
                }
                if (Objects.equals(input, GameCommand.EXIT.value)) {
                    Helper.savePlayer(this.player);
                    System.out.println("Bye!");
                    return;
                }
                this.userGesture = HandGesture.convert(input);
                this.computerGesture = rockPaperScissors.getRandomClassicGesture();

                this.decideWinner();
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            this.classicGameLoop();
        }
    }

    void gameLoop() {
        try {
            while (true) {
                String input = scanner.nextLine();

                this.validateIfInputIsACommand(input);
                if (Objects.equals(input, GameCommand.RATING.value)) {
                    this.printPlayerScore();
                    continue;
                }
                if (Objects.equals(input, GameCommand.EXIT.value)) {
                    Helper.savePlayer(this.player);
                    System.out.println("Bye!");
                    return;
                }
                this.userGesture = HandGesture.convert(input);
                this.computerGesture = rockPaperScissors.getRandomGestureFromArray();

                this.decideWinner();
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            this.gameLoop();
        }
    }

    void decideWinner() {
        switch (rockPaperScissors.isUserWinner(this.userGesture, this.computerGesture)) {
            case 1:
                this.player.score += 100;
                System.out.printf("Well done. The computer chose %s and failed\n", this.computerGesture.value);
                break;
            case -1:
                System.out.printf("Sorry, but the computer chose %s\n", this.computerGesture.value);
                break;
            default:
                this.player.score += 50;
                System.out.printf("There is a draw (%s)\n", this.computerGesture.value);
        }
    }

    void validateIfInputIsACommandClassic(String input) {
        boolean isHandGesture = HandGesture.isHandClassicGesture(input);
        boolean isACommand = Objects.equals(input, GameCommand.EXIT.value) || Objects.equals(input, GameCommand.RATING.value);
        if (!isHandGesture && !isACommand) {
            throw new InputMismatchException("Invalid input");
        }
    }

    void validateIfInputIsACommand(String input) {
        boolean isHandGesture = HandGesture.isInHandGestureList(input);
        boolean isACommand = Objects.equals(input, GameCommand.EXIT.value) || Objects.equals(input, GameCommand.RATING.value);
        if (!isHandGesture && !isACommand) {
            throw new InputMismatchException("Invalid input");
        }
    }

    void initializePlayer(String name) {
        Player[] savedPlayers = Helper.getSavedPlayers();
        for (Player savedPlayer : savedPlayers) {
            if (savedPlayer != null) {
                if (Objects.equals(name, savedPlayer.name)) {
                    this.player = savedPlayer;
                }
            }
        }
        if (this.player == null) {
            this.player = new Player(name, 0);
        }
    }

    void printPlayerScore() {
        System.out.printf("Your rating: %d\n", this.player.score);
    }

}
