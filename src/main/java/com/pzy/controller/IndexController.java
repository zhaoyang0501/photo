package com.pzy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pzy.entity.Photo;
import com.pzy.repository.PhotoRepository;

@Controller
@RequestMapping("admin")
public class IndexController {
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@RequestMapping("test")
	public String test(Model model) {
		photoRepository.save(new Photo());
		return "";
	}
}
