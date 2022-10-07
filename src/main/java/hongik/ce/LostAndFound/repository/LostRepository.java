package hongik.ce.LostAndFound.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LostRepository {

    public final JdbcTemplate jdbcTemplate;

}
