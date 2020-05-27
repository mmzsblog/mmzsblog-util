package com.java.mmzsblog.controller;

import com.java.mmzsblog.covert.UserCovertBasic;
import com.java.mmzsblog.domain.*;
import com.java.mmzsblog.entity.User;
import com.java.mmzsblog.entity.UserEnum;
import com.java.mmzsblog.enums.UserTypeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：liuhongjiang
 * @description：
 * @date ：2020/3/31 0031 9:16
 */
@RestController
public class TestController {

    @GetMapping("convert")
    public Object convertEntity() {
        // 构建测试需要用到的数据
        List<User> userList = new ArrayList<>();
        User user = User.builder().id(1).name("张三")
                .createTime("2020-04-01 11:05:07").updateTime(LocalDateTime.now())
                .build();
        userList.add(user);
        User user2 = User.builder().id(1).name("李四")
                .createTime("2020-04-01 11:05:07").updateTime(LocalDateTime.now())
                .build();
        userList.add(user2);

        // 返回对象
        List<Object> objectList = new ArrayList<>();
        objectList.addAll(userList);


        // 使用mapstruct
        UserVO1 userVO1 = UserCovertBasic.INSTANCE.toConvertVO1(user);
        objectList.add("userVO1:" + UserCovertBasic.INSTANCE.toConvertVO1(user));
        objectList.add("userVO1转换回实体类user:" + UserCovertBasic.INSTANCE.fromConvertEntity1(userVO1));
        // 输出转换结果
        objectList.add("userVO2:" + " | " + UserCovertBasic.INSTANCE.toConvertVO2(user));
        // 使用BeanUtils
        UserVO2 userVO22 = new UserVO2();
        BeanUtils.copyProperties(user, userVO22);
        objectList.add("userVO22:" + " | " + userVO22);

        //转换集合对象
        objectList.add("userVO1List:" + " | " + UserCovertBasic.INSTANCE.toConvertVOList1(userList));
        objectList.add("userVO2List:" + " | " + UserCovertBasic.INSTANCE.toConvertVOList2(userList));

        UserVO3 userVO3 = UserCovertBasic.INSTANCE.toConvertVO3(user);
        objectList.add("userVO3：" + " | " + userVO3);
        objectList.add("userVO3转换回实体类user：" + " | " + UserCovertBasic.INSTANCE.fromConvertEntity3(userVO3));

        UserVO4 userVO4 = UserCovertBasic.INSTANCE.toConvertVO4(user);
        objectList.add("userVO4：" + " | " + userVO4);
        objectList.add("userVO4转换回实体类user：" + " | " + UserCovertBasic.INSTANCE.fromConvertEntity4(userVO4));

        return objectList;
    }

    @GetMapping("convert2")
    public Object convertEntity2() {
        // 返回对象
        List<Object> objectList = new ArrayList<>();
        // 构建测试需要用到的数据
        UserEnum userEnum = UserEnum.builder().id(1).name("淼淼之森").userTypeEnum(UserTypeEnum.Java).build();
        objectList.add(userEnum);

        UserVO5 userVO5 = UserCovertBasic.INSTANCE.toConvertVO5(userEnum);
        objectList.add("userVO4：" + " | " + userVO5);
        objectList.add("userVO4转换回实体类user：" + " | " + UserCovertBasic.INSTANCE.fromConvertEntity5(userVO5));

        return objectList;
    }
}
