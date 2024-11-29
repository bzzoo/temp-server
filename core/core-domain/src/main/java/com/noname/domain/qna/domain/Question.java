package com.noname.domain.qna.domain;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Question {

    private final Long id;
    private final Long authorId;
    private Long acceptedAnswerId;
    private TagIds tagIds;
    private QuestionTitle title;
    private QuestionContent content;
    private QuestionStatus status;
    private QuestionSeasonPoint seasonPoint;

    @Builder
    private Question(
            Long id,
            Long authorId,
            Long acceptedAnswerId,
            TagIds tagIds,
            QuestionTitle title,
            QuestionContent content,
            QuestionStatus status,
            QuestionSeasonPoint seasonPoint
    ) {
        this.id = id;
        this.authorId = authorId;
        this.acceptedAnswerId = acceptedAnswerId;
        this.tagIds = tagIds;
        this.title = title;
        this.content = content;
        this.status = status;
        this.seasonPoint = seasonPoint;
    }

    /**
     * 상태 변경
     */

    public static Question generate(
            Long authorId,
            QuestionTitle title,
            QuestionContent content,
            TagIds tagIds
    ) {
        return Question.builder()
                .authorId(authorId)
                .title(title)
                .content(content)
                .tagIds(tagIds)
                .status(QuestionStatus.OPEN)
                .seasonPoint(QuestionSeasonPoint.zero())
                .build();
    }

    public void edit(Long authorId, QuestionTitle title, QuestionContent content, TagIds tagIds) {
        validateAuthor(authorId);
        status.validateCanUpdate();
        this.title = title;
        this.content = content;
        this.tagIds = tagIds;
    }

    public void adoptAnswer(long answerId, long authorId) {
        validateAuthor(authorId);
        status.validateCanAcceptAnswer();
        this.acceptedAnswerId = answerId;
    }

    public void close(Long authorId) {
        validateAuthor(authorId);
        status.validateCanClose();
        this.status = QuestionStatus.CLOSED;

    }

    public void delete(Long authorId) {
        validateAuthor(authorId);
        this.status = QuestionStatus.DELETED;
    }

    public void editSeasonPoint(Long authorId, QuestionSeasonPoint seasonPoint) {
        validateAuthor(authorId);
        this.seasonPoint = seasonPoint;
    }

    /**
     * 상태 판별
     */

    public boolean hasAlreadyAdoptedAnswer() {
        return this.acceptedAnswerId != null;
    }

    public void checkAnswerPosting() {
        this.status.validateCanAddAnswer();
    }

    public boolean isOwner(Long userId) {
        return this.authorId.equals(userId);
    }

    public void validateVotable(Long userId) {
        if (isOwner(userId)) {
            throw new IllegalArgumentException("질문 작성자는 투표할 수 없습니다.");
        }
        this.status.validateCanVote();
    }

    /**
     * 내부 메서드
     */

    private void validateAuthor(Long authorId) {
        if (!this.authorId.equals(authorId)) {
            throw new IllegalArgumentException("작성자가 아닙니다.");
        }
    }

    /**
     * 참조
     */

    public String getTitle() {
        return title.getValue();
    }

    public String getContent() {
        return content.getValue();
    }

    public String getStatus() {
        return status.name();
    }

    public int getSeasonPoint() {
        return seasonPoint.getValue();
    }

    public Set<Long> getTagIdValues() {
        return tagIds.getTagIds();
    }

}
