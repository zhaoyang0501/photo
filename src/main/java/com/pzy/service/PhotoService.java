package com.pzy.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pzy.entity.Photo;
import com.pzy.entity.User;
import com.pzy.repository.PhotoRepository;

@Service
public class PhotoService {
	
	
	@Autowired
	private PhotoRepository photoRepository;
	
	public  Photo save(Photo pPhoto){
	    	 return photoRepository.save(pPhoto);
	}
	public  Photo find(String id){
   	 return photoRepository.findOne(id);
	}
  
	public  List<Photo> findByUser(User user){
   	 return photoRepository.findByUserid(user.getId());
	}
	
	public  Page<Photo> findByUser(String user,Integer page,Integer pagesize){
		  PageRequest pageRequest = new PageRequest(page, pagesize, new Sort(Direction.DESC, "createDate"));
		  return  photoRepository.findByUserid(user, pageRequest);
	}
	
	public Page<Photo> findAll(final int pageNumber, final int pageSize,final String state){
         PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Page<Photo> result;
         if(!StringUtils.isBlank(state)){
        	 result = (Page<Photo>) photoRepository.findByState(state, pageRequest);
         }else{
        	 result = (Page<Photo>) photoRepository.findAll( pageRequest);
         }
        
         return result;
     }
}
