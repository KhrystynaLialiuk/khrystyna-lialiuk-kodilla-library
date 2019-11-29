package com.kodilla.library.domain;

import com.kodilla.library.repository.CopyRepository;
import com.kodilla.library.repository.ReaderRepository;
import com.kodilla.library.repository.RentRepository;
import com.kodilla.library.repository.TitleRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentTestSuite {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private RentRepository rentRepository;

    private static final String COPY_STATUS = "In circulation";
    private static final String TITLE_TITLE = "Alice's Adventures in Wonderland";
    private static final String TITLE_AUTHOR = "Lewis Carroll";
    private static final int TITLE_PUBLICATION_YEAR = 1865;
    private static final String READER_NAME = "John";
    private static final String READER_SURNAME = "Snith";

    private Title title;
    private Copy copy;
    private Reader reader;

    @Before
    public void prepare() {
        title = new Title();
        title.setTitle(TITLE_TITLE);
        title.setAuthor(TITLE_AUTHOR);
        title.setPublicationYear(TITLE_PUBLICATION_YEAR);
        titleRepository.save(title);

        copy = new Copy();
        copy.setStatus(COPY_STATUS);
        copy.setTitle(title);
        copyRepository.save(copy);

        reader = new Reader();
        reader.setName(READER_NAME);
        reader.setSurname(READER_SURNAME);
        reader.setRegistrationDate(LocalDate.now());
        readerRepository.save(reader);
    }

    @Test
    public void testSaveRent() {
        //Given
        Rent rent = new Rent();
        rent.setCopy(copy);
        rent.setReader(reader);
        rent.setDateOfRent(LocalDate.now());
        rent.setDateOfReturn(LocalDate.now());

        //When
        rentRepository.save(rent);

        //Then
        long rentId = rent.getId();
        Optional<Rent> savedRent = rentRepository.findById(rentId);
        Assert.assertTrue(savedRent.isPresent());
    }

    @After
    public void cleanUp() {
        rentRepository.deleteAll();
        readerRepository.deleteAll();
        copyRepository.deleteAll();
        titleRepository.deleteAll();
    }
}
