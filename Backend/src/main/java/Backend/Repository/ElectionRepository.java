package Backend.Repository;

import Backend.Model.Election;
import Backend.Model.ElectionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ElectionRepository extends JpaRepository<Election, Long> {


    List<Election> findByStatus(ElectionStatus status);

    Optional<Election> findFirstByStatus(ElectionStatus status);

}
