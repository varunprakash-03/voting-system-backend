package Backend.Repository;

import Backend.Model.Candidate;
import Backend.Model.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party,Long> {

    List<Party> findByElection_Id(Long electionId);

}
