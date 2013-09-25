package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.Author;

public class AuthorDto extends LinksDto {
	private Author authors;

	public AuthorDto(Author authors) {
		super();
		this.authors = authors;
	}

	/**
	 * @return the authors
	 */
	public Author getAuthors() {
		return authors;
	}

	/**
	 * @param auth
	 *            the auth to set
	 */
	public void setAuthors(Author authors) {
		this.authors = authors;
	}

}
