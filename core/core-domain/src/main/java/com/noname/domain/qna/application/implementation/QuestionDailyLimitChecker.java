package com.noname.domain.qna.application.implementation;

import com.noname.domain.common.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class QuestionDailyLimitChecker {

    public void check(LoginUser user) {
        decrementLimit();
    }

    public void decrementLimit() {
    }
}
