package com.pzy.web.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pzy.entity.Photo;
import com.pzy.service.PhotoService;
/***
 * 订单管理
 *
 */
@Controller
@Namespace("/admin/photo")
public class PhotoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Integer sEcho = 1;
	private Integer iDisplayStart = 0;
	private Integer iDisplayLength = 10;
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	private String name;
	private String id;
	private Photo photo;
	private List<Photo> photos;
	@Autowired
	private PhotoService photoService;

	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/photo/index.jsp") })
	public String index() {
		List<String> tag = new ArrayList<String>();
		tag.add("红烧狮子头");
		Photo photo = new Photo();
		photo.setUrl("11");
		photo.setCreateDate(new Date());
		photo.setState("待审核");
		photo.setTags(tag);
		//photoService.save(photo);
		return SUCCESS;
	}

	@Action(value = "list", results = { @Result(name = "success", type = "json") }, params = {
			"contentType", "text/html" })
	public String list() {
		int pageNumber = (int) (iDisplayStart / iDisplayLength) + 1;
		int pageSize = iDisplayLength;
		Page<Photo> list = photoService.findAll(pageNumber, pageSize, name);
		resultMap.put("aaData", list.getContent());
		resultMap.put("iTotalRecords", list.getTotalElements());
		resultMap.put("iTotalDisplayRecords", list.getTotalElements());
		resultMap.put("sEcho", sEcho);
		return SUCCESS;
	}
	
	
	public String approveok() {
		Photo photo = this.photoService.find(this.id);
		photo.setState("合格");
		this.photoService.save(photo);
		resultMap.put("state", "success");
		resultMap.put("msg", "操作成功！");
		return SUCCESS;
	}

	public String approvenotok() {
		Photo photo = this.photoService.find(this.id);
		photo.setState("不合格");
		this.photoService.save(photo);
		resultMap.put("state", "success");
		resultMap.put("msg", "操作成功！");
		return SUCCESS;
	}
	/* ~~~~~~~~get and setter~~~~~~~~~ */
	@JSON
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public Integer getSEcho() {
		return sEcho;
	}

	public void setSEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}

	public Integer getIDisplayStart() {
		return iDisplayStart;
	}

	public void setIDisplayStart(Integer idisplayStart) {
		this.iDisplayStart = idisplayStart;
	}

	public Integer getIDisplayLength() {
		return iDisplayLength;
	}

	public void setIDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	
}
