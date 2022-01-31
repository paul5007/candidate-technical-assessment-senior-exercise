package com.gaggle.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaggle.constants.RestEndpoints;
import com.gaggle.domain.Person;
import com.gaggle.service.IMDbService;

@RestController()
public class PersonController {

	@Autowired
	private IMDbService personService;

	@GetMapping(path = RestEndpoints.PERSON, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Person>> getPersons() {
		List<Person> people = personService.findPeople();
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);
	}

	@GetMapping(path = RestEndpoints.PERSON
			+ "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getPerson(@PathVariable(name = "id") Long id) {
		Person p = personService.findPersonById(id);
		if (null != p) {
			return new ResponseEntity<Person>(p, HttpStatus.OK);
		} else {
			return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(path = RestEndpoints.PERSON
			+ "/name/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Person>> getPeopleByName(@PathVariable(name = "name") String name) {
		List<Person> people = personService.findPeopleByName(name);
		if (null != people) {
			return new ResponseEntity<List<Person>>(people, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(path = RestEndpoints.PERSON)
	public ResponseEntity<Person> createPerson(@Valid @RequestBody Person p) {
		Person person = personService.createPerson(p);
		if (null != person) {
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} else {
			return new ResponseEntity<Person>(person, HttpStatus.CONFLICT);
		}
	}

	/* DEMO PURPOSES */
	@PostMapping(path = RestEndpoints.PERSON + "/mass")
	public ResponseEntity<Person> createPeople(@Valid @RequestBody List<Person> people) {
		people.forEach(person -> {
			personService.createPerson(person);
		});
		return new ResponseEntity<Person>(HttpStatus.OK);
	}

}
