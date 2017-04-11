package com.pzy.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.pzy.entity.User;


public interface UserRepository extends MongoRepository<User, String> {

	  public List<User> findByNameAndPassword(String userName,String password);
	  public List<User> findByName(String userName);
	  public Page<User> findByNameLike(String userName,Pageable pageable);


}