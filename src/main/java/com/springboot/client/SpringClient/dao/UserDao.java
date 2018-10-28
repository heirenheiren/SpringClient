package com.springboot.client.SpringClient.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.client.SpringClient.dto.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, String>
{
	public UserEntity findByUserId(String userId);
	
	public UserEntity findByUserName(String userName);
	
	public UserEntity findByUserNameAndPassWord(String userName,String PassWord);

	public UserEntity findById(Integer id);
}
