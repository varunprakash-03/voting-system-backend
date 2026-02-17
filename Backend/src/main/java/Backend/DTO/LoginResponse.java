package Backend.DTO;


import Backend.Model.Role;

public class LoginResponse {
    private String token;
    private String fullName;
    private String voterId;
    private Role role;

    public LoginResponse(String token,String voterId, String fullName,Role role) {

        this.token=token;
        this.voterId = voterId;
        this.fullName=fullName;
        this.role = role;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

