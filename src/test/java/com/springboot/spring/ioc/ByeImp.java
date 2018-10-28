package com.springboot.spring.ioc;

public class ByeImp implements People {

	//无参构造函数
	public ByeImp() {
		System.out.println("ByeImp对象被Spring创建。");
	}
	
	private String name=null;
	private SayImp sayImp=null;
	public void setSayImp(SayImp sayImp) {
		this.sayImp = sayImp;
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
			System.out.println("Nobody to say goodbye!");
		System.out.println("GoodBye"+name);
		sayImp.say();
	}

}
