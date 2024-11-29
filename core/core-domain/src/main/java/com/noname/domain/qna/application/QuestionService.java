package com.noname.domain.qna.application;

import com.noname.domain.common.LoginUser;
import com.noname.domain.qna.application.implementation.QuestionReader;
import com.noname.domain.qna.application.implementation.QuestionWriter;
import com.noname.domain.qna.domain.Question;
import com.noname.domain.qna.domain.QuestionContent;
import com.noname.domain.qna.domain.QuestionTitle;
import com.noname.domain.qna.domain.TagNames;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionReader questionReader;
    private final QuestionWriter questionWriter;

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

}
