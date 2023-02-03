package com.example.model.dbo;

import com.example.model.enums.Category;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "book")
public class BookDbo {
    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author_name")
    private String authorName;
    private int price;
    @ManyToOne(fetch = FetchType.EAGER,  cascade = {CascadeType.MERGE})
    @JoinColumn(name = "library_id")
    private LibraryDbo libraryDbo;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
}
