package com.noname.domain.qna.domain;

public interface Votable {

    void validateVotable(Long userId);

    Long getAuthorId();
}
