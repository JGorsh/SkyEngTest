package com.example.service;

import com.example.model.dto.CommentDto;
import org.springframework.data.domain.Page;

public interface CommentService {

    Page<CommentDto> getAllComment (Long issueId, Integer page, Integer size);
}
