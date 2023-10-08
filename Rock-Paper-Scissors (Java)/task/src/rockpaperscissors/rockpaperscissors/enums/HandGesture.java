package rockpaperscissors.rockpaperscissors.enums;

import java.util.ArrayList;
import java.util.Objects;

public class HandGesture {

    public static ArrayList<HandGesture> gestures = new ArrayList<HandGesture>(2);

    public final String value;

    public HandGesture(String value) {
        this.value = value;
    }

    public static void addGesture(String value) {
        HandGesture gesture = new HandGesture(value);
        HandGesture.gestures.add(gesture);

    };



    public static HandGesture convert(String input) {
        for (HandGesture gesture: gestures) {
            if (Objects.equals(gesture.value, input)) {
                return gesture;
            }
        }

        return HandGesture.gestures.get(0);
    }

    public static boolean isInHandGestureList(String input) {
        boolean result = false;

        for (HandGesture gesture: HandGesture.gestures) {
            if (Objects.equals(gesture.value, input)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean isHandClassicGesture(String input) {
        boolean result = false;
        for (HandGesture gesture: HandGesture.gestures) {
            if (Objects.equals(input, gesture.value)) {
                result = true;
                break;
            }
        }
        return result;
    }


    public static int findIndex(HandGesture t) {
        if (HandGesture.gestures == null) {
            return -1;
        }
        int len = HandGesture.gestures.size();
        int i = 0;
        while (i < len) {
            if (Objects.equals(HandGesture.gestures.get(i).value, t.value)) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }

    public static int findIndex(ArrayList<HandGesture> arr, HandGesture t) {
        if (arr == null) {
            return -1;
        }
        int len = arr.size();
        int i = 0;
        while (i < len) {
            if (Objects.equals(arr.get(i).value, t.value)) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }

}
