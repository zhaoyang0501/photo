package com.pzy.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pzy.entity.AdminUser;
import com.pzy.repository.AdminUserRepository;

@Service
public class AdminUserService {
	 @Autowired
     private AdminUserRepository adminUserRepository;
     
     public  AdminUser save(AdminUser adminUser){
    	 return adminUserRepository.save(adminUser);
     }
     public  void delete(String id){
    	  adminUserRepository.delete(id);
     }
     public  AdminUser findOne(String id){
   	  return adminUserRepository.findOne(id);
     }
     public AdminUser login(String adminUserName,String password){
    	 List<AdminUser> adminUsers=adminUserRepository.findByNameAndPassword(adminUserName,password);
    	 return adminUsers.size()==0?null:adminUsers.get(0);
     }
     
     public Page<AdminUser> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Page<AdminUser> result ;
         if(!StringUtils.isBlank(name)){
        	 result =  (Page<AdminUser>) adminUserRepository.findByNameLike(name, pageRequest);
         }else{
        	 result =  (Page<AdminUser>) adminUserRepository.findAll( pageRequest);
         }
         return result;
     }
	
}