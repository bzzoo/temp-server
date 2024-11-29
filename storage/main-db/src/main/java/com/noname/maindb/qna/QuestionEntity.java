package com.noname.maindb.qna;

import com.noname.domain.qna.domain.Question;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "questions")
@Entity
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long authorId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer seasonPoint;

    @Column
    private Long acceptedAnswerId;

    public QuestionEntity(Long id, Long authorId, Long acceptedAnswerId, String title, String content, String status, Integer seasonPoint) {
        this.id = id;
        this.authorId = authorId;
        this.acceptedAnswerId = acceptedAnswerId;
        this.title = title;
        this.content = content;
        this.status = status;
        this.seasonPoint = seasonPoint;
    }

    public static QuestionEntity from(Question question) {
        return new QuestionEntity(
                question.getId(),
                question.getAuthorId(),
                question.getAcceptedAnswerId(),
                question.getTitle(),
                question.getContent(),
                question.getStatus(),
                question.getSeasonPoint()
        );
    }
}
