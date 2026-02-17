package Backend.DTO;


public class VoterStatusResponse {

    private boolean faceRegistered;
    private boolean faceVerified;
    private boolean hasVoted;

    public VoterStatusResponse(boolean faceRegistered, boolean faceVerified, boolean hasVoted) {
        this.faceRegistered = faceRegistered;
        this.faceVerified = faceVerified;
        this.hasVoted = hasVoted;
    }

    public boolean isFaceRegistered() {
        return faceRegistered;
    }

    public boolean isFaceVerified() {
        return faceVerified;
    }

    public boolean isHasVoted() {
        return hasVoted;
    }
}
