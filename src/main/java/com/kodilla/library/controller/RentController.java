package com.kodilla.library.controller;

import com.kodilla.library.dto.RentDto;
import com.kodilla.library.exception.CopyNotFoundException;
import com.kodilla.library.exception.ReaderNotFoundException;
import com.kodilla.library.exception.RentNotFoundException;
import com.kodilla.library.service.RentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/rent")
@Slf4j
public class RentController {

    @Autowired
    private RentService rentService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public RentDto createRent(@RequestBody RentDto rentDto) throws CopyNotFoundException, ReaderNotFoundException {
        log.info("Creating new rent for copy {}, reader {}", rentDto.getCopyId(), rentDto.getReaderId());
        return rentService.createRent(rentDto);
    }

    @GetMapping(value = "all")
    public List<RentDto> getRents() {
        log.info("Getting list of rents");
        return rentService.getAllRents();
    }

    @GetMapping("{rentId}")
    public RentDto getRent(@PathVariable long rentId) throws RentNotFoundException {
        log.info("Getting rent with ID {}", rentId);
        return rentService.getRentById(rentId);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public RentDto updateRent(@RequestBody RentDto rentDto) throws RentNotFoundException, CopyNotFoundException, ReaderNotFoundException{
        log.info("Updating rent with ID {}", rentDto.getId());
        return rentService.updateRent(rentDto);
    }

    @PutMapping(value = "pay/{rentId}")
    public RentDto payForLostBook(@PathVariable long rentId) throws RentNotFoundException, CopyNotFoundException, ReaderNotFoundException {
        log.info("Closing rent with ID {} through paying", rentId);
        return rentService.payForLost(rentId);
    }
}
