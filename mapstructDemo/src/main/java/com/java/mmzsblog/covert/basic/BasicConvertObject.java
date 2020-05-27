package com.java.mmzsblog.covert.basic;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mappings;
import java.util.Collection;
import java.util.List;

/**
 * 基础转换类，提供基本的几个方法，直接继承就可以，如果有需要写Mappings的写在 {@link #to(Object)} 方法上
 * 并且接口类上一定要加上 {@link org.mapstruct.Mapper} 注解
 * 默认注解，需要单独定义 如 UserCovertExtends INSTANCE = Mappers.getMapper(UserCovertExtends.class); 以此进行实例创建和调用
 * 或者如下
 *
 * @Mapper(componentModel = "spring") 此注解可通过spring进行注入。
 */
public interface BasicConvertObject<SOURCE, TARGET> {
    /**
     * 如有需要自定义该注解即可
     * 例如：
     *
     * @Mappings({
     * @Mapping(source = "id", target = "userId"),
     * @Mapping(source = "name", target = "userName")
     * })
     * <p></p>
     * 重写此注解时一定要注意 返回值（TARGET） 和 参数（SOURCE） 的顺序
     */
    @Mappings({})
    @InheritConfiguration
    TARGET toConvertVO(SOURCE source);

    @InheritConfiguration
    List<TARGET> toConvertVOList(Collection<SOURCE> source);

    @InheritInverseConfiguration
    SOURCE fromConvertEntity(TARGET source);

    @InheritInverseConfiguration
    List<SOURCE> fromConvertEntityList(Collection<TARGET> source);
}

