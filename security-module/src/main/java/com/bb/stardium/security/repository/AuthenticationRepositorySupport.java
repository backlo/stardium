package com.bb.stardium.security.repository;

import com.bb.stardium.chat.domain.player.Player;
import com.bb.stardium.security.model.LoginPlayer;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.bb.stardium.chat.domain.player.QPlayer.player;


@Repository
public class AuthenticationRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public AuthenticationRepositorySupport(JPAQueryFactory queryFactory) {
        super(Player.class);
        this.queryFactory = queryFactory;
    }

    public LoginPlayer findByEmail(String email) throws QuerySyntaxException {
        return queryFactory.from(player)
                .select(Projections.constructor(LoginPlayer.class,
                        player.email,
                        player.password,
                        player.nickname,
                        player.profile,
                        player.role))
                .where(player.email.eq(email))
                .fetchOne();
    }
}
