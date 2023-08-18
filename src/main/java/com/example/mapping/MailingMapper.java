package com.example.mapping;

import com.example.model.domain.Mailing;
import com.example.model.dto.CreateMailingRequestDto;
import com.example.model.dto.MailingDto;
import com.example.model.dto.UpdateMailingRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface MailingMapper {
    MailingDto fromEntity(Mailing mailing);

    Mailing toEntity(MailingDto mailingDto);

    Mailing toEntity(CreateMailingRequestDto mailingDto);

    Mailing updateEntityFromDto(UpdateMailingRequestDto mailingDto, @MappingTarget Mailing mailing);
}
