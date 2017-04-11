package com.pzy.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.pzy.entity.AdminUser;

public interface AdminUserRepository extends MongoRepository<AdminUser, String> {

	 public List<AdminUser> findByNameAndPassword(String userName,String password);
	 public Page<AdminUser> findByNameLike(String name,Pageable pageable);

}