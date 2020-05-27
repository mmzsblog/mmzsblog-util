package com.java.mmzsblog.entity;

import com.java.mmzsblog.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author ：liuhongjiang
 * @description：TODO
 * @date ：2020/3/31 0031 17:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String name;
    private String createTime;
    private LocalDateTime updateTime;
    private UserTypeEnum userTypeEnum;
}
