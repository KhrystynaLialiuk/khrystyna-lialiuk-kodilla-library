package com.kodilla.library.controller;

import com.kodilla.library.dto.TitleDto;
import com.kodilla.library.exception.TitleNotFoundException;
import com.kodilla.library.service.TitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/title")
@Slf4j
public class TitleController {

    @Autowired
    private TitleService titleService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public TitleDto createTitle(@RequestBody TitleDto titleDto) {
        log.info("Creating title {}", titleDto.getTitle());
        return titleService.createTitle(titleDto);
    }

    @GetMapping(value = "all")
    public List<TitleDto> getTitles() {
        log.info("Getting list of titles");
        return titleService.getAllTitles();
    }

    @GetMapping("{titleId}")
    public TitleDto getTitle(@PathVariable long titleId) throws TitleNotFoundException {
        log.info("Getting title with ID {}", titleId);
        return titleService.getTitleById(titleId);
    }
}
