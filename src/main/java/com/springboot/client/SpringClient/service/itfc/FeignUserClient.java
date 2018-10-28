package com.springboot.client.SpringClient.service.itfc;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.client.SpringClient.dto.UserEntity;
import com.springboot.client.SpringClient.service.impl.UserServiceHystric;

@FeignClient(name="spring-producer-server",fallback = UserServiceHystric.class)
public interface FeignUserClient {
  @RequestMapping( value = "/findAll/{name}",method = RequestMethod.GET)
  public List<UserEntity> findAll(@PathVariable("name") String name);
  
  @RequestMapping( value = "/findUserPost",method = RequestMethod.POST)
  public UserEntity findUserPost(@RequestBody UserEntity entity);//复合类型好像默认是POST请求
}