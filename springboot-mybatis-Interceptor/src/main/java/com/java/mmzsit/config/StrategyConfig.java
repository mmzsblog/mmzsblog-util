package com.java.mmzsit.config;

import com.java.mmzsit.framework.mybatisStrategy.StrategyManager;
import com.java.mmzsit.framework.mybatisStrategy.strategy.impl.YYYYMM01Strategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分表策略管理类
 * @author mmzsit
 *
 */
@Configuration
public class StrategyConfig {

	/**
	 * 策略配置类
	 * @return
	 */
	@Bean
	public StrategyManager strategyManager() {
		StrategyManager strategyManager = new StrategyManager();
		strategyManager.addStrategy(StrategyManager._YYYYMM01, new YYYYMM01Strategy());
		return strategyManager;
	}
	
}
