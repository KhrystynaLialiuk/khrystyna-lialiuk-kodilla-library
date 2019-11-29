package com.kodilla.library.service;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.domain.Title;
import com.kodilla.library.dto.CopyDto;
import com.kodilla.library.exception.CopyNotFoundException;
import com.kodilla.library.exception.TitleNotFoundException;
import com.kodilla.library.mapper.CopyMapper;
import com.kodilla.library.repository.CopyRepository;
import com.kodilla.library.repository.RentRepository;
import com.kodilla.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CopyService {

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private CopyMapper copyMapper;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private RentRepository rentRepository;

    private static final String STATUS = "In circulation";

    public CopyDto createCopy(final CopyDto copyDto) {
        return copyMapper.toCopyDto(copyRepository.save(copyMapper.toCopy(copyDto)));
    }

    public List<CopyDto> getAllCopies() {
        return copyMapper.toCopyDtoList(copyRepository.findAll());
    }

    public CopyDto getCopyById(long id) throws CopyNotFoundException {
        return copyMapper.toCopyDto(copyRepository.findById(id).orElseThrow(CopyNotFoundException::new));
    }

    public CopyDto updateCopy(final CopyDto copyDto) throws CopyNotFoundException {
        if (copyRepository.findById(copyDto.getId()).isPresent()) {
            return copyMapper.toCopyDto(copyRepository.save(copyMapper.toCopy(copyDto)));
        } else {
            throw new CopyNotFoundException();
        }
    }

    public List<CopyDto> getAvailableCopies(String title) throws TitleNotFoundException {
        Title foundTitle = titleRepository.findByTitle(title).orElseThrow(TitleNotFoundException::new);
        List<Copy> copiesInCirculation = copyRepository.findByTitleAndStatus(foundTitle, STATUS);
        System.out.println(copiesInCirculation.size());
        List<Copy> availableCopies = new ArrayList<>();
        for (Copy copy : copiesInCirculation) {
            if (!rentRepository.findByCopy(copy).isPresent() || (rentRepository.findByCopy(copy).isPresent() &
                    rentRepository.findByCopy(copy).get().getDateOfReturn() != null)) {
                availableCopies.add(copy);
            }
        }
        return copyMapper.toCopyDtoList(availableCopies);
    }
}
