package com.example.mapping;

import com.example.model.domain.PostOffice;
import com.example.model.dto.PostOfficeDto;
import org.mapstruct.Mapper;

@Mapper
public interface PostOfficeMapper {
    PostOffice toEntity(PostOfficeDto projectDto);

    PostOfficeDto fromEntity(PostOffice project);
}
