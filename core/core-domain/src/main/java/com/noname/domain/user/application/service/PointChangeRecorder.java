package com.noname.domain.user.application.service;

import com.noname.domain.user.application.port.PointHistoryRepository;
import com.noname.domain.user.domain.PointChangeReason;
import com.noname.domain.user.domain.PointDelta;
import com.noname.domain.user.domain.PointHistory;
import com.noname.domain.user.domain.PointReferenceType;
import com.noname.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class PointChangeRecorder {

    private final UserReader userReader;
    private final UserWriter userWriter;
    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public void record(Long toUserId, Long referenceId, PointReferenceType referenceType,
            PointChangeReason reason, PointDelta delta
    ) {
        User user = userReader.getById(toUserId);
        user.applyPoint(delta.value());
        PointHistory pointHistory = PointHistory.of(toUserId, referenceId, referenceType, reason, delta);

        pointHistoryRepository.save(pointHistory);
        userWriter.save(user);
    }
}
