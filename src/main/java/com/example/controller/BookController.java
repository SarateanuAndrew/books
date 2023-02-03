package com.example.controller;

import com.example.model.dbo.BookDbo;
import com.example.model.request.BookRequestDto;
import com.example.model.response.BookResponseDto;
import com.example.repository.BookRepository;
import com.example.repository.LibraryRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.accepted;
import static javax.ws.rs.core.Response.ok;

@Path("/book")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookController {
    @Inject
    BookRepository bookRepository;

    @Inject
    LibraryRepository libraryRepository;

    @GET
    @Path("/get-all-book")
    public Response getAllBook() {
        return ok(bookRepository.listAll().stream()
                        .map(bookDbo -> BookResponseDto.builder()
                                .bookId(bookDbo.getBookId())
                                .libraryId(bookDbo.getLibraryDbo().getLibraryId())
                                .authorName(bookDbo.getAuthorName())
                                .category(bookDbo.getCategory())
                                .bookName(bookDbo.getBookName())
                                .price(bookDbo.getPrice())
                                .build())
                        .toList())
                .build();
    }

    @PUT
    @Path("/update-book")
    @Transactional
    public Response updateBook(BookRequestDto bookRequestDto) {
        BookDbo bookDbo = bookRepository.findByIdOptional(bookRequestDto.getBookId()).orElseThrow();
        bookDbo.setBookId(bookRequestDto.getBookId());
        bookDbo.setBookName(bookRequestDto.getBookName());
        bookDbo.setLibraryDbo(libraryRepository.findByIdOptional(bookRequestDto.getLibraryId()).orElseThrow());
        bookDbo.setAuthorName(bookRequestDto.getAuthorName());
        bookDbo.setCategory(bookRequestDto.getCategory());
        bookDbo.setPrice(bookRequestDto.getPrice());
        bookRepository.persist(bookDbo);
        return ok().build();
    }

    @POST
    @Path("/save-book")
    @Transactional
    public Response saveBook(BookRequestDto bookRequestDto) {
        bookRepository.persist(BookDbo.builder()
                .bookName(bookRequestDto.getBookName())
                .authorName(bookRequestDto.getAuthorName())
                .price(bookRequestDto.getPrice())
                .libraryDbo(libraryRepository.findByIdOptional(bookRequestDto.getLibraryId()).orElseThrow())
                .category(bookRequestDto.getCategory())
                .build());
        return accepted().build();
    }

    @GET
    @Path("/get-book-by-id/{id}")
    @Transactional
    public Response getBookById(@PathParam("id") Long id) {
        BookDbo bookDbo = bookRepository.findByIdOptional(id).orElseThrow();
        return ok(BookResponseDto.builder()
                        .bookId(bookDbo.getBookId())
                        .libraryId(bookDbo.getLibraryDbo().getLibraryId())
                        .authorName(bookDbo.getAuthorName())
                        .category(bookDbo.getCategory())
                        .bookName(bookDbo.getBookName())
                        .price(bookDbo.getPrice())
                        .build())
                .build();
    }

    @DELETE
    @Path("/delete-book-by-id/")
    @Transactional
    public Response deleteLibraryById(@QueryParam("id") Long id) {
        return ok(bookRepository.deleteById(id))
                .build();

    }
}
