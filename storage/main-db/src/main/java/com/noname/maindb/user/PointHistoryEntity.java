package com.noname.maindb.user;

import com.noname.domain.user.domain.PointChangeReason;
import com.noname.domain.user.domain.PointDelta;
import com.noname.domain.user.domain.PointHistory;
import com.noname.domain.user.domain.PointReferenceType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "point_histories")
@Entity
public class PointHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long referenceId;
    private String referenceType;
    private String reason;
    private int delta;

    public PointHistoryEntity(Long id, Long userId, Long referenceId, String referenceType, String reason, int delta) {
        this.id = id;
        this.userId = userId;
        this.referenceId = referenceId;
        this.referenceType = referenceType;
        this.reason = reason;
        this.delta = delta;
    }

    public static PointHistoryEntity from(PointHistory pointHistory) {
        return new PointHistoryEntity(
                pointHistory.getId(),
                pointHistory.getUserId(),
                pointHistory.getReferenceId(),
                pointHistory.getReferenceTypeValue(),
                pointHistory.getReasonValue(),
                pointHistory.getDeltaValue()
        );
    }

    public PointHistory toDomain() {
        return PointHistory.builder()
                .id(id)
                .userId(userId)
                .referenceId(referenceId)
                .referenceType(PointReferenceType.valueOf(referenceType))
                .reason(PointChangeReason.valueOf(reason))
                .delta(PointDelta.of(delta))
                .build();
    }
}
