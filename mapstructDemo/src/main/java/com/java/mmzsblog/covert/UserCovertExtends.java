package com.java.mmzsblog.covert;

import com.java.mmzsblog.covert.basic.BasicConvertObject;
import com.java.mmzsblog.domain.UserVO1;
import com.java.mmzsblog.domain.UserVO2;
import com.java.mmzsblog.entity.User;
import com.java.mmzsblog.util.DateTransform;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
/**
 * @author ：liuhongjiang
 * @description：TODO
 * @date ：2020/3/31 0031 17:03
 */
//@Mapper(componentModel = "spring")
// 定义属性类型不同时，转换时可能会使用到的类
@Mapper(componentModel = "spring",uses = DateTransform.class)
public interface UserCovertExtends extends BasicConvertObject<UserVO1, UserVO2> {
    UserCovertExtends INSTANCE = Mappers.getMapper(UserCovertExtends.class);

    /**
     * 字段数量类型数量相同，利用工具BeanUtils也可以实现类似效果
     * @param source
     * @return
     */
//    UserVO1 toConvertVO(User source);
//    User fromConvertEntity(UserVO1 userVO1);

    /**
     * 字段数量类型相同,数量少：仅能让多的转换成少的
     * @param source
     * @return
     */
    UserVO2 toConvertVO(User source);
    /**
     * 字段类型不一致:
     * 以下的类型之间是mapstruct自动进行类型转换的:
     *           1、基本类型及其他们对应的包装类型。
     *              此时mapstruct会自动进行拆装箱。不需要人为的处理
     *           2、基本类型的包装类型和string类型之间
     * @param source
     * @return
     */
//    @Mappings({
//            @Mapping(target = "createTime", expression = "java(com.java.mmzsblog.util.DateTransform.strToDate(source.getCreateTime()))"),
//    })
//    UserVO3 toConvertVO(User source);
//
//    User fromConvertEntity(UserVO3 userVO3);
    /**
     * 字段类型一致，名字不一致
     * @param source
     * @return
     */
//    @Mappings({
//            @Mapping(source = "id", target = "userId"),
//            @Mapping(source = "name", target = "userName")
//    })
//    UserVO4 toConvertVO(User source);
//    User fromConvertEntity(UserVO4 userVO4);
}
