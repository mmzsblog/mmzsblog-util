package com.java.mmzsit.config;

import com.java.mmzsit.framework.interceptor.TableSplitInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author ：mmzsit
 * @description：
 * @date ：2019/6/14 10:08
 */
@Configuration
public class InterceptConfig {

    /**
     * mybatis分表拦截器
     * @return
     */
    @Bean
    public TableSplitInterceptor initTableSplitInterceptor() {
        return new TableSplitInterceptor();
    }
}
