package com.kodilla.library.mapper;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.dto.CopyDto;
import com.kodilla.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyMapper {

    @Autowired
    private TitleRepository titleRepository;

    public Copy toCopy(final CopyDto copyDto) {
        Copy copy = new Copy();
        copy.setId(copyDto.getId());
        copy.setStatus(copyDto.getStatus());
        if (titleRepository.findById(copyDto.getTitleId()).isPresent()) {
            copy.setTitle(titleRepository.findById(copyDto.getTitleId()).get());
        }
        return copy;
    }

    public CopyDto toCopyDto(final Copy copy) {
        CopyDto copyDto = new CopyDto();
        copyDto.setId(copy.getId());
        copyDto.setStatus(copy.getStatus());
        copyDto.setTitleId(copy.getTitle().getId());
        return copyDto;
    }

    public List<CopyDto> toCopyDtoList(final List<Copy> copies) {
        return copies.stream()
                .map(this::toCopyDto)
                .collect(Collectors.toList());
    }
}
