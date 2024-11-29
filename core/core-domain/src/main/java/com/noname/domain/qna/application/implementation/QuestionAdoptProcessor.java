package com.noname.domain.qna.application.implementation;

import com.noname.domain.common.LoginUser;
import com.noname.domain.qna.domain.Answer;
import com.noname.domain.qna.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class QuestionAdoptProcessor {

    private final AnswerReader answerReader;
    private final AnswerWriter answerWriter;
    private final QuestionWriter questionWriter;

    @Transactional
    public void process(LoginUser user, Question question, Answer answer){
        if (question.hasAlreadyAdoptedAnswer()) {
            Long prevAcceptedAnswerId = question.getAcceptedAnswerId();
            Answer previousAdoptedAnswer = answerReader.getById(prevAcceptedAnswerId);
            answerWriter.unadopt(previousAdoptedAnswer);
        }
        questionWriter.adopt(user, question, answer);
        Answer newAnswerToAdopt = answerReader.getById(answer.getId());
        answerWriter.adopt(newAnswerToAdopt);
    }
}
