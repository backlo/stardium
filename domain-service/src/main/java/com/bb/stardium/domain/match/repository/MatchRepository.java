package com.bb.stardium.domain.match.repository;

import com.bb.stardium.domain.match.Match;
import com.bb.stardium.domain.match.MatchId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, MatchId> {
    Page<Match> findAllByPlayerId(Pageable pageable, Long id);

    List<Match> findAllByClubId(Long id);
}
