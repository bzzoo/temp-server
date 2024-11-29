package com.noname.domain.qna.domain;

public enum AnswerStatus {
    ACTIVE {
        @Override
        public void validateCanUpdate() {}

        @Override
        public void validateCanVote() {}

        @Override
        public void validateCanAccept() {}

        @Override
        public void validateCanDelete() {
        }
    },
    ACCEPTED {
        @Override
        public void validateCanUpdate() {
            throw new IllegalArgumentException("채택된 답변은 수정할 수 없습니다.");
        }

        @Override
        public void validateCanVote() {}

        @Override
        public void validateCanAccept() {
            throw new IllegalArgumentException("이미 채택된 답변입니다.");
        }

        @Override
        public void validateCanDelete() {
            throw new IllegalArgumentException("채택된 답변은 삭제할 수 없습니다.");
        }
    },
    LOCKED {
        @Override
        public void validateCanUpdate() {
            throw new IllegalArgumentException("잠긴 답변은 수정할 수 없습니다.");
        }

        @Override
        public void validateCanVote() {}

        @Override
        public void validateCanAccept() {
            throw new IllegalArgumentException("잠긴 답변은 채택할 수 없습니다.");
        }

        @Override
        public void validateCanDelete() {
            throw new IllegalArgumentException("잠긴 답변은 삭제할 수 없습니다.");
        }
    },
    DELETED {
        @Override
        public void validateCanUpdate() {
            throw new IllegalArgumentException("삭제된 답변은 수정할 수 없습니다.");
        }

        @Override
        public void validateCanVote() {
            throw new IllegalArgumentException("삭제된 답변에는 투표할 수 없습니다.");
        }

        @Override
        public void validateCanAccept() {
            throw new IllegalArgumentException("삭제된 답변은 채택할 수 없습니다.");
        }

        @Override
        public void validateCanDelete() {
            throw new IllegalArgumentException("이미 삭제된 답변입니다.");
        }
    },
    PENDING {
        @Override
        public void validateCanUpdate() {}

        @Override
        public void validateCanVote() {
            throw new IllegalArgumentException("검토 중인 답변에는 투표할 수 없습니다.");
        }

        @Override
        public void validateCanAccept() {}

        @Override
        public void validateCanDelete() {}
    },
    INACTIVE {
        @Override
        public void validateCanUpdate() {
            throw new IllegalArgumentException("비활성화된 답변은 수정할 수 없습니다.");
        }

        @Override
        public void validateCanVote() {
            throw new IllegalArgumentException("비활성화된 답변에는 투표할 수 없습니다.");
        }

        @Override
        public void validateCanAccept() {
            throw new IllegalArgumentException("비활성화된 답변은 채택할 수 없습니다.");
        }

        @Override
        public void validateCanDelete() {
            throw new IllegalArgumentException("비활성화된 답변은 삭제할 수 없습니다.");
        }
    };

    public abstract void validateCanUpdate();
    public abstract void validateCanVote();
    public abstract void validateCanAccept();
    public abstract void validateCanDelete();
}
