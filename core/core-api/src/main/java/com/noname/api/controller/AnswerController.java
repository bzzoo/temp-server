package com.noname.api.controller;

import com.noname.api.support.ApiResponse;
import com.noname.domain.common.LoginUser;
import com.noname.domain.qna.application.AnswerService;
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
@RequestMapping("/api/answers")
@RestController
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping()
    ApiResponse<Long> create(
            @AuthenticationPrincipal LoginUser user,
            @RequestBody AnswerRequests.AnswerCreateRequest request
    ) {
        Long answerId = answerService.create(user, request.questionId(), request.toContent());
        return ApiResponse.success(answerId);
    }

    @PostMapping("/{answerId}")
    ApiResponse<Void> edit(
            @AuthenticationPrincipal LoginUser user,
            @PathVariable(name = "answerId") Long answerId,
            @RequestBody AnswerRequests.AnswerEditRequest request
    ) {
        answerService.edit(user, answerId, request.toContent());
        return ApiResponse.success();
    }

    @PutMapping("/{answerId}")
    ApiResponse<Void> delete(
            @AuthenticationPrincipal LoginUser user,
            @PathVariable(name = "answerId") Long answerId
    ) {
        answerService.delete(user, answerId);
        return ApiResponse.success();
    }

    @PostMapping("/{answerId}/upvote")
    ApiResponse<Void> upvote(
            @AuthenticationPrincipal LoginUser user,
            @PathVariable(name = "answerId") Long answerId
    ) {
        VoteTarget target = new VoteTarget(answerId, VoteTargetType.ANSWER);
        answerService.upvote(user, target);
        return ApiResponse.success();
    }

    @PostMapping("/{answerId}/downvote")
    ApiResponse<Void> downvote(
            @AuthenticationPrincipal LoginUser user,
            @PathVariable(name = "answerId") Long answerId
    ) {
        VoteTarget target = new VoteTarget(answerId, VoteTargetType.ANSWER);
        answerService.upvote(user, target);
        return ApiResponse.success();
    }
}




