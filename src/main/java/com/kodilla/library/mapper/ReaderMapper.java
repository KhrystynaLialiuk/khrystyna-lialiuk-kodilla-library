package com.kodilla.library.mapper;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    public Reader toReader(final ReaderDto readerDto){
        Reader reader = new Reader();
        reader.setId(readerDto.getId());
        reader.setName(readerDto.getName());
        reader.setSurname(readerDto.getSurname());
        reader.setRegistrationDate(readerDto.getRegistrationDate());
        return reader;
    }

    public ReaderDto toReaderDto(final Reader reader) {
        ReaderDto readerDto = new ReaderDto();
        readerDto.setId(reader.getId());
        readerDto.setName(reader.getName());
        readerDto.setSurname(reader.getSurname());
        readerDto.setRegistrationDate(reader.getRegistrationDate());
        return readerDto;
    }

    public List<ReaderDto> toReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(this::toReaderDto)
                .collect(Collectors.toList());
    }
}
