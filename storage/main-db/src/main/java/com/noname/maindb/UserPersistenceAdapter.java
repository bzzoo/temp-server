package com.noname.maindb;

import com.noname.domain.user.application.port.UserRepository;
import com.noname.domain.user.domain.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserPersistenceAdapter implements UserRepository {

    @Override
    public void save(User user) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }
}
