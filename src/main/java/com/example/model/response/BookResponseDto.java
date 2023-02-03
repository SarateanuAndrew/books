package com.example.model.response;

import com.example.model.enums.Category;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookResponseDto {
    private Long bookId;
    private String bookName;
    private String authorName;
    private int price;
    private Long libraryId;
    private Category category;
}