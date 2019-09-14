package com.course.bvtcase;

import com.course.utils.TokenFile;
import org.testng.annotations.Test;

import java.io.IOException;

public class add {
    @Test
    public void a() throws IOException {
        String a= TokenFile.readFile();
        System.out.println(a);
    }
}
