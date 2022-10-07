package hongik.ce.LostAndFound.repository;

<<<<<<< Updated upstream
import lombok.RequiredArgsConstructor;
import org.apache.catalina.session.PersistentManager;

@RequiredArgsConstructor
public class JpaUserRepository{

    private final PersistentManager em;

=======
import hongik.ce.LostAndFound.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User,Long> {
>>>>>>> Stashed changes
}
