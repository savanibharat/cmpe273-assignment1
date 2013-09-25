package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinksDto {
	private List<LinkDto> links = new ArrayList<LinkDto>();

	public void addLink(LinkDto link) {
		links.add(link);
	}

	/**
	 * @return the links
	 */
	@JsonProperty("links")
	public List<LinkDto> getLinks() {
		return links;
	}

	/**
	 * @param links
	 *            the links to set
	 */
	@JsonProperty("links")
	public void setLinks(List<LinkDto> links) {
		this.links = links;
	}

}
