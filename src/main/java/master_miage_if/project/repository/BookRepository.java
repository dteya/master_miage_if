package master_miage_if.project.repository;

import master_miage_if.project.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    BookEntity findByName(String name);
}
