package hongik.ce.LostAndFound.repository;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.session.PersistentManager;

@RequiredArgsConstructor
public class JpaLostRepository {

    private final PersistentManager em;
}
