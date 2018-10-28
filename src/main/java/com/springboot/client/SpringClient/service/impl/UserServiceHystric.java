package com.springboot.client.SpringClient.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.client.SpringClient.dto.UserEntity;
import com.springboot.client.SpringClient.service.itfc.FeignUserClient;

@Component
public class UserServiceHystric implements FeignUserClient
{

	@Override
	public List<UserEntity> findAll(String name)
	{
		return null;
	}

	@Override
	public UserEntity findUserPost(UserEntity entity)
	{
		return null;
	}

}
