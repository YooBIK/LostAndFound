package hongik.ce.LostAndFound.repository;

<<<<<<< Updated upstream
import lombok.RequiredArgsConstructor;
import org.apache.catalina.session.PersistentManager;

@RequiredArgsConstructor
public class JpaLostRepository {

    private final PersistentManager em;
=======
import hongik.ce.LostAndFound.domain.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLostRepository extends JpaRepository<Lost,Long> {

>>>>>>> Stashed changes
}
