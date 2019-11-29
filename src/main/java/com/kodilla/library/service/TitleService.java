package com.kodilla.library.service;

import com.kodilla.library.dto.TitleDto;
import com.kodilla.library.exception.TitleNotFoundException;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleService {

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private TitleMapper titleMapper;

    public TitleDto createTitle(final TitleDto titleDto) {
        return titleMapper.toTitleDto(titleRepository.save(titleMapper.toTitle(titleDto)));
    }

    public List<TitleDto> getAllTitles() {
        return titleMapper.toTitleDtoList(titleRepository.findAll());
    }

    public TitleDto getTitleById(long id) throws TitleNotFoundException {
        return titleMapper.toTitleDto(titleRepository.findById(id).orElseThrow(TitleNotFoundException::new));
    }
}
