package hongik.ce.LostAndFound.repository;

import hongik.ce.LostAndFound.domain.entity.Category;
import hongik.ce.LostAndFound.domain.entity.Found;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFoundRepository extends JpaRepository<Found,Long> {

}
