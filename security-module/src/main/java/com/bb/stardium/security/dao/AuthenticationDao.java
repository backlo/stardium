package com.bb.stardium.security.dao;

import com.bb.stardium.security.model.LoginPlayer;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class AuthenticationDao {

    private static final String FIND_PLAYER_QUERY = "SELECT NEW com.bb.stardium.security.model.LoginPlayer(p.email, p.password, p.role) FROM Player p WHERE p.email = :email";

    @PersistenceContext
    private final EntityManager em;

    public AuthenticationDao(EntityManager em) {
        this.em = em;
    }

    public LoginPlayer findByEmail(String email) throws QuerySyntaxException {
        TypedQuery<LoginPlayer> query =
                em.createQuery(FIND_PLAYER_QUERY, LoginPlayer.class)
                .setParameter("email", email);

        return query.getSingleResult();
    }
}
