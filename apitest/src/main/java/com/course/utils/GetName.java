package com.course.utils;

import java.util.Random;

public class GetName {
    public static String getRandomChar(int length) {
        char[] chr = {'张','王','赵','刘','聂','尚','贾','李','胡','樊','吴','诸','关','闫'};
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(14)]);
        }
        return buffer.toString();
    }
}