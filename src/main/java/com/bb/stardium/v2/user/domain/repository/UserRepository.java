package com.bb.stardium.v2.user.domain.repository;

import com.bb.stardium.v2.user.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}
