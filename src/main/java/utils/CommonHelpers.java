package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonHelpers {

    public static String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

}
