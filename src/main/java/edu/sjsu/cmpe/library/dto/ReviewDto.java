package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.Review;

public class ReviewDto extends LinksDto {

	private Review review;

	public ReviewDto(Review review) {
		super();
		this.review = review;
	}

	/**
	 * @return the review
	 */
	public Review getReview() {
		return review;
	}

	/**
	 * @param review
	 *            the review to set
	 */
	public void setReview(Review review) {
		this.review = review;
	}

}
