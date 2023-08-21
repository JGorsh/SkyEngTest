package com.example.repository;

import com.example.model.domain.Mailing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MailingRepository extends JpaRepository<Mailing, Long> {
}
