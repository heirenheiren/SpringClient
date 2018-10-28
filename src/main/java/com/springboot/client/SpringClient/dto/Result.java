package com.springboot.client.SpringClient.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 200成功
	@ApiModelProperty(value = "错误码", name = "错误码")
	private int code;

	// 返回消息，成功为“success”，失败为具体失败信息
	@ApiModelProperty(value = "错误码描述", name = "错误码描述")
	private String message;

	// 返回数据
	@ApiModelProperty(value = "数据对象", name = "数据对象")
	private T data;

	public Result()
	{
	}

	public Result(int code, String message)
	{
		this.code = code;
		this.message = message;
	}

	public Result(int code, String message, T data)
	{
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}

	@Override
	public String toString()
	{
		return "Result{" + "code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
	}
}
