package com.noname.domain.qna.application.port;

import com.noname.domain.qna.domain.Answer;
import java.util.Optional;

public interface AnswerRepository {

    Long save(Answer answer);

    Optional<Answer> findById(Long answerId);

    boolean existsAnswerByQuestionId(Long questionId);
}
