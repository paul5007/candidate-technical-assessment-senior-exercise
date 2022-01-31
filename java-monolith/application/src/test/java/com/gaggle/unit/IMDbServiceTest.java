package com.gaggle.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gaggle.domain.Person;
import com.gaggle.service.IMDbService;

@SpringBootTest
class IMDbServiceTest {

	@Autowired
	public IMDbService imdbService;

	private static final String ValidName = "Paul";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMovieCount() {
		Person p = new Person();
		p.setName(ValidName);
		List<String> movieList = new ArrayList<String>();
		movieList.add("Test1");
		movieList.add("Test2");
		movieList.add("Test3");
		p.setMovieTitles(movieList);
		int movieCount = imdbService.countMovies(p);
		assertEquals(3, movieCount);
	}

}
