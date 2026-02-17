package Backend.Repository;

import Backend.Model.Voter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter,Long> {
    Optional<Voter> findByVoterId(String voterId);
    boolean existsByVoterId(String voterId);
}
