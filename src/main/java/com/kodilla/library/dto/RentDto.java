package com.kodilla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentDto {
    private long id;
    private long copyId;
    private long readerId;
    private LocalDate dateOfRent;
    private LocalDate dateOfReturn;
}
