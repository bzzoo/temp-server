package com.noname.domain.qna.application.implementation;

import com.noname.domain.common.LoginUser;
import com.noname.domain.qna.application.port.VoteRepository;
import com.noname.domain.qna.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class VoteRestrictionValidator {

    private final VoteRepository voteRepository;
    private final QuestionReader questionReader;

    public void validateQuestionUpdatable(LoginUser user, Question questionId) {
        Question question = questionReader.getById(questionId.getId());
    }

}
