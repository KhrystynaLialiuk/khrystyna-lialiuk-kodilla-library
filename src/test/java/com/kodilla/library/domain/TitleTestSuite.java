package com.kodilla.library.domain;

import com.kodilla.library.repository.TitleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TitleTestSuite {

    @Autowired
    private TitleRepository titleRepository;

    private static final String TITLE_TITLE = "Alice's Adventures in Wonderland";
    private static final String TITLE_AUTHOR = "Lewis Carroll";
    private static final int TITLE_PUBLICATION_YEAR = 1865;

    @Test
    public void testSaveTitle() {
        //Given
        Title title = new Title();
        title.setTitle(TITLE_TITLE);
        title.setAuthor(TITLE_AUTHOR);
        title.setPublicationYear(TITLE_PUBLICATION_YEAR);

        //When
        titleRepository.save(title);

        //Then
        long titleId = title.getId();
        Optional<Title> savedTitle = titleRepository.findById(titleId);
        Assert.assertTrue(savedTitle.isPresent());
        Assert.assertNotEquals(0, titleId);

        //CleanUp
        titleRepository.deleteAll();
    }
}
