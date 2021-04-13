package com.java.mmzsblog.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.puwang.cloud.socket.dao.*.mapper*")
public class MybatisPlusConfig {

	private Logger LOG = LoggerFactory.getLogger(MybatisPlusConfig.class);

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driveClassName;

	// durid配置
	@Value("${spring.druid.initialSize}")
	private String initialSize;
	@Value("${spring.druid.minIdle}")
	private String minIdle;
	@Value("${spring.druid.maxActive}")
	private String maxActive;
	@Value("${spring.druid.maxWait}")
	private String maxWait;
	@Value("${spring.druid.timeBetweenEvictionRunsMillis}")
	private String timeBetweenEvictionRunsMillis;
	@Value("${spring.druid.minEvictableIdleTimeMillis}")
	private String minEvictableIdleTimeMillis;
	@Value("${spring.druid.validationQuery}")
	private String validationQuery;
	@Value("${spring.druid.testWhileIdle}")
	private String testWhileIdle;
	@Value("${spring.druid.testOnBorrow}")
	private String testOnBorrow;
	@Value("${spring.druid.testOnReturn}")
	private String testOnReturn;
	@Value("${spring.druid.poolPreparedStatements}")
	private String poolPreparedStatements;
	@Value("${spring.druid.maxOpenPreparedStatements}")
	private String maxOpenPreparedStatements;
	@Value("${spring.druid.connectionProperties}")
	private String connectionProperties;
	@Value("${spring.druid.filters}")
	private String duridFilters;
	@Value("${spring.druid.servlet.loginUsername}")
	private String druidUserName;
	@Value("${spring.druid.servlet.loginPassword}")
	private String druidPassword;
	// private String filters;

	@Bean
	@ConfigurationProperties(prefix = "spring.druid")
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(userName);
		druidDataSource.setPassword(password);
		druidDataSource.setDriverClassName(driveClassName);
		// durid配置
		druidDataSource.setMaxActive(StringUtils.isNotBlank(maxActive) ? Integer.parseInt(maxActive) : 10);
		druidDataSource.setInitialSize(StringUtils.isNotBlank(initialSize) ? Integer.parseInt(initialSize) : 1);
		druidDataSource.setMaxWait(StringUtils.isNotBlank(maxWait) ? Integer.parseInt(maxWait) : 60000);
		druidDataSource.setMinIdle(StringUtils.isNotBlank(minIdle) ? Integer.parseInt(minIdle) : 3);
		druidDataSource.setTimeBetweenEvictionRunsMillis(
				StringUtils.isNotBlank(timeBetweenEvictionRunsMillis) ? Integer.parseInt(timeBetweenEvictionRunsMillis)
						: 60000);
		druidDataSource.setMinEvictableIdleTimeMillis(
				StringUtils.isNotBlank(minEvictableIdleTimeMillis) ? Integer.parseInt(minEvictableIdleTimeMillis)
						: 300000);
		druidDataSource.setValidationQuery(StringUtils.isNotBlank(validationQuery) ? validationQuery : "select 'x'");
		druidDataSource
				.setTestWhileIdle(StringUtils.isNotBlank(testWhileIdle) ? Boolean.parseBoolean(testWhileIdle) : true);
		druidDataSource
				.setTestOnBorrow(StringUtils.isNotBlank(testOnBorrow) ? Boolean.parseBoolean(testOnBorrow) : false);
		druidDataSource
				.setTestOnReturn(StringUtils.isNotBlank(testOnReturn) ? Boolean.parseBoolean(testOnReturn) : false);
		druidDataSource.setPoolPreparedStatements(
				StringUtils.isNotBlank(poolPreparedStatements) ? Boolean.parseBoolean(poolPreparedStatements) : true);
		druidDataSource.setMaxOpenPreparedStatements(
				StringUtils.isNotBlank(maxOpenPreparedStatements) ? Integer.parseInt(maxOpenPreparedStatements) : 20);
		try {
			druidDataSource.setFilters(duridFilters);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		druidDataSource.setConnectionProperties(connectionProperties);
		return druidDataSource;
	}


	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public ServletRegistrationBean druidServlet() {
		LOG.info("init Druid Servlet Configuration ");
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");
		Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("loginUsername", druidUserName);// 用户名
		initParameters.put("loginPassword", druidPassword);// 密码
		initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
		initParameters.put("allow", ""); // IP白名单 (没有配置或者为空，则允许所有访问)
		// initParameters.put("deny", "192.168.20.38");// IP黑名单 (存在共同时，deny优先于allow)
		servletRegistrationBean.setInitParameters(initParameters);
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

}
