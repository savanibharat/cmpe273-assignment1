package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.library.domain.Review;

public class ReviewsDto extends LinksDto {

	private List<Review> reviews = new ArrayList<Review>();

	public ReviewsDto(List<Review> reviews) {
		super();
		this.reviews = reviews;
	}

	/**
	 * @return the review
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * @param review
	 *            the review to set
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

}
