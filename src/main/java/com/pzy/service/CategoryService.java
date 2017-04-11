
package com.pzy.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pzy.entity.Category;
import com.pzy.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		return (List<Category>) categoryRepository.findAll();
	}

	public List<Category> findCategorySubs() {
		return (List<Category>) categoryRepository.findAll();
	}

	public List<Category> findCategorys() {
		return (List<Category>) categoryRepository.findAll();
	}

	public Page<Category> findAll(final int pageNumber, final int pageSize, final String name) {
		 PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
		 Page<Category> result ;
		 if(!StringUtils.isBlank(name)){
			 result = (Page<Category>) categoryRepository.findByName(name, pageRequest);
         }else{
        	 result = (Page<Category>) categoryRepository.findAll( pageRequest);
         }
		
         return result;
	}

	public void delete(String id) {
		categoryRepository.delete(id);
	}

	public Category findCategory(String id) {
		return categoryRepository.findOne(id);
	}

	public Category find(String id) {
		return categoryRepository.findOne(id);
	}

	public void save(Category category) {
		categoryRepository.save(category);
	}
}