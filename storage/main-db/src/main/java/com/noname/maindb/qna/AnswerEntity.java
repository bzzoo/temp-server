package com.noname.maindb.qna;

import com.noname.domain.qna.domain.Answer;
import com.noname.domain.qna.domain.AnswerContent;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "answers")
@Entity
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long questionId;

    @Column(nullable = false)
    private Long authorId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String status;

    public AnswerEntity(Long id, Long questionId, Long authorId, String content, String status) {
        this.id = id;
        this.questionId = questionId;
        this.authorId = authorId;
        this.content = content;
        this.status = status;
    }

    public static AnswerEntity from(Answer answer) {
        return new AnswerEntity(
                answer.getId(),
                answer.getQuestionId(),
                answer.getAuthorId(),
                answer.getContent(),
                answer.getStatus()
        );
    }

    public Answer toDomain() {
        return Answer.builder()
                .id(id)
                .authorId(authorId)
                .questionId(questionId)
                .content(new AnswerContent(content))
                .build();
    }
}
