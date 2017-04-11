package com.pzy.entity;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "tag") 
public class Category {
	@Id
	private String id;
	
	private String name;
	
	private String remark;
	
	private Date createDate;
	
	
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@JSON(format="yyyy-MM-dd HH:mm")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
