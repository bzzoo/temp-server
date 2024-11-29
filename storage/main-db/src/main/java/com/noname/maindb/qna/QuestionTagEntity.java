package com.noname.maindb.qna;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "question_tags")
@Entity
public class QuestionTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long questionId;
    private Long tagId;

    public QuestionTagEntity(Long questionId, Long tagId) {
        this.questionId = questionId;
        this.tagId = tagId;
    }
}
