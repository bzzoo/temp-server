package com.noname.domain.qna.application.implementation;

import com.noname.domain.qna.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AnswerRestrictionValidator {

    private final AnswerReader answerReader;

    public void validateQuestionDelete(Question question) {
        boolean hasAtLeastOneAnswer = answerReader.existsByQuestionId(question.getId());
        if (hasAtLeastOneAnswer) {
            throw new IllegalArgumentException("질문에 답변이 존재하여 삭제할 수 없습니다.");
        }
    }
}
