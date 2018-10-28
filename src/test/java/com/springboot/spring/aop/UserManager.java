package com.springboot.spring.aop;
/**
 * UserManager接口申明四个方法，deleteUser是要被拦截的方法
 * @author perlin
 *
 */
public interface UserManager {
	public void addUser(String username, String password); 
	public void deleteUser(int id); 
	public void modifyUser(int id, String username, String password); 
	public String findUserById(int id); 
}
