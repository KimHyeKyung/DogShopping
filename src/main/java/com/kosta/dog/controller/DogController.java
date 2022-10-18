package com.kosta.dog.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.dog.bean.Dog;
import com.kosta.dog.service.DogService;

import oracle.jdbc.proxy.annotation.Post;

@Controller
public class DogController {

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	DogService dogService;
	
	@GetMapping("/")
	public String main(Model model) {
		try {
			List<Dog> dogList = dogService.getAllList();
			model.addAttribute("dogList", dogList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"/dogList";
	}
	
	@GetMapping("/dogList")
	public String dogList() {
		return"redirect:/";
	}
	
	@GetMapping("/dogView")
	public String dogView(Model model, @RequestParam("id") Integer id) {
		try {
			Dog dog = new Dog();
			dog = dogService.getDog(id);
			model.addAttribute("dog", dog);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return"/dogView";
	}
	
	@GetMapping("/dogRegistForm")
	public String dogRegistForm() {
		return"/dogRegistForm";
	}
	
	@PostMapping("/dogRegist")
	public ModelAndView dogRegist(@ModelAttribute Dog dog, Model model) {
		ModelAndView mav = new ModelAndView();
		try {
			MultipartFile file = dog.getImageFile(); //파일 자체를 가져옴
			if(!file.isEmpty()) {
				String path = servletContext.getRealPath("/images/");
				File destFile = new File(path + file.getOriginalFilename());//file을 destFile로 옮겨라.
				file.transferTo(destFile);
				dog.setImage(file.getOriginalFilename());//파일의 이름을 넣어주기위해 따로 설정
				mav.setViewName("redirect:/");
			}else {
				
			}
			dogService.dogRegist(dog);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
		
	}
}
