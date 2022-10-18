package com.kosta.dog.service;

import java.util.List;

import com.kosta.dog.bean.Dog;

public interface DogService {

	List<Dog> getAllList() throws Exception;

	Dog getDog(Integer id) throws Exception;

	void dogRegist(Dog dog) throws Exception;

}
