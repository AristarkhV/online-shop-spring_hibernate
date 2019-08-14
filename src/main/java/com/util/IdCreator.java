package com.util;

public class IdCreator {
    private static Long idCounter = 0L;

    public static synchronized Long idCreator()
    {
        return idCounter++;
    }
}
