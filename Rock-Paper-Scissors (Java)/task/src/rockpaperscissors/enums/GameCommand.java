package rockpaperscissors.enums;

import java.util.InputMismatchException;
import java.util.Objects;

public enum GameCommand {
    RATING("!rating"),
    EXIT("!exit");

    public final String value;
    GameCommand(String value) {
        this.value = value;
    }

}
