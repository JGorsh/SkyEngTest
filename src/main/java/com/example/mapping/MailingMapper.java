package com.example.mapping;

import com.example.model.domain.Mailing;
import com.example.model.dto.CreateMailingRequestDto;
import com.example.model.dto.MailingDto;
import com.example.model.dto.CreateArrivalMailingRequestDto;
import com.example.model.dto.UpdateMailingRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(uses = {PostOfficeMapper.class})
public interface MailingMapper {

    MailingDto fromEntity(Mailing mailing);

    UpdateMailingRequestDto dtoFromEntity(Mailing mailing);

    Mailing toEntity(MailingDto mailingDto);

    Mailing toEntity(CreateMailingRequestDto mailingRequestDto);
    Mailing toEntity(UpdateMailingRequestDto mailingRequestDto);

    Mailing updateEntityFromDto(CreateArrivalMailingRequestDto updateMailingRequestDto, @MappingTarget Mailing mailing);
}
