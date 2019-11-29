package com.kodilla.library.controller;

import com.kodilla.library.dto.ReaderDto;
import com.kodilla.library.exception.ReaderNotFoundException;
import com.kodilla.library.service.ReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/reader")
@Slf4j
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ReaderDto createReader(@RequestBody ReaderDto readerDto) {
        log.info("Creating new reader {} {}", readerDto.getName(), readerDto.getSurname());
        return readerService.createReader(readerDto);
    }

    @GetMapping(value = "all")
    public List<ReaderDto> getReaders() {
        log.info("Getting list of readers");
        return readerService.findAllReaders();
    }

    @GetMapping("{readerId}")
    public ReaderDto getReader(@PathVariable long readerId) throws ReaderNotFoundException {
        log.info("Getting reader with ID {}", readerId);
        return readerService.findReaderById(readerId);
    }
}
