package com.noname.maindb.qna;

import com.noname.domain.qna.domain.Tag;
import com.noname.domain.qna.domain.TagName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tags")
@Entity
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public TagEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TagEntity fromDomain(Tag tag) {
        return new TagEntity(tag.getId(), tag.getNameValue());
    }

    public Tag toDomain() {
        return Tag.builder()
                .id(id)
                .name(TagName.of(name))
                .build();
    }

    public Long toDomainId(){
        return this.id;
    }

}
