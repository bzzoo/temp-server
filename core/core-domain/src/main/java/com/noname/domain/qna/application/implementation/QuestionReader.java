package com.noname.domain.qna.application.implementation;

import com.noname.domain.common.LoginUser;
import com.noname.domain.qna.application.port.QuestionRepository;
import com.noname.domain.qna.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionReader {

    private final QuestionRepository questionRepository;

    public Question getById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("질문을 찾을 수 없습니다."));
    }

    public Question validate(LoginUser user, Long questionId) {
        Question question = getById(questionId);
        question.validateVotable(user.id());
        return question;
    }

}
