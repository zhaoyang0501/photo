package com.pzy.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.pzy.entity.Category;
public interface CategoryRepository extends MongoRepository<Category, String> {
	 public Page<Category> findByName(String name,Pageable pageable);

}