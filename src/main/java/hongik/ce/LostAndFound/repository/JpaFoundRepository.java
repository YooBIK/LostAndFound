package hongik.ce.LostAndFound.repository;

import hongik.ce.LostAndFound.domain.entity.Category;
import hongik.ce.LostAndFound.domain.entity.Found;
import hongik.ce.LostAndFound.domain.entity.Lost;
import hongik.ce.LostAndFound.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaFoundRepository extends JpaRepository<Found,Long> {


    Found findByFoundId(Long FoundId);

    @Modifying
    @Query(value = "UPDATE Found l SET l.hit = l.hit+1 WHERE l.foundId = :foundId")
    int updateHit(@Param(value = "foundId") Long foundId);
    List<Found> findAllByUser(User user);
}