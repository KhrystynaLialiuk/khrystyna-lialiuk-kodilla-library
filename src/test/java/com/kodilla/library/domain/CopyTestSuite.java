package com.kodilla.library.domain;

import com.kodilla.library.repository.CopyRepository;
import com.kodilla.library.repository.TitleRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CopyTestSuite {

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private TitleRepository titleRepository;

    private static final String COPY_STATUS = "In circulation";
    private static final String TITLE_TITLE = "Alice's Adventures in Wonderland";
    private static final String TITLE_AUTHOR = "Lewis Carroll";
    private static final int TITLE_PUBLICATION_YEAR = 1865;

    private Title title;

    @Before
    public void prepare() {
        title = new Title();
        title.setTitle(TITLE_TITLE);
        title.setAuthor(TITLE_AUTHOR);
        title.setPublicationYear(TITLE_PUBLICATION_YEAR);
        titleRepository.save(title);
    }

    @Test
    public void testSaveCopy() {
        //Given
        Copy copy = new Copy();
        copy.setStatus(COPY_STATUS);
        copy.setTitle(title);

        //When
        copyRepository.save(copy);

        //Then
        long copyId = copy.getId();
        Optional<Copy> savedCopy = copyRepository.findById(copyId);
        Assert.assertTrue(savedCopy.isPresent());
    }

    @Test
    public void testFindAllCopies() {
        //Given
        Copy copy1 = new Copy();
        copy1.setStatus(COPY_STATUS);
        copy1.setTitle(title);

        Copy copy2 = new Copy();
        copy2.setStatus(COPY_STATUS);
        copy2.setTitle(title);

        //When
        copyRepository.save(copy1);
        copyRepository.save(copy2);

        //Then
        List<Copy> savedCopies = (List<Copy>) copyRepository.findAll();
        Assert.assertEquals(2, savedCopies.size());
    }

    @After
    public void cleanUp() {
        copyRepository.deleteAll();
        titleRepository.deleteAll();
    }
}
