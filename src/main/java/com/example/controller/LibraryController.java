package com.example.controller;

import com.example.model.dbo.LibraryDbo;
import com.example.model.request.LibraryRequestDto;
import com.example.model.response.LibraryResponseDto;
import com.example.repository.LibraryRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

@Path("/library")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LibraryController {
    @Inject
    LibraryRepository libraryRepository;

    @POST
    @Path("/save-library")
    @Transactional
    public Response saveLibrary(LibraryRequestDto libraryRequestDto) {
        libraryRepository.persist(LibraryDbo.builder()
                .libraryId(libraryRequestDto.getLibraryId())
                .libraryAddress(libraryRequestDto.getLibraryAddress())
                .libraryName(libraryRequestDto.getLibraryName())
                .build());
        return Response.accepted().build();
    }

    @PUT
    @Path("/update-library")
    @Transactional
    public Response updateLibrary(LibraryRequestDto libraryRequestDto) {
        LibraryDbo libraryDbo = libraryRepository.findByIdOptional(libraryRequestDto.getLibraryId()).orElseThrow();
        libraryDbo.setLibraryName(libraryRequestDto.getLibraryName());
        libraryDbo.setLibraryAddress(libraryRequestDto.getLibraryAddress());
        libraryRepository.persist(libraryDbo);
        return ok().build();
    }

    @GET
    @Path("/get-all-library")
    @Transactional
    public Response getAllLibrary() {
        return ok(libraryRepository.listAll().stream()
                        .map(libraryDbo -> LibraryResponseDto.builder()
                                .libraryId(libraryDbo.getLibraryId())
                                .libraryName(libraryDbo.getLibraryName())
                                .libraryAddress(libraryDbo.getLibraryAddress())
                                .build())
                        .toList())
                .build();
    }

    @GET
    @Path("/get-library-by-id/{id}")
    @Transactional
    public Response findLibraryById(@PathParam("id") Long id) {
        LibraryDbo libraryDbo = libraryRepository.findByIdOptional(id).orElseThrow();
        return ok(LibraryResponseDto.builder()
                        .libraryAddress(libraryDbo.getLibraryAddress())
                        .libraryName(libraryDbo.getLibraryName())
                        .libraryId(libraryDbo.getLibraryId())
                        .build())
                .build();
    }

    @DELETE
    @Path("/delete-library-by-id/")
    @Transactional
    public Response deleteLibraryById(@QueryParam("id") Long id) {
        return ok(libraryRepository.deleteById(id))
                .build();
    }
}