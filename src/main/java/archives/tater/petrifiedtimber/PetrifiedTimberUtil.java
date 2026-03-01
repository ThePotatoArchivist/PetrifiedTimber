package archives.tater.petrifiedtimber;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PetrifiedTimberUtil {
    private PetrifiedTimberUtil() {}

    public static String snakeToTitle(String text) {
        return Arrays.stream(text.split("_"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
