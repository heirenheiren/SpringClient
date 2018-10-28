package com.springboot.client.SpringClient.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springboot.client.SpringClient.dao.UserDao;
import com.springboot.client.SpringClient.dto.UserEntity;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	private static Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

	@Autowired
	private UserDao userDao;

	/** 定义认证用户信息获取来源，密码校验规则等 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		// inMemoryAuthentication 从内存中获取
		// auth.inMemoryAuthentication().withUser("admin").password("123456").roles("USER");
		// jdbcAuthentication从数据库中获取，但是默认是以security提供的表结构
		// usersByUsernameQuery 指定查询用户SQL
		// authoritiesByUsernameQuery 指定查询权限SQL
		// auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query).authoritiesByUsernameQuery(query);
		// 注入userDetailsService，需要实现userDetailsService接口
		auth.userDetailsService(new UserDetailsService()
		{

			@Override
			public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
			{
				logger.debug("userName:" + userName);
				UserEntity userEntity = userDao.findByUserName(userName);
				if (userEntity == null)
				{
					throw new UsernameNotFoundException("User '" + userName + "' not found.");
				}

				return new User(userEntity.getUserName(), userEntity.getPassWord(), getAuthorities(1));
			}

		});
	}

	/**
	 * 获得访问角色权限
	 * 
	 * @param access
	 * @return
	 */
	public Collection<GrantedAuthority> getAuthorities(Integer access)
	{

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		// 所有的用户默认拥有ROLE_USER权限
		logger.debug("Grant ROLE_USER to this user");
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));

		// 如果参数access为1.则拥有ROLE_ADMIN权限
		if (access.compareTo(1) == 0)
		{
			logger.debug("Grant ROLE_ADMIN to this user");
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return authList;
	}

	/** 定义安全策略 */
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable();
	}
}
