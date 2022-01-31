package com.gaggle.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaggle.domain.Person;

@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Long> {
	Person findByName(String name);

	List<Person> findByNameContaining(String name);
}
