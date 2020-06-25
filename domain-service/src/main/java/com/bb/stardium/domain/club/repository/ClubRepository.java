package com.bb.stardium.domain.club.repository;

import com.bb.stardium.domain.club.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Page<Club> findAllByStartTimeIsAfter(LocalDateTime startTime, Pageable pageable);

//    List<Room> findAllByAddressSectionOrderByStartTimeAsc(final String section);
//
//    List<Room> findAllByTitleContaining(final String searchKeyword);
}
