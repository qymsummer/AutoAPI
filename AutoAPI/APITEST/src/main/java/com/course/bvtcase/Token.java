package com.course.bvtcase;

import com.course.utils.TokenFile;
import org.testng.annotations.Test;

public class Token {
    @Test
    public void token(){
        String a = getaToken();
        TokenFile.witerToken(a);
    }
    public static String getaToken(){
        String a = "1212";
        return a;
    }
}
