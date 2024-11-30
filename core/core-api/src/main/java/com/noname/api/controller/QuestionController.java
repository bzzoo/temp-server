package com.noname.api.controller;

import com.noname.api.support.ApiResponse;
import com.noname.domain.common.LoginUser;
import com.noname.domain.qna.application.QuestionService;
import com.noname.domain.qna.domain.VoteTarget;
import com.noname.domain.qna.domain.VoteTargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/questions")
@RestController
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping()
    ApiResponse<Long> create(
            @AuthenticationPrincipal LoginUser user,
            @RequestBody QuestionRequests.QuestionCreateRequest request
    ) {
        Long questionId = questionService.create(user, request.toTitle(), request.toContent(),
                request.toTagNames());
        return ApiResponse.success(questionId);
    }

    @PostMapping("/{questionId}")
    ApiResponse<Void> edit(
            @AuthenticationPrincipal LoginUser user,
            @PathVariable(name = "questionId") Long questionId,
            @RequestBody QuestionRequests.QuestionEditRequest request
    ) {
        questionService.edit(user, questionId, request.toTitle(), request.toContent(), request.toTagNames());
        return ApiResponse.success();
    }

    @PutMapping("/{questionId}")
    ApiResponse<Void> delete(
            @AuthenticationPrincipal LoginUser user,
            @PathVariable(name = "questionId") Long questionId
    ) {
        questionService.delete(user, questionId);
        return ApiResponse.success();
    }

    @PostMapping("/{questionId}/upvote")
    ApiResponse<Void> upvote(
            @AuthenticationPrincipal LoginUser user,
            @PathVariable(name = "questionId") Long questionId
    ) {
        VoteTarget target = new VoteTarget(questionId, VoteTargetType.QUESTION);
        questionService.upvote(user, target);
        return ApiResponse.success();
    }

    @PostMapping("/{questionId}/downvote")
    ApiResponse<Void> downvote(
            @AuthenticationPrincipal LoginUser user,
            @PathVariable(name = "questionId") Long questionId
    ) {
        VoteTarget target = new VoteTarget(questionId, VoteTargetType.QUESTION);
        questionService.downvote(user, target);
        return ApiResponse.success();
    }

    @PostMapping("/{questionId}/adopt-answer/{answerId}")
    ApiResponse<Void> adoptAnswer(
            @AuthenticationPrincipal LoginUser user,
            @PathVariable(name = "questionId") Long questionId,
            @PathVariable(name = "answerId") Long answerId
    ) {
        questionService.adopt(user, answerId, questionId);
        return ApiResponse.success();
    }
}
