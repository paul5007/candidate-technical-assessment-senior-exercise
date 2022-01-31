package com.gaggle.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gaggle.dao.MovieRepository;
import com.gaggle.dao.PersonRepository;
import com.gaggle.domain.Movie;
import com.gaggle.domain.Person;

@Component
public class IMDbService {

	Logger LOGGER = LogManager.getLogger();

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private MovieRepository movieRepository;

	public List<Person> findPeople() {
		Iterable<Person> personList = personRepository.findAll();
		List<Person> people = StreamSupport.stream(personList.spliterator(), false).collect(Collectors.toList());
		if (null != people) {
			return people;
		} else {
			LOGGER.warn("Some relative message: No data in DB");
			return people;
		}
	}

	public Person findPersonById(Long id) {
		Optional<Person> person = personRepository.findById(id);
		if (person.isPresent()) {
			Person p = person.get();
			List<Movie> movieList = movieRepository.findByPeople(p.getName());
			if (null != movieList) {
				p.setMovieTitles(findMovieTitlesByPerson(p));
				return p;
			} else {
				LOGGER.warn("No Movie Titles exist for Person ID: " + id);
				return p;
			}
		} else {
			LOGGER.warn("Person ID does not exist: " + id);
			return null;
		}
	}

	public List<Person> findPeopleByName(String name) {
		List<Person> people = personRepository.findByNameContaining(name);
		if (null != people) {
			people.forEach(person -> {
				person.setMovieTitles(findMovieTitlesByPerson(person));
			});
		}
		return people;
	}

	public Person createPerson(Person p) {
		Person foundPerson = personRepository.findByName(p.getName());
		if (null == foundPerson) {
			personRepository.save(p);
			return p;
		} else {
			LOGGER.error("Person already exists: " + foundPerson.toString());
			return null;
		}
	}

	private List<String> findMovieTitlesByPerson(Person p) {
		List<Movie> movieList = movieRepository.findByPeople(p.getName());
		return movieList.stream().map(movie -> movie.getTitle()).collect(Collectors.toList());
	}

}
