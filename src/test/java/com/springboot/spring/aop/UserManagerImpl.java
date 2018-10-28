package com.springboot.spring.aop;
/**
 * UserManagerImpl类，UserManager接口的实现
 * @author perlin
 *
 */
public class UserManagerImpl implements UserManager {
	private String name=null;
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public UserManagerImpl(String name) {
		super();
		this.name = name;
	}

	@Override
	public void addUser(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("Hello"+name);
		System.out.println("----UserManagerImpl.addUser()--------"); 
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		System.out.println("----UserManagerImpl.deleteUser()--------"); 
	}

	@Override
	public String findUserById(int id) {
		// TODO Auto-generated method stub
		System.out.println("----UserManagerImpl.findUserById()--------"); 
		return "name";
	}

	@Override
	public void modifyUser(int id, String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("----UserManagerImpl.modifyUser()--------"); 
	}

}
