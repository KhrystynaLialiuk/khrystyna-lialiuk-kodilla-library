package com.kodilla.library.mapper;

import com.kodilla.library.domain.Rent;
import com.kodilla.library.dto.RentDto;
import com.kodilla.library.exception.CopyNotFoundException;
import com.kodilla.library.exception.ReaderNotFoundException;
import com.kodilla.library.repository.CopyRepository;
import com.kodilla.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentMapper {

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private ReaderRepository readerRepository;

    public Rent toRent(final RentDto rentDto) throws CopyNotFoundException, ReaderNotFoundException {
        Rent rent = new Rent();
        rent.setId(rentDto.getId());
        rent.setCopy(copyRepository.findById(rentDto.getCopyId()).orElseThrow(CopyNotFoundException::new));
        rent.setReader(readerRepository.findById(rentDto.getReaderId()).orElseThrow(ReaderNotFoundException::new));
        rent.setDateOfRent(rentDto.getDateOfRent());
        if (rentDto.getDateOfReturn() != null) {
            rent.setDateOfReturn(rentDto.getDateOfReturn());
        }
        return rent;
    }

    public RentDto toRentDto(final Rent rent) {
        RentDto rentDto = new RentDto();
        rentDto.setId(rent.getId());
        rentDto.setCopyId(rent.getCopy().getId());
        rentDto.setReaderId(rent.getReader().getId());
        rentDto.setDateOfRent(rent.getDateOfRent());
        if (rent.getDateOfReturn() != null) {
            rentDto.setDateOfReturn(rent.getDateOfReturn());
        }
        return rentDto;
    }

    public List<RentDto> toRentDtoList(final List<Rent> rentList) {
        return rentList.stream()
                .map(this::toRentDto)
                .collect(Collectors.toList());
    }
}
