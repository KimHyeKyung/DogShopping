package com.kosta.dog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.dog.bean.Dog;
import com.kosta.dog.dao.DogDAO;

@Service
public class DogServiceImpl implements DogService{

	@Autowired
	DogDAO dogDAO;
	
	@Override
	public List<Dog> getAllList() throws Exception{
		return dogDAO.selectDogList();
	}

	@Override
	public Dog getDog(Integer id) throws Exception {
		return dogDAO.selectDog(id);
	}

	@Override
	public void dogRegist(Dog dog) throws Exception {
		dogDAO.insertDog(dog);
	}

}
