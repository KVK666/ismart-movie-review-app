package com.ismart.movieservice.repository;

import com.ismart.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
	
	public Movie findByTitle(String title);

}
