package com.course.bvtcase;

import com.course.utils.TokenFile;
import org.testng.annotations.Test;

import java.io.IOException;

public class dda {
    @Test
    public void daa() throws IOException {
        String value = TokenFile.readFile();
        System.out.println(value);
    }
}
