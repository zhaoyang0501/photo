package com.pzy.web.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pzy.entity.AdminUser;
import com.pzy.service.AdminUserService;
/***
 * 对于权限管理
  @author 263608237@qq.com
 *
 */
@Controller
@Namespace("/admin/adminuser")
@ParentPackage("json-default")
public class AdminUserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Integer sEcho = 1;
	private Integer iDisplayStart = 0;
	private Integer iDisplayLength = 10;
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private String name;
	private String id;
	private AdminUser adminuser;
	private List<AdminUser> adminUsers;
	
	private String tip;
	private String passwordnew;

	@Autowired
	private AdminUserService adminUserService;
	
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/useradmin/index.jsp") })
	public String index() {
		return SUCCESS;
	}
	@Action(value = "reset", results = { @Result(name = "success", location = "/WEB-INF/views/admin/useradmin/reset.jsp") })
	public String reset() {
		return SUCCESS;
	}
	@Action(value = "doreset", results = { @Result(name = "success", location = "/WEB-INF/views/admin/useradmin/reset.jsp"), @Result(name = "input", location = "/WEB-INF/views/admin/useradmin/reset.jsp") })
	public String doreset() {
		AdminUser old=(AdminUser)ActionContext.getContext().getSession().get("adminuser");
		if(!old.getPassword().equals(adminuser.getPassword())){
			this.tip="旧密码不正确";
			return INPUT;
		}
		old.setPassword(this.getPasswordnew());
		adminUserService.save(old);
		ActionContext.getContext().getSession().put("adminuser",old);
		this.tip="修改正确";
		return SUCCESS;
	}
	/***
	 * 点击查询按钮触发的AJAX请求，分页查询
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", type = "json") }, params = {
			"contentType", "text/html" })
	public String list() {
		int pageNumber = (int) (iDisplayStart / iDisplayLength) + 1;
		int pageSize = iDisplayLength;
		Page<AdminUser> list = adminUserService.findAll(pageNumber, pageSize,name);
		resultMap.put("aaData", list.getContent());
		resultMap.put("iTotalRecords", list.getTotalElements());
		resultMap.put("iTotalDisplayRecords", list.getTotalElements());
		resultMap.put("sEcho", sEcho);
		return SUCCESS;
	}
	/***
	 * 点击删除按钮
	 * @return
	 */
	@Action(value = "delete", results = { @Result(name = "success", type = "json") }, params = {
			"contentType", "text/html" })
	public String delete() {
		adminUserService.delete(id);
		resultMap.put("state", "success");
		resultMap.put("msg", "删除成功");
		return SUCCESS;
	}
	/***
	 * 点击编辑按钮
	 * @return
	 */
	@Action(value = "get", results = { @Result(name = "success", type = "json") }, params = {
			"contentType", "text/html" })
	public String get() {
		resultMap.put("adminuser", adminUserService.findOne(id));
		resultMap.put("state", "success");
		resultMap.put("msg", "删除成功");
		return SUCCESS;
	}
	/***
	 * 修改时点击保存按钮
	 * @return
	 */
	@Action(value = "update", results = { @Result(name = "success", type = "json") }, params = {
			"contentType", "text/html" })
	public String update() {
		AdminUser bean = adminUserService.findOne(adminuser.getId());
		bean.setName(adminuser.getName());
		bean.setRemark(adminuser.getRemark());
		bean.setRole1(adminuser.getRole1());
		bean.setRole2(adminuser.getRole2());
		bean.setRole3(adminuser.getRole3());
		bean.setRole4(adminuser.getRole4());
		bean.setRole5(adminuser.getRole5());
		bean.setRole6(adminuser.getRole6());
		adminUserService.save(bean);
		resultMap.put("state", "success");
		resultMap.put("msg", "修改成功");
		return SUCCESS;
	}

	@Action(value = "save", results = { @Result(name = "success", type = "json") }, params = {
			"contentType", "text/html" })
	public String save() {
		adminuser.setId(null);
		adminUserService.save(adminuser);
		resultMap.put("state", "success");
		resultMap.put("msg", "保存成功");
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

	public AdminUser getAdminuser() {
		return adminuser;
	}

	public void setAdminuser(AdminUser adminUser) {
		this.adminuser = adminUser;
	}

	public List<AdminUser> getAdminUsers() {
		return adminUsers;
	}

	public void setAdminUsers(List<AdminUser> adminUsers) {
		this.adminUsers = adminUsers;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getPasswordnew() {
		return passwordnew;
	}
	public void setPasswordnew(String passwordnew) {
		this.passwordnew = passwordnew;
	}
}