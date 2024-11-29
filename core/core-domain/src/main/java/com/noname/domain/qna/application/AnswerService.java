package com.noname.domain.qna.application;

import com.noname.domain.common.LoginUser;
import com.noname.domain.qna.application.implementation.AnswerReader;
import com.noname.domain.qna.application.implementation.AnswerWriter;
import com.noname.domain.qna.application.implementation.QuestionReader;
import com.noname.domain.qna.application.implementation.VoteProcessor;
import com.noname.domain.qna.domain.Answer;
import com.noname.domain.qna.domain.AnswerContent;
import com.noname.domain.qna.domain.Question;
import com.noname.domain.qna.domain.VoteTarget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final QuestionReader questionReader;
    private final AnswerWriter answerWriter;
    private final AnswerReader answerReader;
    private final VoteProcessor voteProcessor;

    public Long create(LoginUser user, Long questionId, AnswerContent content) {
        Question question = questionReader.getById(questionId);
        return answerWriter.create(user, question, content);
    }

    public void edit(LoginUser user, Long answerId, AnswerContent content) {
        Answer answer = answerReader.getById(answerId);
        answerWriter.edit(user, answer, content);
    }

    public void delete(LoginUser user, Long answerId) {
        Answer answer = answerReader.getById(answerId);
        answerWriter.delete(user, answer);
    }

    public void upvote(LoginUser user, VoteTarget target) {
        Answer answer = answerReader.getById(target.getId());
        voteProcessor.upvote(user, answer, target);
    }

    public void downvote(LoginUser user, VoteTarget target) {
        Answer answer = answerReader.getById(target.getId());
        voteProcessor.upvote(user, answer, target);
    }
}
