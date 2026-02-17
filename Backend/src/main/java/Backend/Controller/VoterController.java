package Backend.Controller;


import Backend.DTO.VoterStatusResponse;
import Backend.Model.Candidate;
import Backend.Model.Election;
import Backend.Model.ElectionStatus;
import Backend.Model.Voter;
import Backend.Repository.CandidateRepository;
import Backend.Repository.ElectionRepository;
import Backend.Repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voter")
public class VoterController {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    

    @GetMapping("/status")
    public ResponseEntity<VoterStatusResponse> getStatus(
            @RequestParam String voterId
    ) {

        Voter voter = voterRepository.findByVoterId(voterId)
                .orElseThrow(() -> new RuntimeException("Voter not found"));

        VoterStatusResponse response = new VoterStatusResponse(
                voter.isFaceRegister(),
                voter.isFaceVerified(),
                voter.isHasVoted()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/active-elections")
    public ResponseEntity<?> getActiveElections(@RequestParam String voterId) {

        Voter voter = voterRepository.findByVoterId(voterId)
                .orElseThrow(() -> new RuntimeException("Voter not found"));

        if (!voter.isFaceVerified()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Face not verified");
        }

        List<Election> activeElections =
                electionRepository.findByStatus(ElectionStatus.ACTIVE);

        if (activeElections.isEmpty()) {
            throw new RuntimeException("No active election");
        }

        Election election = activeElections.get(0); // Assuming only one ACTIVE


        return ResponseEntity.ok(election);
    }

    @PostMapping("/{candidateId}")
    public String castVote(@PathVariable Long candidateId,
                           @RequestParam String voterId) {

        Voter voter = voterRepository.findByVoterId(voterId)
                .orElseThrow();

        if (!voter.isFaceVerified()) {
            throw new RuntimeException("Face not verified");
        }

        if (voter.isHasVoted()) {
            throw new RuntimeException("Already voted");
        }

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow();

        candidate.setVoteCount(candidate.getVoteCount() + 1);
        candidateRepository.save(candidate);

        voter.setHasVoted(true);
        voterRepository.save(voter);

        return "Vote Cast Successfully";
    }
}


