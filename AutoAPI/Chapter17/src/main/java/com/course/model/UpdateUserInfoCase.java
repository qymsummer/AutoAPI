package com.course.model;


import lombok.Data;

/**
 * 更新用户信息接口的测试用例
 */
@Data
public class UpdateUserInfoCase {

    private int id;
    private int userId;
    private String userName;
    private String sex;
    private String age;
    private String permission;
    private String isDelete;
    private String expected;

}