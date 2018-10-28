package com.springboot.client.SpringClient.service.itfc;

import java.util.List;
import java.util.Map;

import com.springboot.client.SpringClient.dto.UserEntity;

public interface UserInterface
{
	public List<UserEntity> listUser(UserEntity userEntity);
	
	public Boolean saveUser(UserEntity userEntity);
	
	public Boolean deleteUser(UserEntity userEntity);
	
	public Boolean modifyUser(UserEntity userEntity);
	
	public UserEntity findByUserId(String userId);

	public UserEntity findByUserNameAndPassWord(String userName, String PassWord);

	int count();

	List<Map<String, Object>> queryUser(int start, int rows);
	
}
