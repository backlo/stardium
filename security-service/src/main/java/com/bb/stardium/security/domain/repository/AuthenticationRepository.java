package com.bb.stardium.security.domain.repository;

import com.bb.stardium.security.domain.LoginViewModel;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class AuthenticationRepository {

    private static final String FIND_PLAYER_QUERY = "SELECT NEW com.bb.stardium.security.domain.LoginViewModel(p.email, p.password) FROM Player p WHERE p.email = :email";

    @PersistenceContext
    private final EntityManager em;

    public AuthenticationRepository(EntityManager em) {
        this.em = em;
    }

    public LoginViewModel findByEmail(String email) throws QuerySyntaxException {
        TypedQuery<LoginViewModel> query =
                em.createQuery(FIND_PLAYER_QUERY, LoginViewModel.class)
                .setParameter("email", email);

        return query.getSingleResult();
    }
}
