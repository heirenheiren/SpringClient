package com.springboot.client.SpringClient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.client.SpringClient.dto.UserEntity;
import com.springboot.client.SpringClient.service.impl.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户", tags = { "用户类" })
@RestController
@RequestMapping("user")
public class UserController
{
	@Autowired
	private UserService userService;
	
	@ApiOperation("根据用户ID获取用户信息")
	@ApiImplicitParam(name = "userId", value = "入参", dataType = "string", paramType = "query")
	@GetMapping("/findByUserId/{userId}")
	public UserEntity findByUserId(@PathVariable("userId") String userId)
	{
		return userService.findByUserId(userId);
	}
	
	@ApiOperation("新建用户")
	@ApiImplicitParam(name = "userEntity", value = "入参", dataType = "UserEntity", paramType = "save")
	@PostMapping("/save")
	public Boolean save(@RequestBody UserEntity userEntity)
	{
		return userService.saveUser(userEntity);
	}
	
	@ApiOperation("删除用户信息")
	@ApiImplicitParam(name = "userEntity", value = "入参", dataType = "UserEntity", paramType = "delete")
	@DeleteMapping("/delete")
	public Boolean delete(@RequestBody UserEntity userEntity)
	{
		return userService.deleteUser(userEntity);
	}
	
	@ApiOperation("修改用户信息")
	@ApiImplicitParam(name = "userEntity", value = "入参", dataType = "UserEntity", paramType = "modify")
	@PutMapping("/modify")
	public Boolean modify(@RequestBody UserEntity userEntity)
	{
		return userService.modifyUser(userEntity);
	}
	
	@ApiOperation("分页查询用户信息")
	@ApiImplicitParam(name = "userEntity", value = "入参", dataType = "UserEntity", paramType = "list")
	@PostMapping("/list")
	@ResponseBody
	public PageInfo<UserEntity> list(@RequestBody UserEntity userEntity)
	{
		//引入分页查询，使用PageHelper分页功能  
	    //在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(2,5);
	    //startPage后紧跟的这个查询就是分页查询 
	    List<UserEntity> listUser = userService.listUser(userEntity);
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo<UserEntity> pageInfo = new PageInfo<>(listUser,1);  
	    //pageINfo封装了分页的详细信息，也可以指定连续显示的页数  
		return pageInfo;
	}
}
