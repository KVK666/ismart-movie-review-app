package com.ismart.movieservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ismart.movieservice.entity.Movie;
import com.ismart.movieservice.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismart.movieservice.exception.MovieNotFoundException;
import com.ismart.movieservice.exception.ReviewNotFoundException;
import com.ismart.movieservice.repository.MovieRepository;
import com.ismart.movieservice.repository.ReviewRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class MovieReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private MovieRepository movieRepository;

	// fetch all the movies
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	public List<Review> getAllReviews(){
		return reviewRepository.findAll();
	}


	//fetch a movie by Id
	public Movie getMovieDetailsById(Integer id) {

		Optional<Movie> optional = movieRepository.findById(id);
		if (optional.isEmpty())
			throw new MovieNotFoundException("No movie found with the id " + id);

		Movie exsistingMovie = optional.get();
		return exsistingMovie;
	}

	// fetch a review details by it's Id
	public Review getReviewById(Integer id) {

		Optional<Review> optional = reviewRepository.findById(id);
		if (!optional.isPresent())
			throw new ReviewNotFoundException("No Review Found with id " + id);

		Review review = optional.get();
		return review;
	}

	// fetch movie details by movie title
	public Movie getMovieByTitle(String title) {

		Movie movie = movieRepository.findByTitle(title);
		if (movie.equals(null))
			throw new MovieNotFoundException("No movie found with the name of " + title);
		return movie;
	}

	// to fetch all reviews of a particular movie
	public List<Review> getAllReviewsByMovieId(Integer id) {

		List<Review> reviews = reviewRepository.findAll().stream()
				.filter(review -> review.getMovie().getId()==id).collect(Collectors.toList());
		if (reviews.isEmpty())
			throw new ReviewNotFoundException("No reviews exist for the movie with id " + id);
		return reviews;
	}

	// to fetch all reviews of a single user
	public List<Review> getAllReviewsByUserName(String userName) {

		List<Review> reviews = reviewRepository.findAll().stream()
				.filter(review -> review.getUserName().equalsIgnoreCase(userName)).collect(Collectors.toList());
		if (reviews.isEmpty())
			throw new ReviewNotFoundException("No reviews exist for the user " + userName);
		return reviews;
	}

	// To add a new review
	public Review addReview(Review review, Integer id, String userName) {

		review.setMovie(getMovieDetailsById(id));
		review.setUserName(userName);
		return reviewRepository.save(review);
	}

	// to add a movie
	public Movie addMovie(Movie movie) {
		
		return movieRepository.save(movie);
	}

	// To delete a review
	public String deleteReview(Integer id) {

		Optional<Review> optional = reviewRepository.findById(id);
		if (!optional.isPresent())
			throw new ReviewNotFoundException("No Review Found to Delete");
		Review review = optional.get();
		reviewRepository.delete(review);
		return id + " is deleted";
	}

	// to delete a movie
	public String deleteMovie(Integer id) {
		Optional<Movie> optional = movieRepository.findById(id);
		if (!optional.isPresent())
			throw new MovieNotFoundException("No Movie Found to Delete");
		Movie movie = optional.get();
		movieRepository.delete(movie);
		return id + " is deleted";
	}

	// To update a existing review
	public Review updateReview(Review review) {
		Optional<Review> optional = reviewRepository.findById(review.getId());
		if (!optional.isPresent())
			throw new ReviewNotFoundException("No Review Found to update");
		Review existingReview = optional.get();
		existingReview.setReview(review.getReview());
		existingReview.setRating(review.getRating());
		reviewRepository.save(existingReview);
		return existingReview;
	}

	// To update a existing movie
	public Movie updateMovie(Movie movie) {
		Movie existingMovie = movieRepository.findById(movie.getId()).orElse(null);
		if (existingMovie == null)
			throw new MovieNotFoundException("No such movie exist");
		existingMovie.setTitle(movie.getTitle());

		existingMovie.setYear(movie.getYear());
		existingMovie.setOverAllRating(movie.getOverAllRating());
		existingMovie.setDescription(movie.getDescription());
		existingMovie.setDirector(movie.getDirector());
		existingMovie.setLanguage(movie.getLanguage());
		return movieRepository.save(existingMovie);
	}

//	public List<Review> getReviews(Integer id) {
//		List<Review> reviews = movieRepository.findAll().stream()
//				.filter(movie -> movie.getId() == id)
//				.flatMap(movie -> Arrays.stream(movie.getReviews()))
//				.collect(Collectors.toList());
//		if (reviews.isEmpty()) {
//			throw new MovieNotFoundException();
//		}
//		return reviews;
//	}

//	public List<Review> getReviews(Integer id){
//		Movie movie = movieRepository.findById(d).orElseThrow(MovieNotFoundException::new);
//		return movie.getReviews();
//
//	}

}
