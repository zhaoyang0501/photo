
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
import com.pzy.entity.User;
import com.pzy.repository.UserRepository;

@Service
public class UserService {
     @Autowired
     private UserRepository userRepository;
     
     public  User save(User user){
    	 return userRepository.save(user);
     }
     public User login(String userName,String password){
    	 List<User> users=userRepository.findByNameAndPassword(userName,password);
    	 return users.size()==0?null:users.get(0);
     }
     
	public void delete(String id){
	    userRepository.delete(id);
	}
	public User find(String id){
		  return userRepository.findOne(id);
	}
	public User findByName(String name){
		  List<User> users=userRepository.findByName(name);
		  if(users!=null&&users.size()>=1)
			  return users.get(0);
		  else return null;
	}
	
	 public Page<User> findAll(final int pageNumber, final int pageSize,final String name){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Page<User> result ;
         if(!StringUtils.isBlank(name)){
        	 result =  (Page<User>) userRepository.findByNameLike(name, pageRequest);
         }else{
        	 result =  (Page<User>) userRepository.findAll( pageRequest);
         }
         return result;
     }
}