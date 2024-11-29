package com.noname.domain.qna.application.implementation;

import com.noname.domain.common.LoginUser;
import com.noname.domain.qna.application.port.QuestionRepository;
import com.noname.domain.qna.domain.Answer;
import com.noname.domain.qna.domain.Question;
import com.noname.domain.qna.domain.QuestionContent;
import com.noname.domain.qna.domain.QuestionTitle;
import com.noname.domain.qna.domain.TagNames;
import com.noname.domain.qna.domain.Tags;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class QuestionWriter {

    private final TagResolver tagResolver;
    private final QuestionDailyLimitChecker questionDailyLimitChecker;
    private final VoteRestrictionValidator voteRestrictionValidator;
    private final AnswerRestrictionValidator answerRestrictionValidator;
    private final QuestionRepository questionRepository;

    @Transactional
    public Long create(LoginUser user, QuestionTitle title, QuestionContent content, TagNames names) {
        questionDailyLimitChecker.check(user);
        Tags tags = tagResolver.fetchOrCreate(names);
        Question newQuestion = Question.generate(user.id(), title, content, tags.getTagIds());
        return questionRepository.save(newQuestion);
    }

    @Transactional
    public void edit(LoginUser user, Question question, QuestionTitle title, QuestionContent content, TagNames names) {
        Tags tags = tagResolver.fetchOrCreate(names);
        question.edit(user.id(), title, content, tags.getTagIds());
        questionRepository.save(question);
    }

    public void delete(LoginUser user, Question question) {
        answerRestrictionValidator.validateQuestionDelete(question);
        voteRestrictionValidator.validateQuestionUpdatable(user, question);
        question.delete(user.id());
        questionRepository.save(question);
    }

    public void adopt(LoginUser user, Question question, Answer answer){
        question.adoptAnswer(user.id(), answer.getId());
        questionRepository.save(question);
    }
}
