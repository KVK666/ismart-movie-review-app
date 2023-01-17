package com.ismart.movieservice.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


	@Entity
	@Table(name="Review")
	public class Review {
		
		@Id
		@GeneratedValue
		private Integer id;
		private String review;
		private Integer rating;
		private String userName;
		@ManyToOne
		private Movie movie;
			
		public Review() {}

		public Review(String review, Integer rating) {
			this.review = review;
			this.rating = rating;
		}

		public Integer getId() {
			return id;
		}

		public String getReview() {
			return review;
		}

		public void setReview(String review) {
			this.review = review;
		}

		public Integer getRating() {
			return rating;
		}

		public void setRating(Integer rating) {
			this.rating = rating;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Movie getMovie() {
			return movie;
		}

		public void setMovie(Movie movie) {
			this.movie = movie;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Review other = (Review) obj;
			return Objects.equals(id, other.id);
		}	

	}
