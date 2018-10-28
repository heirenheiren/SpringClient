package com.springboot.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * SecurityHandler类实现了InvocationHandler接口
 * @author perlin
 *
 */
public class SecurityHandler implements InvocationHandler {
	
	private Object targetObject;
	
	//将欲代理的对象传入，返回一个代理对象
	public Object newProxy(Object targetObject) { 
		this.targetObject = targetObject; 
		//三个参数，第一个是欲代理对象的类加载器，第二个是得到这个类的接口集合，第三个参数是一个handler
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this); 
	}
	
	//对欲代理对象的方法的调用将会调用这个代理对象的invoke方法
	//第一个参数是这个代理对象，第二个参数是欲代理对象实现方法，第三个是方法的参数集合
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//开头则调用下面逻辑 
		checkSecurity(); 
		Object ret = null; 
		try {
			//调用欲代理对象的相应方法
			for (int i = 0; i < args.length; i++)
			{
				System.out.println(args[i]);
			}
			ret = method.invoke(this.targetObject, args);
			System.out.println("方法的返回值："+ret);
			checkSecurity();
		} catch (Exception e) {
			e.printStackTrace();
			throw new java.lang.RuntimeException(e); 
		}
		return ret;
	}
	private void checkSecurity() {
		System.out.println("我不用每调用一个方法都写，我只需要写在动态代理里面就行了。"); 
	}

}
