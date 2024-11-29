package com.noname.domain.qna.application.implementation;

import com.noname.domain.common.LoginUser;
import com.noname.domain.qna.application.port.VoteRepository;
import com.noname.domain.qna.domain.Votable;
import com.noname.domain.qna.domain.Vote;
import com.noname.domain.qna.domain.VoteTarget;
import com.noname.domain.qna.domain.VoteType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class VoteProcessor {
    private final VoteRepository voteRepository;

    @Transactional
    public void upvote(LoginUser user, Votable votable, VoteTarget target) {
        ensure(user, target);
        votable.validateVotable(user.id());
        Vote vote = Vote.of(user.id(), target, VoteType.UPVOTE);
        voteRepository.save(vote);
    }

    @Transactional
    public void downvote(LoginUser user, Votable votable, VoteTarget target){
        ensure(user,target);
        votable.validateVotable(user.id());
        Vote vote = Vote.of(user.id(), target, VoteType.DOWNVOTE);
        voteRepository.save(vote);
    }

    private void ensure(LoginUser user, VoteTarget target) {
        voteRepository
                .findByTargetIdAndTargetTypeAndUserId(target, user.id())
                .ifPresent(vote -> {
                    throw new IllegalStateException("이미 투표를 진행했습니다.");
                });
    }
}
