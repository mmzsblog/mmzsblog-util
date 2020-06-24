package com.java.mmzsit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDatas {
    private int id;

    private String name;

    private String age;

    private String information;

    private String updatedate;

}