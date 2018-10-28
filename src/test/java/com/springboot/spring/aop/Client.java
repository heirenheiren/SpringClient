package com.springboot.spring.aop;

public class Client {

	/**Spring整理10 -- 动态代理
	 * 问题：我们有一个实现类，分别有添加、删除、更新、查询等方法，
	 * 如果我们现在要在执行方法加一个验证权限的方法，我们该如何做呢?
	 * 有人会这还不简单，新建一个方法，在每个方法前调用一个就行。
	 * 这种是可以完成功能，但它不够灵活，也破坏了我们的原有的代码。
	 * 那该如何做才能更灵活呢?我们现在用jdk提供的动态代理模式来实现。
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SecurityHandler handler = new SecurityHandler(); 
		UserManager userManager = (UserManager)handler.newProxy(new UserManagerImpl("fg")); 
		//userManager.deleteUser(1);
		//userManager.addUser("username", "password");
		userManager.findUserById(1);
	}
}
