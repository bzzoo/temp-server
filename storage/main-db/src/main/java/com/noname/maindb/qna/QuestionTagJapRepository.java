package com.noname.maindb.qna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface QuestionTagJapRepository extends JpaRepository<QuestionTagEntity, Long> {

    Set<QuestionTagEntity> findAllByQuestionId(Long questionId);
}
