package com.springboot.client.SpringClient.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.client.SpringClient.dao.UserDao;
import com.springboot.client.SpringClient.dto.UserEntity;
import com.springboot.client.SpringClient.service.itfc.UserInterface;
import com.springboot.client.SpringClient.utils.UUIDUtil;

@Service
public class UserService implements UserInterface
{
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserEntity findByUserId(String userId)
	{
		return userDao.findByUserId(userId);
	}

	@Override
	public UserEntity findByUserNameAndPassWord(String userName, String PassWord)
	{
		return userDao.findByUserNameAndPassWord(userName, PassWord);
	}

	@Override
	public Boolean saveUser(UserEntity userEntity)
	{
		UserEntity exUe = userDao.findByUserName(userEntity.getUserName());
		if(exUe != null)
		{
			return false;
		}
		
		PasswordEncoder pe = new BCryptPasswordEncoder();
		userEntity.setPassWord(pe.encode(userEntity.getPassWord()));
		userEntity.setUserId(UUIDUtil.get32UUID());
		logger.debug("userEntity:", userEntity.toString());
		
		UserEntity ue = userDao.saveAndFlush(userEntity);
		
		if(ue == null || StringUtils.isEmpty(ue.getId()))
		{
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean deleteUser(UserEntity userEntity)
	{
		UserEntity ue = userDao.findById(userEntity.getId());
		if(ue == null)
		{
			return false;
		}
		
		userDao.delete(userEntity);
		return true;
	}

	@Override
	public Boolean modifyUser(UserEntity userEntity)
	{
		UserEntity ue = userDao.findById(userEntity.getId());
		if(ue == null)
		{
			return false;
		}
		PasswordEncoder pe = new BCryptPasswordEncoder();
		userEntity.setPassWord(pe.encode(userEntity.getPassWord()));
		
		userDao.save(userEntity);
		return true;
	}

	@Override
	public List<UserEntity> listUser(UserEntity userEntity)
	{
		return userDao.findAll();
	}

	@Override
	public int count()
	{
		return 0;
	}

	@Override
	public List<Map<String, Object>> queryUser(int start, int rows)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
