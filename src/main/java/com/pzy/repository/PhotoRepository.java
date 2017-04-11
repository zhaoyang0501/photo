package com.pzy.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.pzy.entity.Photo;
import com.pzy.entity.User;


public interface PhotoRepository extends MongoRepository<Photo, String> {
	
	 public List<Photo> findByUserid(String userid);
	 public List<Photo> findByUser(User user);
    public List<Photo> findByState(String state);
    public Page<Photo> findByState(String name,Pageable pageable);

}