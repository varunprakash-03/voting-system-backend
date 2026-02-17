package Backend.Service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class AiFaceService {

    private final WebClient webClient;

    public AiFaceService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://127.0.0.1:8000")
                .build();
    }

    // REGISTER FACE
    public Map registerFace(byte[] imageBytes) {

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new ByteArrayResource(imageBytes) {
            @Override
            public String getFilename() {
                return "face.jpg";
            }
        });

        return webClient.post()
                .uri("/register-face")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(builder.build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    // VERIFY FACE
    public Map verifyFace(byte[] imageBytes, Object storedEmbedding) {

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new ByteArrayResource(imageBytes) {
            @Override
            public String getFilename() {
                return "face.jpg";
            }
        });

        builder.part("stored_embedding", storedEmbedding);

        return webClient.post()
                .uri("/verify-face")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(builder.build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}
