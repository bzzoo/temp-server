package com.noname.domain.user.application.service;

import com.noname.domain.user.application.port.UserRepository;
import com.noname.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserReader {

    private final UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("우저를 찾을 수 없습니다."));
    }
}
