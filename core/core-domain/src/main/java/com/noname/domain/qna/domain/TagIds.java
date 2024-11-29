package com.noname.domain.qna.domain;

import java.util.Set;

public class TagIds {
    
    private final Set<Long> tagIdValues;

    private TagIds(Set<Long> tagIds) {
        this.tagIdValues = tagIds;
    }
    
    public static TagIds of(Set<Long> tagIdValues) {
        return new TagIds(tagIdValues);
    }

    public Set<Long> getTagIds() {
        return tagIdValues;
    }
}
