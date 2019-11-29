package com.kodilla.library.domain;

import com.kodilla.library.repository.ReaderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderTestSuite {
    @Autowired
    private ReaderRepository readerRepository;

    private static final String NAME = "John";
    private static final String SURNAME = "Snith";

    @Test
    public void testSaveReader() {
        //Given
        Reader reader = new Reader();
        reader.setName(NAME);
        reader.setSurname(SURNAME);
        reader.setRegistrationDate(LocalDate.now());

        //When
        readerRepository.save(reader);

        //Then
        long readerId = reader.getId();
        Optional<Reader> savedReader = readerRepository.findById(readerId);
        Assert.assertTrue(savedReader.isPresent());

        //CleanUp
        readerRepository.deleteAll();
    }
}
