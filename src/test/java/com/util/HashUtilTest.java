package com.util;

import org.junit.Assert;
import org.junit.Test;

public class HashUtilTest {

    private static final String PASSWORD = "1";

    @Test
    public void whenSaltedPasswordEqualsExpectedHash_ThenOk() {
        byte[] salt = {0, 1, 2, 3, 4, 5};
        String result = HashUtil.getSaltedPassword(PASSWORD, salt);
        String expectedResult = "52985c24a33dc01d8065a9ce07acde5fc87509d5e28fb276513d2186ff2cceb5";
        Assert.assertNotNull("Result is null", result);
        Assert.assertEquals(expectedResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSaltIsEmptyThenThrowException() {
        byte[] salt = new byte[16];
        String hash = HashUtil.getSaltedPassword(PASSWORD, salt);
    }
}