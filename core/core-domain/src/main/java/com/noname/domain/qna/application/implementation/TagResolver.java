package com.noname.domain.qna.application.implementation;

import com.noname.domain.qna.application.port.TagRepository;
import com.noname.domain.qna.domain.TagNames;
import com.noname.domain.qna.domain.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TagResolver {

    private final TagRepository tagRepository;

    public Tags fetchOrCreate(TagNames names) {
        Tags tags = tagRepository.findAllByNameIn(names);
        Tags newTags = tags.filterNewTag(names);
        return tagRepository.saveAll(newTags);
    }

}