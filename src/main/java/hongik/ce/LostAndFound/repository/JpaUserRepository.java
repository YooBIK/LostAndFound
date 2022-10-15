package hongik.ce.LostAndFound.repository;

import hongik.ce.LostAndFound.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User,Long> {

    User findByUserId(Long userId);
    User findByStudentNumber(String studentNumber);
    boolean existsByStudentNumber(String studentNumber);

}
