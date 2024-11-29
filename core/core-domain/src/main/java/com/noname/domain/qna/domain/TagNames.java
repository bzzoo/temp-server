package com.noname.domain.qna.domain;

import java.util.Set;
import java.util.stream.Collectors;

public class TagNames {

    private final Set<TagName> tagNames;

    private TagNames(Set<String> names) {
        this.tagNames = names.stream()
                .map(TagName::of)
                .collect(Collectors.toSet());
    }

    public static TagNames of(Set<String> tagNames) {
        return new TagNames(tagNames);
    }

    public boolean isNewTag(TagName name) {
        return !tagNames.contains(name);
    }

    public Set<TagName> getNames() {
        return tagNames;
    }

    public Set<String> getNameValues() {
        return tagNames.stream()
                .map(TagName::getValue)
                .collect(Collectors.toSet());
    }
}
