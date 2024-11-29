package com.noname.domain.qna.domain;

public final class QuestionSeasonPoint {

    private static final int DEFAULT_VALUE = 0;

    private final int value;

    public QuestionSeasonPoint(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("비정상적인 값입니다.");
        }
        this.value = value;
    }

    public static QuestionSeasonPoint of(int value) {
        return new QuestionSeasonPoint(value);
    }

    public static QuestionSeasonPoint zero() {
        return new QuestionSeasonPoint(DEFAULT_VALUE);
    }

    public int getValue() {
        return value;
    }
}
