package edu.sjsu.cmpe.library.domain;

public class Review {

	private int id;
	private int rating;
	private String comment;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param idr
	 *            the id to set
	 */
	public void setId(int id) {

		this.id = id;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;

	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
