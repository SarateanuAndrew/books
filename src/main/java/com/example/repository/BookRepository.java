package com.example.repository;

import com.example.model.dbo.BookDbo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepository implements PanacheRepository<BookDbo> {

}