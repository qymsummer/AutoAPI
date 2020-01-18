package com.course.utils;

import java.util.Random;
/**
 * @author qym
 */
public class GetNum {
    public static String getRandomChar(int length) {
        char[] chr = {'0','1','2','3','4','5','6','7','8','9'};
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(10)]);
        }
        return buffer.toString();
    }
}