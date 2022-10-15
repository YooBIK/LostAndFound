package hongik.ce.LostAndFound.repository;

import hongik.ce.LostAndFound.domain.entity.Category;
import hongik.ce.LostAndFound.domain.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaLostRepository extends JpaRepository<Lost,Long> {
    List<Lost> findByCategory(Category category);


    @Query("select l from Lost l where l.category = :category AND l.date LIKE :yearMonth")
    List<Lost> findByCategoryAndYearMonth(@Param("category") Category category, @Param("yearMonth") String yearMonth);


    @Query("select l from Lost l where l.date LIKE :yearMonth")
    List<Lost> findByYearMonth(@Param("yearMonth") String yearMonth);
}
