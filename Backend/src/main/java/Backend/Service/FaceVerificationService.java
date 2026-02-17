package Backend.Service;

import Backend.Model.Voter;
import Backend.Repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FaceVerificationService {

    @Autowired
    private VoterRepository voterRepository;

    public boolean verifyFace(MultipartFile image, String voterId) {

        // 1️⃣ Find voter
        Voter voter = voterRepository.findByVoterId(voterId)
                .orElseThrow(() -> new RuntimeException("Voter not found"));

        // 2️⃣ (Dummy logic for now)
        // Later: send image to AI model
        // Now: just accept and mark verified

        voter.setFaceVerified(true);
        voterRepository.save(voter);

        // Optional debug
        System.out.println("Face verified for voter: " + voterId);
        System.out.println("Received image size: " + image.getSize());

        return true;
    }
}
