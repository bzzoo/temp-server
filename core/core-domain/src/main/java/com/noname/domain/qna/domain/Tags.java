package com.noname.domain.qna.domain;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import lombok.Getter;

@Getter
public class Tags {

    private final Set<Tag> tags;

    public Tags(Set<Tag> tags) {
        this.tags = tags;
    }

    public static Tags of(Set<Tag> tags) {
        return new Tags(tags);
    }

    public TagIds getTagIds() {
        Set<Long> tagIdValues = getTagIdValues();
        return TagIds.of(tagIdValues);
    }

    public Set<Long> getTagIdValues() {
        return tags.stream()
                .map(Tag::getId)
                .collect(toSet());
    }

    public Tags filterNewTag(TagNames names) {
        Set<Tag> newTags = names.getNames().stream()
                .filter(names::isNewTag)
                .map(Tag::of)
                .collect(toSet());
        return Tags.of(newTags);
    }

    public void merge(Tags existingTags) {
        tags.addAll(existingTags.tags);
    }

}
