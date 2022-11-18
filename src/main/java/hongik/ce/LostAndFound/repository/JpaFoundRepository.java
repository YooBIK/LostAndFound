package hongik.ce.LostAndFound.repository;

import hongik.ce.LostAndFound.domain.dto.found.FoundListByLocationRes;
import hongik.ce.LostAndFound.domain.dto.found.list.FoundListRes;
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

    @Query(value = "select new hongik.ce.LostAndFound.domain.dto.found.FoundListByLocationRes(f.lost_location, count(f)) from Found f group by f.lost_location")
    List<FoundListByLocationRes> countAllByLocation();


    @Query(value = "select f from Found f where f.lost_location =:location")
    List<Found> findAllByLostLocation(@Param(value = "location") String location);
}