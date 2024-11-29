package com.noname.maindb.qna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TagJpaRepository extends JpaRepository<TagEntity, Long> {
    List<TagEntity> findAllByNameIn(Set<String> names);
}
