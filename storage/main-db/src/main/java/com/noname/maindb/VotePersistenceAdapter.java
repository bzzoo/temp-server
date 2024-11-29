package com.noname.maindb;

import com.noname.domain.qna.application.port.VoteRepository;
import com.noname.domain.qna.domain.Vote;
import com.noname.domain.qna.domain.VoteTarget;
import com.noname.maindb.qna.VoteEntity;
import com.noname.maindb.qna.VoteJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class VotePersistenceAdapter implements VoteRepository {

    private final VoteJpaRepository voteJpaRepository;


    public void save(Vote vote) {
        voteJpaRepository.save(VoteEntity.from(vote)).toDomain();
    }


    public void delete(Vote vote) {
        voteJpaRepository.delete(VoteEntity.from(vote));
    }


    public Vote findByUserIdAndTargetId(Long userId, Long targetId) {
        return voteJpaRepository.findByUserIdAndTargetId(userId, targetId).toDomain();
    }

    public Optional<Vote> findByTargetIdAndTargetTypeAndUserId(VoteTarget target, Long userId) {
        return voteJpaRepository
                .findByTargetIdAndTargetTypeAndUserId(
                        target.getId(),
                        target.getTargetTypeValue(),
                        userId)
                .map(VoteEntity::toDomain);
    }
}
