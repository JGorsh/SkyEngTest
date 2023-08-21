package com.example.repository;

import com.example.model.domain.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
}
