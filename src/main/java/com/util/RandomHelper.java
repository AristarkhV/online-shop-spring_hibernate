package com.util;

public class RandomHelper {
        public static String generateCode() {
            return String.valueOf((int) (1000 + Math.random() * 9595));
    }
}
