package Backend.Model;

import jakarta.persistence.*;

import Backend.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="Voter")

public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String voterId;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private boolean hasVoted;

    @Lob
    @Column(name = "face_image")
    private byte[] faceImage;

    @Column(nullable = false)
    private boolean faceRegister=false;

    public String getFaceEmbedding() {
        return faceEmbedding;
    }

    public void setFaceEmbedding(String faceEmbedding) {
        this.faceEmbedding = faceEmbedding;
    }

    @Lob
    @Column(name = "face_embedding",columnDefinition = "TEXT")
    private String faceEmbedding;

    public byte[] getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(byte[] faceimage) {
        this.faceImage = faceimage;
    }





    public boolean isFaceRegister() {
        return faceRegister;
    }

    public void setFaceRegister(boolean faceRegister) {
        this.faceRegister = faceRegister;
    }

    private boolean faceVerified;


    private String faceImagePath;

    public int getId() {
        return id;
    }

    public String getFaceImagePath() {
        return faceImagePath;
    }

    public void setFaceImagePath(String faceImagePath) {
        this.faceImagePath = faceImagePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public boolean isFaceVerified() {
        return faceVerified;
    }

    public void setFaceVerified(boolean faceVerified) {
        this.faceVerified = faceVerified;
    }
}
