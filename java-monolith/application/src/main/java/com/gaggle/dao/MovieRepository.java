package com.gaggle.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaggle.domain.Movie;

@RepositoryRestResource
public interface MovieRepository extends CrudRepository<Movie, Long> {
	Movie findByTitle(String name);

	List<Movie> findByPeople(String name);
}
