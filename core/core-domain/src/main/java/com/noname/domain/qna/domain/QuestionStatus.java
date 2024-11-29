package com.noname.domain.qna.domain;

public enum QuestionStatus {
    OPEN {
        @Override
        public void validateCanUpdate() {
        }

        @Override
        public void validateCanAcceptAnswer() {
        }

        @Override
        public void validateCanClose() {
        }

        @Override
        public void validateCanAddAnswer() {
        }

        @Override
        public void validateCanVote() {
        }

        @Override
        public void validateCanComment() {
        }
    },
    LOCKED {
        @Override
        public void validateCanUpdate() {
            throw new IllegalArgumentException("잠긴 질문은 수정할 수 없습니다.");
        }

        @Override
        public void validateCanAcceptAnswer() {
            throw new IllegalArgumentException("잠긴 질문에서는 답변을 채택할 수 없습니다.");
        }

        @Override
        public void validateCanClose() {
            throw new IllegalArgumentException("잠긴 질문은 닫을 수 없습니다.");
        }

        @Override
        public void validateCanAddAnswer() {
            throw new IllegalArgumentException("잠긴 질문에는 답변을 추가할 수 없습니다.");
        }

        @Override
        public void validateCanVote() {
            throw new IllegalArgumentException("잠긴 질문에는 투표할 수 없습니다.");
        }

        @Override
        public void validateCanComment() {
            throw new IllegalArgumentException("잠긴 질문에는 댓글을 추가할 수 없습니다.");
        }
    },
    CLOSED {
        @Override
        public void validateCanUpdate() {
            throw new IllegalArgumentException("닫힌 질문은 수정할 수 없습니다.");
        }

        @Override
        public void validateCanAcceptAnswer() {
            throw new IllegalArgumentException("닫힌 질문에서는 답변을 채택할 수 없습니다.");
        }

        @Override
        public void validateCanClose() {
            throw new IllegalArgumentException("이미 닫힌 질문입니다.");
        }

        @Override
        public void validateCanAddAnswer() {
            throw new IllegalArgumentException("닫힌 질문에는 답변을 추가할 수 없습니다.");
        }

        @Override
        public void validateCanVote() {
        }

        @Override
        public void validateCanComment() {
        }
    },
    DELETED {
        @Override
        public void validateCanUpdate() {
            throw new IllegalArgumentException("삭제된 질문은 수정할 수 없습니다.");
        }

        @Override
        public void validateCanAcceptAnswer() {
            throw new IllegalArgumentException("삭제된 질문에서는 답변을 채택할 수 없습니다.");
        }

        @Override
        public void validateCanClose() {
            throw new IllegalArgumentException("삭제된 질문은 닫을 수 없습니다.");
        }

        @Override
        public void validateCanAddAnswer() {
            throw new IllegalArgumentException("삭제된 질문에는 답변을 추가할 수 없습니다.");
        }

        @Override
        public void validateCanVote() {
            throw new IllegalArgumentException("삭제된 질문에는 투표할 수 없습니다.");
        }

        @Override
        public void validateCanComment() {
            throw new IllegalArgumentException("삭제된 질문에는 댓글을 추가할 수 없습니다.");
        }
    },
    ON_HOLD {
        @Override
        public void validateCanUpdate() {
        }

        @Override
        public void validateCanAcceptAnswer() {
            throw new IllegalArgumentException("보류된 질문에서는 답변을 채택할 수 없습니다.");
        }

        @Override
        public void validateCanClose() {
            throw new IllegalArgumentException("보류된 질문은 닫을 수 없습니다.");
        }

        @Override
        public void validateCanAddAnswer() {
            throw new IllegalArgumentException("보류된 질문에는 답변을 추가할 수 없습니다.");
        }

        @Override
        public void validateCanVote() {
        }

        @Override
        public void validateCanComment() {
        }
    },
    PROTECTED {
        @Override
        public void validateCanUpdate() {
        }

        @Override
        public void validateCanAcceptAnswer() {
        }

        @Override
        public void validateCanClose() {
        }

        @Override
        public void validateCanAddAnswer() {
        }

        @Override
        public void validateCanVote() {
        }

        @Override
        public void validateCanComment() {
        }
    };

    public abstract void validateCanUpdate();

    public abstract void validateCanAcceptAnswer();

    public abstract void validateCanClose();

    public abstract void validateCanAddAnswer();

    public abstract void validateCanVote();

    public abstract void validateCanComment();
}
