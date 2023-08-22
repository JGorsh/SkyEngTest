package com.example.repository;

import com.example.model.domain.Mailing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface MailingRepository extends JpaRepository<Mailing, Long> {

    List<Mailing> findAllByUuid(UUID uuid);
}
