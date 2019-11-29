package com.kodilla.library.service;

import com.kodilla.library.dto.CopyDto;
import com.kodilla.library.dto.RentDto;
import com.kodilla.library.exception.CopyNotFoundException;
import com.kodilla.library.exception.ReaderNotFoundException;
import com.kodilla.library.exception.RentNotFoundException;
import com.kodilla.library.mapper.RentMapper;
import com.kodilla.library.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CopyService copyService;

    private static final String STATUS = "Lost";

    public RentDto createRent(final RentDto rentDto) throws CopyNotFoundException, ReaderNotFoundException {
        return rentMapper.toRentDto(rentRepository.save(rentMapper.toRent(rentDto)));
    }

    public List<RentDto> getAllRents() {
        return rentMapper.toRentDtoList(rentRepository.findAll());
    }

    public RentDto getRentById(long id) throws RentNotFoundException {
        return rentMapper.toRentDto(rentRepository.findById(id).orElseThrow(RentNotFoundException::new));
    }

    public RentDto updateRent(final RentDto rentDto) throws RentNotFoundException, CopyNotFoundException, ReaderNotFoundException {
        if (rentRepository.findById(rentDto.getId()).isPresent()) {
            return rentMapper.toRentDto(rentRepository.save(rentMapper.toRent(rentDto)));
        } else {
            throw new RentNotFoundException();
        }
    }

    public RentDto payForLost(final long id) throws RentNotFoundException, CopyNotFoundException, ReaderNotFoundException {
        RentDto rentDto = getRentById(id);
        rentDto.setDateOfReturn(LocalDate.now());
        RentDto updatedRent = updateRent(rentDto);
        CopyDto copyDto = copyService.getCopyById(rentDto.getCopyId());
        copyDto.setStatus(STATUS);
        copyService.updateCopy(copyDto);
        return updatedRent;
    }
}
