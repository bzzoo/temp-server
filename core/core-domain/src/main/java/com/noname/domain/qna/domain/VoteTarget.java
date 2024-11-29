package com.noname.domain.qna.domain;

import lombok.Getter;

@Getter
public final class VoteTarget {

    private final Long id;
    private final VoteTargetType type;

    public VoteTarget(Long targetId, VoteTargetType type) {
        this.id = targetId;
        this.type = type;
    }

    public static VoteTargetType of(String type) {
        return VoteTargetType.valueOf(type);
    }

    public String getTargetTypeValue() {
        return this.type.name();
    }
}
