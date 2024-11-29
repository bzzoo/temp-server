package com.noname.maindb;

import com.noname.domain.qna.application.port.QuestionRepository;
import com.noname.domain.qna.domain.Question;
import com.noname.domain.qna.domain.QuestionContent;
import com.noname.domain.qna.domain.QuestionSeasonPoint;
import com.noname.domain.qna.domain.QuestionStatus;
import com.noname.domain.qna.domain.QuestionTitle;
import com.noname.domain.qna.domain.TagIds;
import com.noname.maindb.qna.QuestionEntity;
import com.noname.maindb.qna.QuestionJpaRepository;
import com.noname.maindb.qna.QuestionTagEntity;
import com.noname.maindb.qna.QuestionTagJapRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class QuestionPersistenceAdapter implements QuestionRepository {

    private final QuestionJpaRepository questionJpaRepository;
    private final QuestionTagJapRepository questionTagJapRepository;

    @Override
    public Long save(Question question) {
        QuestionEntity questionEntity = QuestionEntity.from(question);
        QuestionEntity savedQuestionEntity = questionJpaRepository.save(questionEntity);

        Set<Long> tagIds = question.getTagIdValues();
        List<QuestionTagEntity> questionTagEntities = tagIds.stream()
                .map(tagId -> new QuestionTagEntity(savedQuestionEntity.getId(), tagId))
                .collect(Collectors.toList());

        questionTagJapRepository.saveAll(questionTagEntities);
        return savedQuestionEntity.getId();
    }

    public Optional<Question> findById(Long id) {
        return questionJpaRepository.findById(id)
                .map(questionEntity -> {
                    Set<Long> tagIds = questionTagJapRepository.findAllByQuestionId(id)
                            .stream()
                            .map(QuestionTagEntity::getTagId)
                            .collect(Collectors.toSet());

                    TagIds tagIdsAggregate = TagIds.of(tagIds);
                    return mapToQuestion(questionEntity, tagIdsAggregate);
                });
    }


    private Question mapToQuestion(QuestionEntity questionEntity, TagIds tagIds) {
        return Question.builder()
                .id(questionEntity.getId())
                .authorId(questionEntity.getAuthorId())
                .acceptedAnswerId(questionEntity.getAcceptedAnswerId())
                .tagIds(tagIds)
                .title(new QuestionTitle(questionEntity.getTitle()))
                .content(new QuestionContent(questionEntity.getContent()))
                .status(QuestionStatus.valueOf(questionEntity.getStatus()))
                .seasonPoint(new QuestionSeasonPoint(questionEntity.getSeasonPoint()))
                .build();
    }
}
