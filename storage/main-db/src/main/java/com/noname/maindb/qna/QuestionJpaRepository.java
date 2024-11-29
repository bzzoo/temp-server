package com.noname.maindb.qna;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionJpaRepository extends JpaRepository<QuestionEntity, Long> {
}
