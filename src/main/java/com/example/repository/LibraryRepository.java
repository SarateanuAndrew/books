package com.example.repository;

import com.example.model.dbo.LibraryDbo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LibraryRepository implements PanacheRepository<LibraryDbo> {

}