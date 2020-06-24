package com.java.mmzsblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：created by mmzsblog
 * @date ：created at 2020/06/23 16:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private static final long serialVersionUID = -8183942491930372236L;
    private Long userId;
    private String username;
    private String password;
}
