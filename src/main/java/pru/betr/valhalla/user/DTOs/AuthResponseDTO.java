package pru.betr.valhalla.user.DTOs;

public class AuthResponseDTO {
    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
}
