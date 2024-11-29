package com.noname.maindb.qna;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerJpaRepository extends JpaRepository<AnswerEntity, Long> {
    boolean existsAnswerByQuestionId(Long questionId);
}
