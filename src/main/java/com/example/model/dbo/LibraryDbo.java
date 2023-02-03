package com.example.model.dbo;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "library")
public class LibraryDbo {
    @Id
    @GeneratedValue
    @Column(name = "library_id")
    private Long libraryId;
    @Column(name = "library_name")
    private String libraryName;
    @Column(name = "library_address")
    private String libraryAddress;
}
