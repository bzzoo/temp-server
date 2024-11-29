package com.noname.domain.user.application.service;

import com.noname.domain.qna.domain.Answer;
import com.noname.domain.common.LoginUser;
import com.noname.domain.user.domain.PointChangeReason;
import com.noname.domain.user.domain.PointDelta;
import com.noname.domain.user.domain.PointReferenceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PointUpdateManager {

    private final PointChangeRecorder pointChangeRecorder;

    public void grantQuestionPostPoints(Long userId, Long questionId) {
        pointChangeRecorder.record(
                userId,
                questionId,
                PointReferenceType.QUESTION,
                PointChangeReason.QUESTION_POST,
                PointDelta.from(PointChangeReason.QUESTION_POST)
        );
    }

    public void grantAnswerPostPoints(Long userId, Long answerId) {
        pointChangeRecorder.record(
                userId,
                answerId,
                PointReferenceType.ANSWER,
                PointChangeReason.ANSWER_POST,
                PointDelta.from(PointChangeReason.ANSWER_POST)
        );
    }

    public void revokeAnswerDeletePoints(Long userId, Long answerId) {
        pointChangeRecorder.record(
                userId,
                answerId,
                PointReferenceType.ANSWER,
                PointChangeReason.ANSWER_DELETE,
                PointDelta.from(PointChangeReason.ANSWER_DELETE)
        );
    }

    public void revokeQuestionDeletePoints(Long userId, Long questionId) {
        pointChangeRecorder.record(
                userId,
                questionId,
                PointReferenceType.QUESTION,
                PointChangeReason.QUESTION_DELETE,
                PointDelta.from(PointChangeReason.QUESTION_DELETE)
        );
    }

    public void grantAnswerAcceptancePoints(Answer answer, Long questionId, LoginUser user) {
        pointChangeRecorder.record(
                answer.getAuthorId(),
                questionId,
                PointReferenceType.ANSWER,
                PointChangeReason.ANSWER_ACCEPTED,
                PointDelta.from(PointChangeReason.ANSWER_ACCEPTED)
        );

        pointChangeRecorder.record(
                user.id(),
                questionId,
                PointReferenceType.ANSWER,
                PointChangeReason.QUESTION_ACCEPTED,
                PointDelta.from(PointChangeReason.QUESTION_ACCEPTED)
        );
    }

    public void revokeAnswerAcceptancePoints(Answer previousAnswer, Long questionId) {
        pointChangeRecorder.record(
                previousAnswer.getAuthorId(),
                questionId,
                PointReferenceType.ANSWER,
                PointChangeReason.ANSWER_UNACCEPTED,
                PointDelta.from(PointChangeReason.ANSWER_UNACCEPTED)
        );
    }

    public void grantQuestionUpVotePoints(Long questionAuthorId, Long questionId) {
        pointChangeRecorder.record(
                questionAuthorId,
                questionId,
                PointReferenceType.QUESTION,
                PointChangeReason.QUESTION_UPVOTED,
                PointDelta.from(PointChangeReason.QUESTION_UPVOTED)
        );
    }

    public void grantAnswerUpVotePoints(Long answerAuthorId, Long answerId) {
        pointChangeRecorder.record(
                answerAuthorId,
                answerId,
                PointReferenceType.ANSWER,
                PointChangeReason.ANSWER_UPVOTED,
                PointDelta.from(PointChangeReason.ANSWER_UPVOTED)
        );
    }

    public void grantQuestionDownVotePoints(Long questionAuthorId, Long questionId) {
        pointChangeRecorder.record(
                questionAuthorId,
                questionId,
                PointReferenceType.QUESTION,
                PointChangeReason.QUESTION_DOWNVOTED,
                PointDelta.from(PointChangeReason.QUESTION_DOWNVOTED)
        );
    }

    public void grantAnswerDownVotePoints(Long answerAuthorId, Long answerId) {
        pointChangeRecorder.record(
                answerAuthorId,
                answerId,
                PointReferenceType.ANSWER,
                PointChangeReason.ANSWER_DOWNVOTED,
                PointDelta.from(PointChangeReason.ANSWER_DOWNVOTED)
        );
    }
}

