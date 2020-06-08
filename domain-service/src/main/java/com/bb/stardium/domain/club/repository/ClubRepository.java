package com.bb.stardium.domain.club.repository;

import com.bb.stardium.domain.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
//    TODO : player로 찾는 걸로 수정
//    List<Room> findByPlayersEmail(final String email);
//
//    List<Room> findAllByAddressSectionOrderByStartTimeAsc(final String section);
//
//    List<Room> findAllByTitleContaining(final String searchKeyword);
}
