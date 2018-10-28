package com.springboot.spring.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 *2012-5-24 上午12:31:26
 *perlin
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.传统方法
		//People hw = new SayImp();
		//hw.setName("  Spring!");
		//hw.say();
		
		//2.spring的Context方法
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:springContext.xml");
		SayImp hws = (SayImp)ac.getBean("&sayImp");
		hws.say();
		
		ByeImp hwb = (ByeImp)ac.getBean("byeImp");
		hwb.say();
		
		//3.提取出事务调用，做成单例模式
		((ByeImp)ApplicationContextUtil.getApplicationContext().getBean("byeImp")).say();
		
		//4.Spring的bean工厂容器方法
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("springContext.xml"));
		ByeImp byeImp = (ByeImp)factory.getBean("byeImp");
		byeImp.say();
	}
}
