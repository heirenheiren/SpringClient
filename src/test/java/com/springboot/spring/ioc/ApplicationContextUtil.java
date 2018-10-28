package com.springboot.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil
{
	private static ApplicationContext ac = null;

	private ApplicationContextUtil()
	{
		System.out.println("单例模式");
	}

	static
	{
		System.out.println("Spring容器初始化");
		ac = new ClassPathXmlApplicationContext("classpath:springContext.xml");
	}

	public static ApplicationContext getApplicationContext()
	{
		if (ac == null)
		{
			System.out.println("空空");
		}
		return ac;
	}
}
