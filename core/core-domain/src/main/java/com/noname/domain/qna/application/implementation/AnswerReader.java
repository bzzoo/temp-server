package com.noname.domain.qna.application.implementation;

import com.noname.domain.qna.application.port.AnswerRepository;
import com.noname.domain.qna.domain.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AnswerReader {

    private final AnswerRepository answerRepository;

    public Answer getById(Long answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new IllegalArgumentException("답변을 찾을 수 없습니다."));
    }

    public boolean existsByQuestionId(Long questionId) {
        return answerRepository.existsAnswerByQuestionId(questionId);
    }
}
