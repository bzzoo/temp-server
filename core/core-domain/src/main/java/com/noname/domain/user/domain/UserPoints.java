package com.noname.domain.user.domain;

public record UserPoints(
        int value
) {
    public UserPoints {
        if (value < 0) {
            throw new IllegalArgumentException("유저 포인트 최소값은 0 입니다.");
        }
    }

    public UserPoints add(int pointsToAdd) {
        return new UserPoints(this.value + pointsToAdd);
    }
}
