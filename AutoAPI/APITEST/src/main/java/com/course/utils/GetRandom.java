package com.course.utils;

import java.util.Random;

public class GetRandom {
    public static String getRandomChar(int length) {            //生成随机字符串
        char[] chr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(26)]);
        }
        return buffer.toString();
    }
}