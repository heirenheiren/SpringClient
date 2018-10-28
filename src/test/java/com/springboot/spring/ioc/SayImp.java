package com.springboot.spring.ioc;

/**
 *2012-5-24 上午12:29:05
 *perlin 可以不用实现一个接口，而是把属性直接写在这个类里面，在添加get和set方法。
 */
public class SayImp implements People {
	
	private String name=null;
	
	// 无参构造函数
	public SayImp()
	{
		System.out.println("SayImp对象被Spring创建。");
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;
	}

	@Override
	public void say() {
		// TODO Auto-generated method stub
		if(name==null)
			System.out.println("Hello World! Name is null."); 
		System.out.println("Hello"+name);
	}

}