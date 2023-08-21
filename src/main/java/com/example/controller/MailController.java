package com.example.controller;

import com.example.model.domain.Mailing;
import com.example.model.dto.CreateMailingRequestDto;
import com.example.model.dto.MailingDto;
import com.example.service.MailingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class MailController implements MailApi {
    @Override
    public Mailing createMailing(@RequestBody @Valid Mailing mailing) {
        return mailingService.createMailing(mailing);
    }

    private final MailingService mailingService;
//
//    private FilterSpecification filterSpecification;
//
//    private final CommentService commentService;
//
//    @Override
//    public Page<IssueDto> getAllIssues(Integer page, Integer size) {
//        return issueService.getAllIssues(page, size);
//    }
//
//    @Override
//    public IssueDto getOne(Long id) {
//        return issueService.getOne(id);
//    }
//
//    @Override
//    public IssueDto updateIssue(@RequestBody @Valid UpdateIssueRequestDto issue) {
//        return issueService.updateIssue(issue);
//    }
//
//    @Override
//    public Page<IssueDto> filteredListWithCriteria(Integer page, Integer size, List<SearchIssueDto> searchIssueDtos) {
//        Specification<Issue> searchSpecification = filterSpecification.getSearchSpecification(searchIssueDtos);
//        return issueService.getAllIssues( searchSpecification, page, size);
//    }
//
//    @Override
//    public Page<CommentDto> getAllComments(Long issueId, Integer page, Integer size) {
//        return commentService.getAllComment(issueId, page, size);
//    }
}
