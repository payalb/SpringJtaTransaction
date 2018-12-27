package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.dto.Person;
public interface PersonRepository extends CrudRepository<Person, Integer>{

}
