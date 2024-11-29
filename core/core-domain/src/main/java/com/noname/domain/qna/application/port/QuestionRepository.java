package com.noname.domain.qna.application.port;

import com.noname.domain.qna.domain.Question;
import java.util.Optional;

public interface QuestionRepository {

    Long save(Question newQuestion);

    Optional<Question> findById(Long id);
}
