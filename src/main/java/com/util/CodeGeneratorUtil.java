package com.util;

import java.util.Random;

public class CodeGeneratorUtil {

    public static String generate() {
        return String.valueOf(new Random().nextInt(9000) + 1000);
    }
}
