package Backend.Controller;


import Backend.DTO.CandidateRequest;
import Backend.Model.Candidate;
import Backend.Model.Election;
import Backend.Model.ElectionStatus;
import Backend.Model.Party;
import Backend.Repository.CandidateRepository;
import Backend.Repository.ElectionRepository;
import Backend.Repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    // CREATE ELECTION
    @PostMapping("/election")
    public ResponseEntity<?> createElection(@RequestBody Election election) {

        // Check if an ACTIVE election exists
        electionRepository
                .findFirstByStatus(ElectionStatus.ACTIVE)
                .ifPresent(existing -> {
                    existing.setStatus(ElectionStatus.COMPLETED);
                    electionRepository.save(existing);
                });

        // Set new election as ACTIVE
        election.setStatus(ElectionStatus.ACTIVE);
        electionRepository.save(election);

        return ResponseEntity.ok("Election created successfully");
    }


    // CREATE PARTY (linked to election)
    @PostMapping("/party")
    public ResponseEntity<?> createParty(@RequestParam Long electionId,
                                         @RequestBody Party party) {

        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new RuntimeException("Election not found"));

        party.setElection(election);
        partyRepository.save(party);

        return ResponseEntity.ok("Party created successfully");
    }

    // ADD CANDIDATE (proper relational mapping)
    @PostMapping("/candidate")
    public ResponseEntity<?> createCandidate(@RequestBody CandidateRequest request) {

        Election election = electionRepository.findById(request.getElectionId())
                .orElseThrow(() -> new RuntimeException("Election not found"));

        Party party = partyRepository.findById(request.getPartyId())
                .orElseThrow(() -> new RuntimeException("Party not found"));

        Candidate candidate = new Candidate();
        candidate.setCandidateName(request.getName());
        candidate.setElection(election);
        candidate.setParty(party);
        candidate.setVoteCount(0);

        candidateRepository.save(candidate);

        return ResponseEntity.ok("Candidate added successfully");
    }

    @GetMapping("/results/{electionId}")
    public List<Candidate> getResults(@PathVariable Long electionId) {
        return candidateRepository.findByElection_Id(electionId);
    }

}
