package com.noname.domain.user.domain;

public record PointDelta(
        int value
) {
    private static final int MIN_POINTS = 0;
    private static final int MAX_POINTS = 10_000;

    public PointDelta {
        if (value < MIN_POINTS || value > MAX_POINTS) {
            throw new IllegalArgumentException("유저점수는 " + MIN_POINTS + " ~ " + MAX_POINTS + "사이입니다.");
        }
    }

    public static PointDelta of(int value) {
        return new PointDelta(value);
    }

    public static PointDelta zero() {
        return new PointDelta(0);
    }

    public static PointDelta from(PointChangeReason reason) {
        return new PointDelta(reason.getChangePointValue());
    }

    public PointDelta increase(int additionalPoints) {
        int newPoints = Math.min(value + additionalPoints, MAX_POINTS);
        return new PointDelta(newPoints);
    }

    public PointDelta decrease(int reductionPoints) {
        int newPoints = Math.max(value - reductionPoints, MIN_POINTS);
        return new PointDelta(newPoints);
    }
}
