package hongik.ce.LostAndFound.repository;

import hongik.ce.LostAndFound.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<Category,String> {
    Category findByCategory(String Category);
}
