package com.springboot.client.SpringClient.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot.client.SpringClient.dto.enumer.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "user", schema = "RECORDS")
@ApiModel(value = "用户信息")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@ApiModelProperty(value = "表物理主键", required = true)
	@Column(name = "id", unique = true, length = 11, updatable = false)
	public Integer id;
	
	@ApiModelProperty(value = "用户主键ID,逻辑主键,业务主键,32位UUID", required = true)
	@Column(name = "userid", unique = true, nullable = false, length = 32, updatable = false)
	public String userId;

	@ApiModelProperty(value = "用户登录名，支持登录", required = true)
	@Column(name = "username", unique = true, nullable = false, length = 20)
	public String userName;

	@ApiModelProperty(value = "用户登录密码", required = true)
	@Column(name = "password", nullable = false, length = 100)
	public String passWord;

	@ApiModelProperty(value = "用户性别")
	@Column(nullable = false, length = 1, updatable = false)
	public Gender gender;
	
	@ApiModelProperty(value = "用户注册的手机号码，支持登录")
	@Column(unique = true, length = 11)
	public String cellphone;
	
	@ApiModelProperty(value = "用户Email地址，支持登录")
	@Column(unique = true, length = 30)
	public String email;
	
	@ApiModelProperty(value = "用户出生日期：yyyy-MM-DD")
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	// @JSONField(format="YYYY-MM-dd HH:mm:ss:SS")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	// @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date birthday;
	
	@ApiModelProperty(value = "用户地址")
	@Column(unique = false, length = 100)
	public String address;
	
	@ApiModelProperty(value = "证件号码")
	@Column(unique = true, length = 20)
	public String credentials;
	
	@ApiModelProperty(value = "证件类型：省份证、护照、其他")
	@Column(name = "certificatetype", unique = false)
	public Integer certificateType;
	
	@ApiModelProperty(value = "用户来源:注册、微信、微博、QQ、百度、淘宝、Github")
	@Column(name = "usertype", unique = false, updatable = false)
	public Integer userType;
	
	@ApiModelProperty(value = "用户创建时间:yyyy-MM-DD HH:mm:ss")
	@Column(name = "createtime", unique = false, nullable = false, updatable = false)
	public Timestamp createTime;
	
	@ApiModelProperty(value = "信息修改时间:yyyy-MM-DD HH:mm:ss")
	@Column(name = "updatetime", unique = false)
	public Timestamp updateTime;
	
	@ApiModelProperty(value = "用户头像")
	@Column(unique = true)
	public byte[] head;
	
	@ApiModelProperty(value = "用户级别:普通用户、VIP用户、白金用户")
	@Column(unique = false)
	public Integer level;
	
	@ApiModelProperty(value = "登录次数")
	@Column(name = "logincount", unique = false, length = 11)
	public Integer loginCount;
	
	@ApiModelProperty(value = "经度")
	@Column(unique = false, precision=3, scale=7)
	public double longitude;
	
	@ApiModelProperty(value = "纬度")
	@Column(unique = false, precision=3, scale=7)
	public double latitude;

}