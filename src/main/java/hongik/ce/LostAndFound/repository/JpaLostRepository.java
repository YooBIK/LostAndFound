package hongik.ce.LostAndFound.repository;

import hongik.ce.LostAndFound.domain.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLostRepository extends JpaRepository<Lost,Long> {


}
