package com.ismart.movieservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ismart.movieservice.entity.Movie;
import com.ismart.movieservice.entity.Review;
import com.ismart.movieservice.exception.MovieNotFoundException;
import com.ismart.movieservice.repository.MovieRepository;
import com.ismart.movieservice.repository.ReviewRepository;
import com.ismart.movieservice.service.MovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MovieReviewController {
	@Autowired
	private MovieReviewService service;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	//To get all movies
	@GetMapping("/movies")
	public List<Movie> getAllMovies(){
		return service.getAllMovies();
	}
	
	//Get a movie by Id
	@GetMapping("/movies/{id}")
	public Movie getMovieById(@PathVariable Integer id) {
		return service.getMovieDetailsById(id);
	}
	
	//To add a new movie
	@PostMapping("/movies/addMovie")
	public Movie addMovie(@RequestBody Movie movie) {
		return service.addMovie(movie);
	}

	@PutMapping("/movies/updateMovie/{id}")
	public Movie updateMovie(@PathVariable Integer id, @RequestBody Movie movie){ return service.updateMovie(movie);}

	//To delete a movie
	@DeleteMapping("/movies/removeMovie/{id}")
	public String removeMovie(@PathVariable Integer id) {
		return service.deleteMovie(id);
	}
	
	//TO Get a review By Id
	@GetMapping("/reviews/{id}")
	public Review getMovieReviewById(@PathVariable Integer id){
		return service.getReviewById(id);
	}

	//Get all reviews of a movie
	@GetMapping("/movies/{id}/reviews")
	public List<Review> getAllMovieReviewsByMovieId(@PathVariable Integer id){
		return service.getAllReviewsByMovieId(id);
	}
	
	//To Add a movie review
	@PostMapping("/movies/{id}/users/{userName}")
	public Review addMovieReview(@RequestBody Review review,
			@PathVariable Integer id,@PathVariable String userName) {
		return service.addReview(review,id,userName);
	}


	
	//To delete a review
	@DeleteMapping("/reviews/deleteReview/{id}")
	public String deleteReview(@PathVariable Integer id) {
		return service.deleteReview(id);
	}
	
	//To update a review
	@PutMapping("/reviews/updateReview")
	public Review updateMovieReview(@RequestBody Review review) {
		return service.updateReview(review);
	}
	
	//To get All the reviews written by a user
	@GetMapping("/users/reviews/{userName}")
	public List<Review> getAllMovieReviewsByUserName(@PathVariable String userName){
		return service.getAllReviewsByUserName(userName);				
	}

	@GetMapping("/reviews")
	public List<Review> getAllReviews(){
		return service.getAllReviews();
	}





}
