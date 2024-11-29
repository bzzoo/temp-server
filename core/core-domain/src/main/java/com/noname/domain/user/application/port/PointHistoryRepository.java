package com.noname.domain.user.application.port;

import com.noname.domain.user.domain.PointHistory;

public interface PointHistoryRepository {

    void save(PointHistory pointHistory);
}
