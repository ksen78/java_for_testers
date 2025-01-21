package common;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {

    public static String randomString(int n){
        var rnd = new Random();
        Supplier<Integer> randomNambers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNambers)
                .limit(n)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;
    }
}
