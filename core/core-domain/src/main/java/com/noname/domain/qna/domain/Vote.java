package com.noname.domain.qna.domain;

import lombok.Builder;

public class Vote {

    private final Long id;
    private final Long userId;
    private final VoteTarget voteTarget;
    private final VoteType voteType;

    @Builder
    private Vote(
            Long id,
            Long userId,
            VoteTarget voteTarget,
            VoteType voteType
    ) {

        this.id = id;
        this.userId = userId;
        this.voteTarget = voteTarget;
        this.voteType = voteType;
    }

    public static Vote of(Long userId, VoteTarget target, VoteType type) {
        return Vote.builder()
                .userId(userId)
                .voteTarget(target)
                .voteType(type)
                .build();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public VoteTarget getVoteTarget() {
        return voteTarget;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public Long getVoteTargetId() { return voteTarget.getId();}

    public String getVoteTargetTypeValue() { return voteTarget.getTargetTypeValue();}

}

