package com.kodilla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TitleDto {
    private long id;
    private String title;
    private String author;
    private int publicationYear;
}
