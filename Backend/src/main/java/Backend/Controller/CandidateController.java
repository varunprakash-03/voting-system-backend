package Backend.Controller;

import Backend.Model.Candidate;
import Backend.Model.Election;
import Backend.Model.ElectionStatus;
import Backend.Repository.CandidateRepository;
import Backend.Repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CandidateController {


    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @GetMapping("/candidates")
    public List<Candidate> getActiveElectionCandidates() {

        Election activeElection = electionRepository
                .findFirstByStatus(ElectionStatus.ACTIVE)
                .orElseThrow(() -> new RuntimeException("No active election"));

        return candidateRepository.findByElection_Id(activeElection.getId());
    }

}
