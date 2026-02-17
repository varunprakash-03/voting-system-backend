package Backend.Controller;


import Backend.Model.Voter;
import Backend.Repository.VoterRepository;
import Backend.Service.AiFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/face")
public class FaceRegisterController {


    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private AiFaceService aiFaceService;

    @PostMapping("/register")
    public ResponseEntity<?> registerFace(
            @RequestParam("image") MultipartFile image,
            @RequestParam("voterId") String voterId
    ) throws IOException {

        Voter voter = voterRepository.findByVoterId(voterId)
                .orElseThrow(() -> new RuntimeException("Voter not found"));

        // 🔥 Call AI service
        Map response = aiFaceService.registerFace(image.getBytes());

        Object embedding = response.get("embedding");

        voter.setFaceImage(image.getBytes());
        voter.setFaceRegister(true);

        // 🔥 Save embedding as JSON string
        voter.setFaceEmbedding(embedding.toString());

        voterRepository.save(voter);

        return ResponseEntity.ok("Face registered successfully");
    }
}
