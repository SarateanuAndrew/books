package com.example.model.request;

import lombok.*;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LibraryRequestDto {
    private Long libraryId;
    private String libraryName;
    private String libraryAddress;
}
