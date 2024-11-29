package com.noname.domain.qna.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Tag {
    
    private final Long id;
    private final TagName name;

    @Builder
    private Tag(Long id, TagName name) {
        this.id = id;
        this.name = name;
    }
    
    public static Tag of(TagName name) {
        return new Tag(null, name);
    }

    public String getNameValue() {
        return this.name.getValue();
    }
}
