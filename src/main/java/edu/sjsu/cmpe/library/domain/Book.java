package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
	@JsonProperty("isbn")
	private Long isbn;
	private String title;
	@JsonProperty("publication-date")
	private String publication_date;
	private String language;
	@JsonProperty("num-pages")
	private int num_pages;
	private String status = "available";
	public List<Author> authors = new ArrayList<Author>();
	public List<Review> reviews = new ArrayList<Review>();

	/**
	 * @return the reviews
	 */

	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * @param reviews
	 *            the reviews to set
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * @return the authors
	 */
	public List<Author> getauthors() {
		return authors;
	}

	/**
	 * @param authors
	 *            the authors to set
	 */
	public void setauthors(List<Author> authors) {
		this.authors = authors;
	}

	/**
	 * @return the id
	 */

	/**
	 * @return the num_pages
	 */
	public int getNum_pages() {
		return num_pages;
	}

	/**
	 * @param num_pages
	 *            the num_pages to set
	 */

	public void setNum_pages(int num_pages) {
		this.num_pages = num_pages;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the publicationDate
	 */
	public String getPublication_date() {
		return publication_date;
	}

	/**
	 * @param publicationDate
	 *            the publicationDate to set
	 */
	public void setPublicationDate(String publication_date) {
		this.publication_date = publication_date;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param launguage
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the isbn
	 */
	public Long getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn
	 *            the isbn to set
	 */
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public int hashCode() {

		// int hash = 3;
		// hash = 7 * hash + this.isbn.hashCode();
		// hash = 7 * hash + this.title.hashCode();
		return this.isbn.hashCode();
	}

	public boolean equals(Object object) {

		if (object != null && object instanceof Book) {
			Book bookObj = (Book) object;
			if (this.isbn == bookObj.getIsbn()) {
				return true;
			}
		}
		return false;
	}
}
