package Backend.Service;

import Backend.Config.JwtUtil;
import Backend.DTO.LoginRequest;
import Backend.DTO.LoginResponse;
import Backend.DTO.RegisterRequest;
import Backend.Model.Role;
import Backend.Model.Voter;
import Backend.Repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    VoterRepository voterRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public void register(RegisterRequest request) {

        if (voterRepository.existsByVoterId(request.getVoterId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Voter ID Already Registered"
            );
        }

        Voter voter = new Voter();
        voter.setFullName(request.getFullName());
        voter.setVoterId(request.getVoterId());
        voter.setPassword(passwordEncoder.encode(request.getPassword()));

        // 🔐 Always register as VOTER
        voter.setRole(Role.VOTER);

        voter.setHasVoted(false);
        voter.setFaceVerified(false);

        voterRepository.save(voter);
    }


    public LoginResponse login(LoginRequest request){
        Voter voter= voterRepository.findByVoterId(request.getVoterId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Voter Id"));


        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                voter.getPassword()
        );


        if (!matches) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");

        }

        String token = jwtUtil.generateToken(
                voter.getVoterId(),
                voter.getRole().name()
        );

        return new LoginResponse(
                token,
                voter.getVoterId(),
                voter.getFullName(),
                voter.getRole()
        );

    }

    @Bean
    public CommandLineRunner createAdmin() {
        return args -> {

            String adminId = "ADMIN001";

            if (!voterRepository.existsByVoterId(adminId)) {

                Voter admin = new Voter();
                admin.setFullName("System Admin");
                admin.setVoterId(adminId);
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                admin.setHasVoted(false);
                admin.setFaceVerified(true);

                voterRepository.save(admin);

                System.out.println("✅ Admin user created successfully!");
            }
        };
    }




}
