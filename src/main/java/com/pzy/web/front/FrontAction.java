package com.pzy.web.front;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pzy.entity.Category;
import com.pzy.entity.Photo;
import com.pzy.entity.User;
import com.pzy.service.CategoryService;
import com.pzy.service.PhotoService;
import com.pzy.service.UserService;

@Controller()
@Scope("prototype")
@Namespace("/front")
public class FrontAction  extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private String tip;
	
	private User user;
	
	private File image;
	 
	private String imageFileName;
	 
	private String imageContentType;
	
	private Photo photo;
	
	private String id;
	
	private List<Category> categorys;
	
	private List<Photo> photos;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private CategoryService categoryService;
	
	@Autowired 
	private PhotoService photoService;
	
	private int page=0;
	
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	private final List<String>  allowTypes = new ArrayList<String>(){
		private static final long serialVersionUID = -7478928976664848255L;
		{
			 add("png");
			 add("jpg");
			 add("jpeg");
			 add("gif");
			 add("bmp");
		 }
	 };
	 
	 
	/***
	 * 后台登录首页
	 * @return
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/front/index.jsp") })
     public String index(){
          return SUCCESS;
     }
	/***
	 * 点击登录按钮
	 * @return
	 */
     @Action(value = "login", results = { @Result(name = "success", location = "/WEB-INF/views/front/login.jsp") })
     public String login(){
    	 return SUCCESS;
     }
     
     @Action(value = "dologin", results = { @Result(name = "success", location = "/WEB-INF/views/front/login.jsp") })
     public String dologin(){
    	 User newuser = this.userService.login(user.getName(), user.getPassword());
    	 if(newuser == null	){
    		 this.tip ="用户名密码不正确";
    		 return LOGIN;
    	 }
    	 this.tip =null;	 
    	 ServletActionContext.getRequest().getSession().setAttribute("user",newuser);
    	 this.categorys=categoryService.findAll();
    	 return SUCCESS;
     }
     
     /***
      * 注销退出
      * @return
      */
     @Action(value = "loginout", results = { @Result(name = "success", location = "/WEB-INF/views/front/loginout.jsp") })
     public String loginout(){
    	 	ActionContext.getContext().getSession().remove("user");
    	 	ActionContext.getContext().getSession().clear();
    	 	 this.tip ="退出成功，请重新登录";
    	 	return SUCCESS;
     }
    
     @Action(value = "register", results = { @Result(name = "success", location = "/WEB-INF/views/front/register.jsp") })
     public String register(){
          return SUCCESS;
     }
     
     @Action(value = "center", results = { @Result(name = "success", location = "/WEB-INF/views/front/center.jsp") })
     public String center(){
    	 this.categorys=categoryService.findAll();
          return SUCCESS;
     }
     
     @Action(value = "myinfo", results = { @Result(name = "success", location = "/WEB-INF/views/front/myinfo.jsp") })
     public String myinfo(){
          return SUCCESS;
     }
     @Action(value = "mypic", results = { @Result(name = "success", location = "/WEB-INF/views/front/mypic.jsp") })
     public String mypic(){
    	 User user  = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
    	 Assert.notNull(user,"未登录不允许操作！");
    	 if(page<0)
    		 page=0;
    	 this.photos=this.photoService.findByUser(user.getId(), page, 12).getContent();
    	 System.out.println(this.getPhotos().size());
    	 return SUCCESS;
     }
     @Action(value = "picwall", results = { @Result(name = "success", location = "/WEB-INF/views/front/mypic.jsp") })
     public String picwall(){
    	 this.photos=this.photoService.findAll(1, 20, "合格").getContent();
    	 System.out.println(this.getPhotos().size());
    	 return SUCCESS;
     }
     
     public String uploadimg() throws FileNotFoundException, IOException{
    	 System.out.println("ddddddddd"+this.imageFileName);
    	 if (this.image!=null) {
	        	String fileType = StringUtils.getFilenameExtension(this.imageFileName);
	        	
	        	if(!allowTypes.contains(fileType.toLowerCase())){
	        		this.tip="文件类型不被允许！";
	        		return INPUT;
	        	}
	        	/**新的文件名*/
	        	String newfilename = System.currentTimeMillis()+"."+ StringUtils.getFilenameExtension(this.imageFileName);
	        	/**文件的绝对路径*/
	        	String realPath =  ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/") +"/upload/"+ newfilename; 
	        	FileUtils.copyInputStreamToFile(new FileInputStream(image), new File(realPath));
	        	Photo p = new Photo();
	        	p.setCreateDate(new Date());
	        	p.setState("待审核");
	        
	        	p.setCategory(this.categoryService.find(id));
	        	User user = (User)ActionContext.getContext().getSession().get("user");
	        	p.setUser(user);
	        	p.setUserid(user.getId());
	        	p.setUrl(newfilename);
	        	photoService.save(p);
	        	 this.categorys=categoryService.findAll();
	        	this.tip="文件上传成功！！";
        		return SUCCESS;
	        }
	        else{
	        	this.tip="空文件！";
        		return INPUT;
	        }
     }
     public String doregister(){
    	 userService.save(user);
    	 this.tip="注册成功";
          return SUCCESS;
     }
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Category> getCategorys() {
		return categorys;
	}
	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
     
     
}

