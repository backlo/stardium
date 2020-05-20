package com.bb.stardium.v2.user.service;

import com.bb.stardium.v2.user.domain.User;
import com.bb.stardium.v2.user.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getAllUser() {
        Flux<User> users = userRepository.findAll();
    }
}
