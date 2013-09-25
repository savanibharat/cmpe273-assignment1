package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.library.domain.Author;

public class AuthorsDto extends LinksDto {

	private List<Author> authors = new ArrayList<Author>();

	public AuthorsDto(List<Author> authors) {
		super();
		this.authors = authors;
	}

	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}

	/**
	 * @param auth
	 *            the auth to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

}
