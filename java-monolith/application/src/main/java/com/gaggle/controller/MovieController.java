package com.gaggle.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
import com.gaggle.dao.MovieRepository;
import com.gaggle.domain.Movie;

@RestController()
public class MovieController {

	@Autowired
	private MovieRepository repository;

	@GetMapping(path = RestEndpoints.MOVIE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Movie>> getMovies() {
		Iterable<Movie> movieList = repository.findAll();
		List<Movie> movie = StreamSupport.stream(movieList.spliterator(), false).collect(Collectors.toList());
		return new ResponseEntity<List<Movie>>(movie, HttpStatus.OK);
	}

	@GetMapping(path = RestEndpoints.MOVIE
			+ "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Movie> getMovie(@PathVariable(name = "id") Long id) {
		Optional<Movie> movie = repository.findById(id);
		if (movie.isPresent()) {
			return new ResponseEntity<Movie>(movie.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(path = RestEndpoints.MOVIE
			+ "/person/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Movie>> getMovieByPeople(@PathVariable(name = "name") String name) {
		List<Movie> movieList = repository.findByPeople(name);
		if (null != movieList) {
			return new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(path = RestEndpoints.MOVIE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie m) {
		Movie movie = repository.save(m);
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}

	/* DEMO PURPOSES */
	@PostMapping(path = RestEndpoints.MOVIE
			+ "/mass", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Movie> createMovies(@Valid @RequestBody List<Movie> movieList) {
		movieList.forEach(movie -> {
			repository.save(movie);
		});
		return new ResponseEntity<Movie>(HttpStatus.OK);
	}

}
