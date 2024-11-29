package com.noname.maindb;

import com.noname.domain.user.application.port.PointHistoryRepository;
import com.noname.domain.user.domain.PointHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PointPersistenceAdapter implements PointHistoryRepository {

    @Override
    public void save(PointHistory pointHistory) {

    }
}
