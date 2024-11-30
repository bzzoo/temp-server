package com.noname.api.controller;

import com.noname.domain.qna.domain.AnswerContent;

public class AnswerRequests {

    public record AnswerCreateRequest(
            Long questionId,
            String content
    ) {
        public AnswerContent toContent() {
            return new AnswerContent(content);
        }
    }

    public record AnswerEditRequest(
            String content
    ) {
        public AnswerContent toContent() {
            return new AnswerContent(content);
        }
    }
}
