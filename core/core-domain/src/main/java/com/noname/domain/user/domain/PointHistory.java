package com.noname.domain.user.domain;

import lombok.Builder;

public class PointHistory {
    private final Long id;
    private final Long userId;
    private final Long referenceId;
    private final PointReferenceType referenceType;
    private final PointChangeReason reason;
    private final PointDelta delta;

    @Builder
    private PointHistory(
            Long id,
            Long userId,
            Long referenceId,
            PointReferenceType referenceType,
            PointChangeReason reason,
            PointDelta delta
    ) {
        this.id = id;
        this.userId = userId;
        this.referenceId = referenceId;
        this.referenceType = referenceType;
        this.reason = reason;
        this.delta = delta;
    }

    public static PointHistory of(
            Long toUserId,
            Long referenceId,
            PointReferenceType referenceType,
            PointChangeReason reason,
            PointDelta delta
    ) {
        return new PointHistory(null, toUserId, referenceId, referenceType, reason, delta);
    }

    /** 참조 */

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public String getReferenceTypeValue() {
        return referenceType.name();
    }

    public String getReasonValue() {
        return reason.name();
    }

    public int getDeltaValue() {
        return delta.value();
    }
}