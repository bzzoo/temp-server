package com.noname.domain.qna.application.port;

import com.noname.domain.qna.domain.TagNames;
import com.noname.domain.qna.domain.Tags;

public interface TagRepository {

    Tags saveAll(Tags tags);

    Tags findAllByNameIn(TagNames tagNames);
}
