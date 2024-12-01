package com.noname.maindb;

import com.noname.domain.qna.application.port.AnswerRepository;
import com.noname.domain.qna.domain.Answer;
import com.noname.maindb.qna.AnswerEntity;
import com.noname.maindb.qna.AnswerJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AnswerPersistenceAdapter implements AnswerRepository {

    private final AnswerJpaRepository answerJpaRepository;

    @Transactional
    public Long save(Answer answer) {
        return answerJpaRepository.save(AnswerEntity.from(answer)).getId();
    }


    public boolean existsAnswerByQuestionId(Long questionId) {
        return answerJpaRepository.existsAnswerByQuestionId(questionId);
    }


    public Optional<Answer> findById(Long answerId) {
        return answerJpaRepository.findById(answerId)
                .map(AnswerEntity::toDomain);
    }
}
