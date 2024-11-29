package com.noname.maindb.qna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteJpaRepository extends JpaRepository<VoteEntity, Long> {
    VoteEntity findByUserIdAndTargetId(Long userId, Long targetId);
    Optional<VoteEntity> findByTargetIdAndTargetTypeAndUserId(Long targetId, String targetType, Long userId);
}
