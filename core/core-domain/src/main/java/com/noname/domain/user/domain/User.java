package com.noname.domain.user.domain;

import lombok.Builder;

public class User {
    private final Long id;
    private Username username;
    private UserEmail email;
    private UserRole role;
    private UserPoints points;

    @Builder
    private User(
            Long id,
            Username username,
            UserEmail email,
            UserRole role,
            UserPoints points
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.points = points;
    }

    public static User create(Username username, UserEmail email, UserRole role, UserPoints points) {
        return new User(null, username, email, role, points);
    }

    public void applyPoint(int points) {
        this.points = this.points.add(points);
    }

    public  void validateEnoughPointsForQuestion(int value) {
        hasEnoughPoints(value);
    }

    private void hasEnoughPoints(int value) {
        if(this.points.value() < value) {
            throw new IllegalArgumentException("포인트가 충분하지 않습니다.");
        }
    }

    public Long getId() {
        return id;
    }

}
