package com.java.mmzsit.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
/**
 * @author ：mmzsit
 * @description：
 * @date ：2019/6/13 11:45
 */


@Configuration
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 注册Servlet信息， 配置监控视图
     *
     * @return 返回监控注册的servlet对象
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        /**
         * 禁用HTML页面上的“Rest All”功能
         */
        initParameters.put("resetEnable", "false");
        /**
         * ip白名单（没有配置或者为空，则允许所有访问）
         */
//        initParameters.put("allow", "10.8.9.115");
        /**
         * 监控页面登录用户名
         */
        initParameters.put("loginUsername", "admin");
        /**
         * 监控页面登录用户密码
         */
        initParameters.put("loginPassword", "admin");
        initParameters.put("deny", ""); //ip黑名单
        /**
        如果某个ip同时存在，deny优先于allow
         */
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }
}
