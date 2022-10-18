package com.kosta.dog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.kosta.dog.bean.Dog;

@Mapper
@Repository
public interface DogDAO {

	List<Dog> selectDogList() throws Exception;

	Dog selectDog(Integer id) throws Exception;

	void insertDog(Dog dog) throws Exception;
}
