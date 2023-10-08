package rockpaperscissors.rockpaperscissors;

import rockpaperscissors.rockpaperscissors.enums.HandGesture;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

public class RockPaperScissors {

    public int isUserWinner(HandGesture userGesture, HandGesture computerGesture) {
        int computerIndex = HandGesture.findIndex(computerGesture);
        int count = (HandGesture.gestures.size() - 1) / 2;

        ArrayList<HandGesture> winnableGestures= new ArrayList<HandGesture>();
        ArrayList<HandGesture> losableGestures= new ArrayList<HandGesture>();
        int c = 0;
        boolean isIteratingOnLosable = true;
        for (int i = (computerIndex + 1) % HandGesture.gestures.size(); i != computerIndex; i = i % HandGesture.gestures.size()) {
            if (c != count && isIteratingOnLosable) {
                winnableGestures.add(HandGesture.gestures.get(i));
                c++;
            }
            if (c != count && !isIteratingOnLosable) {
                losableGestures.add(HandGesture.gestures.get(i));
                c++;
            }
            if (c == count) {
                c = 0;
                isIteratingOnLosable = false;
            }
            i++;
        }

        if (HandGesture.findIndex(winnableGestures, userGesture) >= 0) {
            return 1;
        }     if (HandGesture.findIndex(losableGestures, userGesture) >= 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public HandGesture getRandomClassicGesture() {
        final Random random = new Random();
        int randomNumber = random.nextInt(3);
        return HandGesture.gestures.get(randomNumber);
    }

    public HandGesture getRandomGestureFromArray() {
        final Random random = new Random();
        int maxIndex = HandGesture.gestures.size();
        int randomIndex = random.nextInt(maxIndex);
        return HandGesture.gestures.get(randomIndex);
    }

    public void validateInputGestureForClassicMatch(String input) {
        if (!HandGesture.isHandClassicGesture(input)) {
            throw new InputMismatchException("Input is not a hand gesture");
        }
    }
    public void validateInputGesture(String input) {
        if (!HandGesture.isInHandGestureList(input)) {
            throw new InputMismatchException("Input is not a hand gesture");
        }
    }

}
