package com.noname.domain.user.application.port;

import com.noname.domain.user.domain.User;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(Long id);
}
