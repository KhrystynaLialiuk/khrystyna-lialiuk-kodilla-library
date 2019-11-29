package com.kodilla.library.mapper;

import com.kodilla.library.domain.Title;
import com.kodilla.library.dto.TitleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TitleMapper {

    public Title toTitle(final TitleDto titleDto) {
        Title title = new Title();
        title.setTitle(titleDto.getTitle());
        title.setAuthor(titleDto.getAuthor());
        title.setPublicationYear(titleDto.getPublicationYear());
        return title;
    }

    public TitleDto toTitleDto(final Title title) {
        TitleDto titleDto = new TitleDto();
        titleDto.setId(title.getId());
        titleDto.setTitle(title.getTitle());
        titleDto.setAuthor(title.getAuthor());
        titleDto.setPublicationYear(title.getPublicationYear());
        return titleDto;
    }

    public List<TitleDto> toTitleDtoList(final List<Title> titleList) {
        return titleList.stream()
                .map(this::toTitleDto)
                .collect(Collectors.toList());
    }
}
