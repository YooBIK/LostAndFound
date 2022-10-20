package hongik.ce.LostAndFound.repository;

import hongik.ce.LostAndFound.domain.entity.Category;
import hongik.ce.LostAndFound.domain.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaLostRepository extends JpaRepository<Lost,Long> {


    Lost findByLostId(Long lostId);

    @Modifying
    @Query(value = "UPDATE Lost l SET l.hit = l.hit+1 WHERE l.lostId = :lostId")
    int updateHit(@Param(value = "lostId") Long lostId);

}
