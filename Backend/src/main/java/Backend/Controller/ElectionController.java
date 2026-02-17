package Backend.Controller;


import Backend.Model.Candidate;
import Backend.Model.Election;
import Backend.Model.ElectionStatus;
import Backend.Repository.CandidateRepository;
import Backend.Repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/elections")
public class ElectionController {


    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/active")
    public ResponseEntity<?> getActiveElection() {

        List<Election> activeElections =
                electionRepository.findByStatus(ElectionStatus.ACTIVE);

        if (activeElections.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("No active election");
        }

        return ResponseEntity.ok(activeElections);
    }


    @GetMapping("/{id}/candidates")
    public List<Candidate> getCandidates(@PathVariable Long id) {
        return candidateRepository.findByElection_Id(id);
    }
}
