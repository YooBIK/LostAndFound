package hongik.ce.LostAndFound.repository;

import hongik.ce.LostAndFound.domain.entity.FoundComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaFoundCommentRepository extends JpaRepository<FoundComment,Long> {
}
