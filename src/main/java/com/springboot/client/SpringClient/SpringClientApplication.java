package com.springboot.client.SpringClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 例子：https://blog.csdn.net/zzzzzz55300411/article/category/6672921
 * 如果选用的注册中心是eureka，那么就推荐@EnableEurekaClient，如果是其他的注册中心，
 * 那么推荐使用@EnableDiscoveryClient。
 * https://blog.csdn.net/zhongzunfa/article/details/79481011
 */
//@EnableDiscoveryClient
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker // 开启容错
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@EnableAutoConfiguration
public class SpringClientApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringClientApplication.class, args);
	}

	// 配置mybatis的分页插件pageHelper
	/*
	 * @Bean public PageHelper pageHelper() { PageHelper pageHelper = new
	 * PageHelper(); Properties properties = new Properties();
	 * properties.setProperty("offsetAsPageNum", "true");
	 * properties.setProperty("rowBoundsWithCount", "true");
	 * properties.setProperty("reasonable", "true");
	 * properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
	 * pageHelper.setProperties(properties); return pageHelper; }
	 */

	/*@Bean
	public Converter<String, Date> addNewConvert()
	{
		return new Converter<String, Date>()
		{
			@Override
			public Date convert(String source)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try
				{
					date = sdf.parse((String) source);
				}
				catch (ParseException e)
				{
					e.printStackTrace();
				}
				return date;
			}
		};
	}*/
}
