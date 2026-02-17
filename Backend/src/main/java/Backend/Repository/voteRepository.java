package Backend.Repository;

import Backend.Model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface voteRepository extends JpaRepository<Vote,Long> {

}
