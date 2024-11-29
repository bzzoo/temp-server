package com.noname.maindb;

import com.noname.domain.qna.application.port.TagRepository;
import com.noname.domain.qna.domain.Tag;
import com.noname.domain.qna.domain.TagNames;
import com.noname.domain.qna.domain.Tags;
import com.noname.maindb.qna.TagEntity;
import com.noname.maindb.qna.TagJpaRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TagPersistenceAdapter implements TagRepository {

    private final TagJpaRepository tagJpaRepository;

    @Override
    public Tags saveAll(Tags tags) {
        List<TagEntity> tagEntities = tags.getTags().stream()
                .map(TagEntity::fromDomain)
                .collect(Collectors.toList());

        return Tags.of(tagJpaRepository.saveAll(tagEntities).stream()
                .map(TagEntity::toDomain)
                .collect(Collectors.toSet()));
    }

    public Tags findAllByNameIn(TagNames names) {
        Set<Tag> tags = tagJpaRepository
                .findAllByNameIn(names.getNameValues())
                .stream()
                .map(TagEntity::toDomain)
                .collect(Collectors.toSet());
        return Tags.of(tags);
    }
}
