package com.course.model;

import lombok.Data;

/**
 * 获取用户信息的测试用例
 */
@Data
public class GetUserInfoCase {
    private int userId;
    private String expected;
}