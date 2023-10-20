package master_miage_if.project.repository;

import master_miage_if.project.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, Integer> {
    List<StudentEntity> findAll();

    Optional<StudentEntity> findById(UUID id);

    List<StudentEntity> findByLastName(String name);

    List<StudentEntity> findByAgeLessThan(int age);

    StudentEntity findByEmail(String email);

}
