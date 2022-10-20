package hongik.ce.LostAndFound.repository;

import hongik.ce.LostAndFound.domain.entity.Lost;
import hongik.ce.LostAndFound.domain.entity.LostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaLostCommentRepository extends JpaRepository<LostComment,Long> {
    List<LostComment> findByLost_LostId(Long lostId);
}
