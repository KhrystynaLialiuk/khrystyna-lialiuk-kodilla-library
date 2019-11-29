package com.kodilla.library.controller;

import com.kodilla.library.dto.CopyDto;
import com.kodilla.library.exception.CopyNotFoundException;
import com.kodilla.library.exception.TitleNotFoundException;
import com.kodilla.library.service.CopyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/copy")
@Slf4j
public class CopyController {

    @Autowired
    private CopyService copyService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public CopyDto createCopy(@RequestBody CopyDto copyDto) {
        log.info("Creating new copy for {}", copyDto.getTitleId());
        return copyService.createCopy(copyDto);
    }

    @GetMapping(value = "all")
    public List<CopyDto> getCopies(){
        log.info("Getting list of copies");
        return copyService.getAllCopies();
    }

    @GetMapping("{copyId}")
    public CopyDto getCopy(@PathVariable long copyId) throws CopyNotFoundException {
        log.info("Getting copy with ID {}", copyId);
        return copyService.getCopyById(copyId);
    }

    @GetMapping
    public List<CopyDto> getAvailableCopies(@RequestParam String title) throws TitleNotFoundException {
        log.info("Getting available copies of {}", title);
        return copyService.getAvailableCopies(title);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public CopyDto updateCopy(@RequestBody CopyDto copyDto) throws CopyNotFoundException {
        log.info("Updating copy with ID {}", copyDto.getId());
        return copyService.updateCopy(copyDto);
    }
}
