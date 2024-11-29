package com.noname.domain.qna.domain;

public class TagName {

    private final String value;

    private TagName(String value) {
        this.value = value;
    }

    public static TagName of(String name) {
        return new TagName(name);
    }

    public String getValue() {
        return value;
    }
}
