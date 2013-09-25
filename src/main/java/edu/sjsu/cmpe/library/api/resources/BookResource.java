package edu.sjsu.cmpe.library.api.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.common.base.Optional;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
	public static Map<Long, Book> hashMapBooks = new HashMap<Long, Book>();
	public List<Author> bookAuthors;
	public static long randomisbn;
	String location = "/books/";
	public int authid;
	public static int reviewid;

	public BookResource() {
		//
	}

	@GET
	@Path("/{isbn}")
	@Timed(name = "view-book")
	public Response getBookByIsbn(@PathParam("isbn") LongParam isbn) {

		Book getBook = new Book();

		if (hashMapBooks.containsKey(isbn.get())) {
			getBook = hashMapBooks.get(isbn.get());

			BookDto bookdto = new BookDto(getBook);

			bookdto.addLink(new LinkDto("view-author", location
					+ getBook.getIsbn() + "/authors/"
					+ getBook.getauthors().get(0).getId(), "GET"));
			bookdto.addLink(new LinkDto("view-author", location
					+ getBook.getIsbn() + "/authors/"
					+ getBook.getauthors().get(1).getId(), "GET"));

			bookdto.addLink(new LinkDto("view-book", location
					+ getBook.getIsbn(), "GET"));
			bookdto.addLink(new LinkDto("update-book", location
					+ getBook.getIsbn(), "PUT"));
			bookdto.addLink(new LinkDto("delete-book", location
					+ getBook.getIsbn(), "DELETE"));
			bookdto.addLink(new LinkDto("create-review", location
					+ getBook.getIsbn(), "POST"));
			if (getBook.getReviews().size() > 0) {
				bookdto.addLink(new LinkDto("view-all-reviews", location
						+ getBook.getIsbn(), "GET"));
			}
			// build is used to convert oour return type from ResponseBuilder to
			// Response
			// so we can match our return type.
			return Response.status(200).entity(bookdto).build();

		} else {
			// err. Details from Documentation
			// The request could not be understood by the server due to
			// malformed syntax.
			// The client SHOULD NOT repeat the request without modifications.

			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("Malformed Syntax:: The request entered by you is invalid. Please try again.")
					.build();
		}

	}

	// 4
	@DELETE
	@Path("/{isbn}")
	@Timed(name = "delete-book")
	public Response deleteBook(@PathParam("isbn") LongParam isbn) {

		if (hashMapBooks.containsKey(isbn.get())) {

			hashMapBooks.remove(isbn.get());

			LinksDto links = new LinksDto();
			links.addLink(new LinkDto("create-book", "/books", "POST"));

			return Response.status(200).entity(links).build();
		} else {
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("Malformed Syntax:: The request entered by you is invalid. Please try again.")
					.build();
		}

	}

	/**
	 * Create book method
	 */
	// POST -> Client sends JSON data which is mapped to Domain Object
	// GET -> Server sends Domain Object which is mapped to JSON data
	@POST
	@Timed(name = "create-book")
	public Response createBook(Book request)/* @RequestBody Book b */
	{
		randomisbn++;
		request.setIsbn(randomisbn);

		bookAuthors = new ArrayList<Author>();
		if (randomisbn > 0) {
			for (int i = 0; i < request.getauthors().size(); i++) {
				++authid;
				Author author = new Author();
				author.setId(authid);
				author.setName(request.getauthors().get(i).getName());
				bookAuthors.add(author);
				author = null;
			}
			authid = 0;
			// Make authid =0 as when new book is inserted then the
			// authors will have id from 1
			reviewid = 0;

			request.setauthors(bookAuthors);
			hashMapBooks.put(randomisbn, request);

			LinksDto links = new LinksDto();
			links.addLink(new LinkDto("view-book",
					location + request.getIsbn(), "GET"));
			links.addLink(new LinkDto("update-book", location
					+ request.getIsbn(), "PUT"));
			links.addLink(new LinkDto("delete-book", location
					+ request.getIsbn(), "DELETE"));
			links.addLink(new LinkDto("create-review", location
					+ request.getIsbn() + "/reviews", "POST"));
			return Response.status(200).entity(links).build();
		}
		return Response
				.status(Response.Status.BAD_REQUEST)
				.entity("Malformed Syntax:: The request entered by you is invalid. Please try again.")
				.build();
	}

	@PUT
	@Path("/{isbn}")
	@Timed(name = "update-book")
	public Response updateStatus(@QueryParam("status") Optional<String> status,
			@PathParam("isbn") LongParam isbn) {

		if (hashMapBooks.containsKey(isbn.get())) {
			System.out.println(status.get());
			if (status.get().equals("Available") || status.get().equals("Lost")
					|| status.get().equals("Checkedout")
					|| status.get().equals("Inqueue")
					|| status.get().equals("available")
					|| status.get().equals("lost")
					|| status.get().equals("checkedout")
					|| status.get().equals("inqueue")) {
				//System.out.println(status.get());
				Book book = hashMapBooks.get(isbn.get());
				book.setStatus(status.get());
				hashMapBooks.put(isbn.get(), book);
				LinksDto links = new LinksDto();
				links.addLink(new LinkDto("view-book", location
						+ book.getIsbn(), "GET"));
				links.addLink(new LinkDto("update-book", location
						+ book.getIsbn(), "PUT"));
				links.addLink(new LinkDto("delete-book", location
						+ book.getIsbn(), "DELETE"));
				links.addLink(new LinkDto("create-review", location
						+ book.getIsbn() + "/reviews", "POST"));
				if (book.getReviews().size() > 0) {
					links.addLink(new LinkDto("view-all-reviews", location
							+ book.getIsbn(), "GET"));
				}
				return Response.status(200).entity(links).build();
			} else {
				return Response
						.status(Status.BAD_REQUEST)
						.entity("Status accepted are Available,Lost,Checkedout,Inqueue,available,lost,checkedout,inqueue")
						.build();
			}
		} else {
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("Malformed Syntax:: The request entered by you is invalid. Please try again.")
					.build();
		}

	}

	@POST
	@Path("/{isbn}/reviews")
	@Timed(name = "create-review")
	public Response createReview(@PathParam("isbn") LongParam isbn,
			Review review) {
		System.out.println(review.getRating());
		System.out.println(isbn.get());
		if (hashMapBooks.containsKey(isbn.get())) {

			Review newReview = new Review();

			reviewid = reviewid + 1;

			newReview.setId(reviewid);
			newReview.setComment(review.getComment());
			newReview.setRating(review.getRating());

			Book book = hashMapBooks.get(isbn.get());
			List<Review> reviews = book.getReviews();

			reviews.add(newReview);
			book.setReviews(reviews);

			hashMapBooks.put(isbn.get(), book);

			LinksDto links = new LinksDto();
			links.addLink(new LinkDto("view-review", "/book/" + book.getIsbn()
					+ "/review/" + reviewid, "GET"));

			return Response.ok(links).build();
		} else {
			return Response
					.status(Status.BAD_REQUEST)
					.entity("Malformed Syntax:: The request entered by you is invalid. Please try again.")
					.build();
		}

	}

	@GET
	@Path("/{isbn}/reviews/{id}")
	@Timed(name = "view-review")
	public Response viewbookreview(@PathParam("isbn") LongParam isbn,
			@PathParam("id") LongParam id) {

		if (hashMapBooks.containsKey(isbn.get())) {

			Book book = hashMapBooks.get(isbn.get());

			List<Review> getReviews = book.getReviews();

			Review resultReview = new Review();
			// getReview is a list from which we extract one review as per
			// requested in parameter ("idr")
			for (Review r : getReviews) {

				if (r.getId() == id.get()) {
					resultReview = r;
				}
			}

			if (resultReview.getId() == 0) {
				// error saying review with that id doesn't exist
				return Response.status(Status.BAD_REQUEST)
						.entity("Review with mentioned does not exists.")
						.build();

			} else {
				ReviewDto reviewDto = new ReviewDto(resultReview);
				String location = "/books/" + book.getIsbn() + "/reviews/"
						+ resultReview.getId();
				reviewDto.addLink(new LinkDto("view-review", location, "GET"));
				return Response.status(200).entity(reviewDto).build();
			}
		} else {
			// err saying book with that id doesn't exist.
			return Response
					.status(Status.BAD_REQUEST)
					.entity("Malformed Syntax:: The request entered by you is invalid. Please try again.")
					.build();
		}

	}

	@GET
	@Path("/{isbn}/authors/{id}")
	@Timed(name = "view-author")
	public Response viewAuthor(@PathParam("isbn") LongParam isbn,
			@PathParam("id") LongParam id) {
		if (hashMapBooks.containsKey(isbn.get())) {
			Book getBookAuthor = hashMapBooks.get(isbn.get());
			List<Author> bookAuthor = getBookAuthor.getauthors();

			Author author = new Author();
			for (Author a : bookAuthor) {
				if (a.getId() == id.get()) {
					author = a;
				}
			}

			if (author.getId() == 0) {
				// error saying author with that id doesn't exist
				return Response.status(Status.BAD_REQUEST)
						.entity("Author with mentioned ID does not exists")
						.build();
			} else {
				AuthorDto authorDto = new AuthorDto(author);
				String location = "/books/" + isbn.get() + "/reviews/"
						+ author.getId();
				authorDto.addLink(new LinkDto("view-review", location, "GET"));
				return Response.status(200).entity(authorDto).build();
			}

		}
		return Response
				.status(Status.BAD_REQUEST)
				.entity("Malformed Syntax:: The request entered by you is invalid. Please try again.")
				.build();

	}

	@GET
	@Path("/{isbn}/reviews")
	@Timed(name = "view-all-reviews")
	public Response viewallreviews(@PathParam("isbn") LongParam isbn) {
		if (hashMapBooks.containsKey(isbn.get())) {
			Book book = hashMapBooks.get(isbn.get());
			List<Review> newreviewlist = book.getReviews();

			List<Review> reviews = new ArrayList<Review>();

			for (Review r : newreviewlist) {
				reviews.add(r);
			}

			ReviewsDto reviewsDto = new ReviewsDto(reviews);
			return Response.status(200).entity(reviewsDto).build();

		} else {
			// err saying book with that id doesn't exist.
			return Response
					.status(Status.BAD_REQUEST)
					.entity("Malformed Syntax:: The request entered by you is invalid. Please try again.")
					.build();
		}
	}

	@GET
	@Path("/{isbn}/authors")
	@Timed(name = "view-author")
	public Response viewAllAuthors(@PathParam("isbn") LongParam isbn) {
		if (hashMapBooks.containsKey(isbn.get())) {
			Book book = hashMapBooks.get(isbn.get());
			List<Author> authorList = book.getauthors();

			AuthorsDto authorssDto = new AuthorsDto(authorList);
			return Response.status(200).entity(authorssDto).build();

		} else {
			// error book not there
			return Response
					.status(Status.BAD_REQUEST)
					.entity("Malformed Syntax:: The request entered by you is invalid. Please try again.")
					.build();
		}
	}
}
