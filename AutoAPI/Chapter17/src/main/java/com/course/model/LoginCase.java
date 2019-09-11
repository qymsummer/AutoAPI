package com.course.model;

import lombok.Data;

/**
 * 登录接口的测试用例
 */
@Data
public class LoginCase {
    private int id;
    private String userName;
    private String password;
    private String expected;
}