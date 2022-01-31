package com.gaggle.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gaggle.controller.PersonController;
import com.gaggle.dao.PersonRepository;
import com.gaggle.domain.Person;

@SpringBootTest
class PersonControllerTest {

	@Autowired
	PersonController personController;

	@Autowired
	PersonRepository personRepository;

	private static final String ValidName = "Paul";

	@Test
	void testGetPerson() {
		Person p = new Person();
		p.setName(ValidName);
		Person person = personRepository.save(p);

		ResponseEntity<Person> resp = personController.getPerson(person.getId());
		assertEquals(HttpStatus.OK.value(), resp.getStatusCodeValue());
	}

	@Test
	void testCreatePerson() {
		Person p = new Person();
		p.setName(ValidName);
		ResponseEntity<Person> resp = personController.createPerson(p);
		assertEquals(HttpStatus.OK.value(), resp.getStatusCodeValue());

		Optional<Person> person = personRepository.findById(resp.getBody().getId());
		assertTrue(person.isPresent());
		assertTrue(p.getName().equals(person.get().getName()));
	}

}
