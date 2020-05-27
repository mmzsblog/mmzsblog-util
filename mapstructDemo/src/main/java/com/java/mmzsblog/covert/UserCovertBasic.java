package com.java.mmzsblog.covert;

import com.java.mmzsblog.domain.*;
import com.java.mmzsblog.entity.User;
import com.java.mmzsblog.entity.UserEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author ：liuhongjiang
 * @description：先普通使用
 * @date ：2020/3/31 0031 17:03
 */
// 定义
@Mapper(componentModel = "spring")
//@Mapper(componentModel = "spring",uses = DateTransform.class)
public interface UserCovertBasic {
    UserCovertBasic INSTANCE = Mappers.getMapper(UserCovertBasic.class);

    /**
     * 字段数量类型数量相同，利用工具BeanUtils也可以实现类似效果
     * @param source
     * @return
     */
    UserVO1 toConvertVO1(User source);
    User fromConvertEntity1(UserVO1 userVO1);

    List<UserVO1> toConvertVOList1(List<User> source);

    /**
     * 字段数量类型相同,数量少：仅能让多的转换成少的
     * @param source
     * @return
     */
    UserVO2 toConvertVO2(User source);
    List<UserVO2> toConvertVOList2(List<User> source);

    /**
     * 字段类型不一致:
     * 以下的类型之间是mapstruct自动进行类型转换的:
     * 1、基本类型及其他们对应的包装类型。
     * 此时mapstruct会自动进行拆装箱。不需要人为的处理
     * 2、基本类型的包装类型和string类型之间
     *
     * @param source
     * @return
     */
    @Mappings({
            @Mapping(target = "createTime", expression = "java(com.java.mmzsblog.util.DateTransform.strToDate(source.getCreateTime()))"),
    })
    UserVO3 toConvertVO3(User source);

    User fromConvertEntity3(UserVO3 userVO3);
    /**
     * 字段类型一致，名字不一致
     * @param source
     * @return
     */
    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "name", target = "userName")
    })
    UserVO4 toConvertVO4(User source);
    User fromConvertEntity4(UserVO4 userVO4);


    /**
     * 字段类型一致，名字不一致
     *
     * @param source
     * @return
     */
    @Mapping(source = "userTypeEnum", target = "type")
    UserVO5 toConvertVO5(UserEnum source);

    UserEnum fromConvertEntity5(UserVO5 userVO5);
}
