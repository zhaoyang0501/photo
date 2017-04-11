package com.pzy.entity;

import java.io.Serializable;
import java.util.Date;


import org.apache.struts2.json.annotations.JSON;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/***
 * 前台注册用户
 * @author 263608237@qq.com
 */

@Document(collection = "user") 
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1276885901230182151L;
	
	@Id
	private String id;
	
	private String name;
	
	private String password;
	private String nickname;
	private String email;
	private String address;
	private Date birthday;
	private String job;
	private String sex;
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}