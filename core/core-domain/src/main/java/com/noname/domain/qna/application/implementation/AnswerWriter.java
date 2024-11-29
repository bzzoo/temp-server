package com.noname.domain.qna.application.implementation;

import com.noname.domain.common.LoginUser;
import com.noname.domain.qna.application.port.AnswerRepository;
import com.noname.domain.qna.domain.Answer;
import com.noname.domain.qna.domain.AnswerContent;
import com.noname.domain.qna.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AnswerWriter {

    private final AnswerRepository answerRepository;

    public Long create(LoginUser user, Question question, AnswerContent content) {
        question.validateAddAnswer(user.id());
        Answer answer = Answer.generate( user.id(), question.getId(), content);
        return answerRepository.save(answer);
    }

    public void edit(LoginUser user, Answer answer, AnswerContent content) {
        answer.updateContent(user.id(), content);
        answerRepository.save(answer);
    }

    public void delete(LoginUser user, Answer answer) {
        answer.delete(user.id());
        answerRepository.save(answer);
    }

    public void adopt(Answer answer) {
        answer.accept();
        answerRepository.save(answer);
    }

    public void unadopt(Answer prevAdotedAnswer) {
        prevAdotedAnswer.unaccept();
        answerRepository.save(prevAdotedAnswer);
    }
}
