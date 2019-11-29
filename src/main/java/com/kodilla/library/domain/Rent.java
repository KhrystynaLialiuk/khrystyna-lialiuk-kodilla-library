package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne(targetEntity = Copy.class)
    @JoinColumn(name = "COPY_ID")
    private Copy copy;

    @ManyToOne(targetEntity = Reader.class)
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @NotNull
    @Column(name = "DATE_OF_RENT")
    private LocalDate dateOfRent;

    @Column(name = "DATE_OF_RETURN")
    private LocalDate dateOfReturn;
}
