package com.kodilla.library.service;

import com.kodilla.library.dto.ReaderDto;
import com.kodilla.library.exception.ReaderNotFoundException;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private ReaderMapper readerMapper;

    public ReaderDto createReader(final ReaderDto readerDto){
        return readerMapper.toReaderDto(readerRepository.save(readerMapper.toReader(readerDto)));
    }

    public List<ReaderDto> findAllReaders() {
        return readerMapper.toReaderDtoList(readerRepository.findAll());
    }

    public ReaderDto findReaderById(long id) throws ReaderNotFoundException {
        return readerMapper.toReaderDto(readerRepository.findById(id).orElseThrow(ReaderNotFoundException::new));
    }
}
