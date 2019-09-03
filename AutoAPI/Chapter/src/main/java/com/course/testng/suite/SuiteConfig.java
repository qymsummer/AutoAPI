package com.course.testng.suite;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class SuiteConfig {
    @BeforeSuite
    public void beforeSutie(){
        System.out.println("before suite运行啦");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("after suite运行啦");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("before class运行");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("after Class运行");
    }
}
