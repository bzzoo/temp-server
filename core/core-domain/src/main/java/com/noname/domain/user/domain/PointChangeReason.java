package com.noname.domain.user.domain;

public enum PointChangeReason {
    QUESTION_POST(10, "질문 작성 (+10)"),
    QUESTION_DELETE(-10, "질문 삭제 (-10)"),
    QUESTION_UPVOTED(10, "질문 추천 받음 (+10)"),
    ANSWER_UPVOTED(10, "답변 추천 받음 (+10)"),
    ANSWER_ACCEPTED(15, "답변 채택됨 (+15)"),
    ANSWER_UNACCEPTED(-15, "답변 채택취소됨 (-15)"),
    QUESTION_ACCEPTED(15, "답변 채택함 (+15)"),
    SUGGESTED_EDIT_APPROVED(2, "편집 허용 받음 (+2)"),
    QUESTION_DOWNVOTED(-2, "질문 비추천 받음 (-2)"),
    ANSWER_DOWNVOTED(-2, "답변 비추천받음 (-2)"),
    GIVEN_DOWNVOTE(-1, "비추천함 (-1)"),
    BOUNTY_SET(0, "현상금 설정"),
    ANSWER_POST(2, "답변 작성" ),
    ANSWER_DELETE(-2, "답변 삭제" )
    ;

    private final int changePointValue;
    private final String description;

    PointChangeReason(int changePointValue, String description) {
        this.changePointValue = changePointValue;
        this.description = description;
    }

    public int getChangePointValue() {
        return changePointValue;
    }
}
