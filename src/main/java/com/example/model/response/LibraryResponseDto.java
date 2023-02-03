package com.example.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LibraryResponseDto {
    private Long libraryId;
    private String libraryName;
    private String libraryAddress;
}
