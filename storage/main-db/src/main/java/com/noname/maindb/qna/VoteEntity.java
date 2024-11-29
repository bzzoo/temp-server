package com.noname.maindb.qna;

import com.noname.domain.qna.domain.Vote;
import com.noname.domain.qna.domain.VoteTarget;
import com.noname.domain.qna.domain.VoteType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "votes")
@Entity
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long targetId;

    @Column(nullable = false)
    private String targetType;

    @Column(nullable = false)
    private String voteType;

    public VoteEntity(Long id, Long userId, Long targetId, String targetType, String voteType) {
        this.id = id;
        this.userId = userId;
        this.targetId = targetId;
        this.targetType = targetType;
        this.voteType = voteType;
    }

    public static VoteEntity from(Vote vote) {
        return new VoteEntity(
                vote.getId(),
                vote.getUserId(),
                vote.getVoteTargetId(),
                vote.getVoteTargetTypeValue(),
                vote.getVoteType().name()
        );
    }

    public Vote toDomain() {
        return Vote.builder()
                .id(this.id)
                .userId(this.userId)
                .voteTarget(new VoteTarget(this.targetId, VoteTarget.of(this.targetType)))
                .voteType(VoteType.valueOf(this.voteType))
                .build();
    }
}
