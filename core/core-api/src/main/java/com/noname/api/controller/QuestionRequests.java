package com.noname.api.controller;

import com.noname.domain.qna.domain.QuestionContent;
import com.noname.domain.qna.domain.QuestionTitle;
import com.noname.domain.qna.domain.TagNames;

import java.util.Set;

public class QuestionRequests {

    public record QuestionCreateRequest(
            String title,
            String content,
            Set<String> tagNames
    ) {
        public QuestionContent toContent() {
            return new QuestionContent(content);
        }

        public QuestionTitle toTitle() {
            return new QuestionTitle(title);
        }

        public TagNames toTagNames() {
            return TagNames.of(tagNames);
        }
    }

    public record QuestionEditRequest(
            String title,
            String content,
            Set<String> tagNames
    ) {
        public QuestionContent toContent() {
            return new QuestionContent(content);
        }

        public QuestionTitle toTitle() {
            return new QuestionTitle(title);
        }

        public TagNames toTagNames() {
            return TagNames.of(tagNames);
        }
    }

    public record QuestionAdoptAnswerRequest(

    ) {

    }
}
