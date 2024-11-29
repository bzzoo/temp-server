package com.noname.domain.qna.application;

import com.noname.domain.common.LoginUser;
import com.noname.domain.qna.application.implementation.AnswerReader;
import com.noname.domain.qna.application.implementation.QuestionAdoptProcessor;
import com.noname.domain.qna.application.implementation.QuestionReader;
import com.noname.domain.qna.application.implementation.QuestionWriter;
import com.noname.domain.qna.application.implementation.VoteProcessor;
import com.noname.domain.qna.domain.Answer;
import com.noname.domain.qna.domain.Question;
import com.noname.domain.qna.domain.QuestionContent;
import com.noname.domain.qna.domain.QuestionTitle;
import com.noname.domain.qna.domain.TagNames;
import com.noname.domain.qna.domain.VoteTarget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionReader questionReader;
    private final QuestionWriter questionWriter;
    private final VoteProcessor voteProcessor;
    private final QuestionAdoptProcessor adoptProcessor;
    private final AnswerReader answerReader;

    public Long create(LoginUser user, QuestionTitle title, QuestionContent content, TagNames tagNames) {
        return questionWriter.create(user, title, content, tagNames);
    }

    public void edit(LoginUser user, Long questionId, QuestionTitle title, QuestionContent content, TagNames tagNames) {
        Question question = questionReader.getById(questionId);
        questionWriter.edit(user, question, title, content, tagNames);
    }

    public void delete(LoginUser user, Long questionId) {
        Question question = questionReader.getById(questionId);
        questionWriter.delete(user, question);
    }

    public void adopt(LoginUser user, Long questionId, Long answerId){
        Question question = questionReader.getById(questionId);
        Answer answer = answerReader.getById(answerId);
        adoptProcessor.process(user, question, answer);
    }

    public void upvote(LoginUser user, VoteTarget target) {
        Question question = questionReader.getById(target.getId());
        voteProcessor.upvote(user, question, target);
    }

    public void downvote(LoginUser user, VoteTarget target) {
        Question question = questionReader.getById(target.getId());
        voteProcessor.downvote(user, question, target);
    }

}
