package hongik.ce.LostAndFound.repository;

import hongik.ce.LostAndFound.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRepository extends JpaRepository<User,Long> {
    User findByStudentNumber(String sNum);

    @Query(value = "SELECT COUNT(*) " +
            "FROM User u " +
            "WHERE u.studentNumber = ?1")
    Long countByStudentNumber(String studentNumber);
}
