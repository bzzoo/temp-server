package com.noname.domain.qna.application.port;

import com.noname.domain.qna.domain.Vote;
import com.noname.domain.qna.domain.VoteTarget;
import java.util.Optional;

public interface VoteRepository {

    void save(Vote vote);

    void delete(Vote existingVote);

    Vote findByUserIdAndTargetId(Long targetId, Long userId);

    Optional<Vote> findByTargetIdAndTargetTypeAndUserId(VoteTarget target, Long userId);
}
