package com.example.controller;

import com.example.errors.IncorrectDataException;
import com.example.model.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Tag(name = "mail-controller", description = "The Mailing API")
@RequestMapping("mailing")
public interface MailApi {

    @Operation(summary = "Регистрация нового почтового отправления")
    @PostMapping("registration")
    MailingDto createMailing(CreateMailingRequestDto mailing);


    @Operation(summary = "Приход почтового отправления в почтовый офис")
    @PutMapping("arrival")
    MailingDto createArrivalMail(CreateArrivalMailingRequestDto mailing) throws IncorrectDataException;

    @Operation(summary = "Отправка почтового отправления из офиса")
    @PutMapping("departure")
    MailingDto createDepartureMail(CreateDepartureMailRequestDto mailing) throws IncorrectDataException;

    @Operation(summary = "Получение почтового отправления адресатом")
    @PutMapping("receiving")
    MailingDto createReceivingMail(CreateReceiptAddresseeRequestDto mailing) throws IncorrectDataException;

    @Operation(summary = "Проверка статуса почтового отправления")
    @GetMapping("status")
    MailingDto getStatus (@RequestParam UUID uuid);

    @Operation(summary = "Просмотр истории движения почтового отправления")
    @GetMapping("full")
    Page<MailingDto> getAllMailingByUUID(@RequestParam UUID uuid,
                                         @PageableDefault(value = 100, page = 0) Pageable pageable);
}
