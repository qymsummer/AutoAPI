package com.course.utils;

import org.testng.annotations.Test;

import java.io.IOException;

public class xx {
    @Test
    public void t() throws IOException {
        String s = GetTicket.jsonLoop();
        System.out.println(s);
    }
}
