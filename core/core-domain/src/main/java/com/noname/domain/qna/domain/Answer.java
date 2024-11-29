package com.noname.domain.qna.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Answer {

    private final Long id;
    private final Long questionId;
    private final Long authorId;
    private AnswerContent content;
    private AnswerStatus status;

    @Builder
    private Answer(Long id, Long authorId, Long questionId, AnswerContent content) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.authorId = authorId;
        this.status = AnswerStatus.ACTIVE;
    }

    /**
     * 상태 변경
     */

    public static Answer generate(Long userId, Long questionId, AnswerContent content) {
        return Answer.builder()
                .authorId(userId)
                .questionId(questionId)
                .build();
    }

    public void updateContent(Long userId, AnswerContent newContent) {
        validateAuthor(userId);
        status.validateCanUpdate();
        this.content = newContent;
    }

    public void accept() {
        status.validateCanAccept();
        this.status = AnswerStatus.ACCEPTED;
    }

    public void unaccept() {
        this.status = AnswerStatus.ACTIVE;
    }

    public void delete(Long authorId) {
        this.status.validateCanDelete();
        validateAuthor(authorId);
        this.status = AnswerStatus.DELETED;
    }

    /**
     * 상태 판별
     */

    public boolean isOwner(Long userId) {
        return this.authorId.equals(userId);
    }

    public void validateVotable(Long userId) {
        if (isOwner(userId)) {
            throw new IllegalArgumentException("답변자는 본인 답변에 투표할 수 없습니다.");
        }
        this.status.validateCanVote();
    }

    /**
     * 참조
     */


    public String getContent() {
        return content.getValue();
    }

    public String getStatus() {
        return status.name();
    }

    /**
     * 내부 메서드
     */

    private void validateAuthor(Long authorId) {
        if (!this.authorId.equals(authorId)) {
            throw new IllegalArgumentException("You can't edit this answer");
        }
    }
}
