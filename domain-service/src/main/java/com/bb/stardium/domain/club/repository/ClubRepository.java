package com.bb.stardium.domain.club.repository;

import com.bb.stardium.domain.club.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Page<Club> findAllByStartTimeIsAfter(LocalDateTime startTime, Pageable pageable);

    Page<Club> findAllByAddress_Section(String section, Pageable pageable);

    List<Club> findAllByTitleContainingOrIntroContaining(String title, String intro);
}
