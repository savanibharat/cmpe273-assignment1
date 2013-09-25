package edu.sjsu.cmpe.library.domain;

import java.util.List;

public class Author {
	private int id;
	private String name;

	/**
	 * @param id
	 *            the id to set
	 */
	public Author() {
		// this.id=++id;
	}

	public void setId(int id) {

		this.id = id;// for setting id you need to increment a counter of id in
						// constructor
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
