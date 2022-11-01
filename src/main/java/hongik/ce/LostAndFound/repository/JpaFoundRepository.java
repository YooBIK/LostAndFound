package hongik.ce.LostAndFound.repository;

import hongik.ce.LostAndFound.domain.entity.Category;
import hongik.ce.LostAndFound.domain.entity.Found;
import hongik.ce.LostAndFound.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaFoundRepository extends JpaRepository<Found,Long> {
    List<Found> findAllByUser(User user);
}
