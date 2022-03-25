package com.partick.articleservice.db.pojo;

import lombok.Data;

/**
 * @author partick_peng
 */
@Data
public class User {

    private Long userId;

    private String username;

    private String password;

    private String name;

    private String grade;
}
