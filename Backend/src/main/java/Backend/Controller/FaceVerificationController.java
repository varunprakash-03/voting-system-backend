package Backend.Controller;


import Backend.Model.Voter;
import Backend.Repository.VoterRepository;
import Backend.Service.AiFaceService;
import Backend.Service.FaceVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/face-verification")
public class FaceVerificationController {

    @Autowired
    private FaceVerificationService faceVerificationService;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private AiFaceService aiFaceService;
    @PostMapping("/verify")
    public ResponseEntity<?> verifyFace(
            @RequestParam("image") MultipartFile image,
            @RequestParam("voterId") String voterId
    ) throws IOException {

        Voter voter = voterRepository.findByVoterId(voterId)
                .orElseThrow(() -> new RuntimeException("Voter not found"));

        Object storedEmbedding = voter.getFaceEmbedding();

        Map response = aiFaceService.verifyFace(image.getBytes(), storedEmbedding);

        boolean verified = (Boolean) response.get("verified");

        if (verified) {
            voter.setFaceVerified(true);
            voterRepository.save(voter);
        }

        return ResponseEntity.ok(response);
    }
}
