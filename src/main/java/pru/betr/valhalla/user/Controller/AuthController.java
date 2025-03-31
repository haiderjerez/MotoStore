package pru.betr.valhalla.user.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pru.betr.valhalla.user.DTOs.AuthRequestDTO;
import pru.betr.valhalla.user.DTOs.AuthResponseDTO;
import pru.betr.valhalla.user.DTOs.UserDTO;
import pru.betr.valhalla.user.Security.JwtUtil;
import pru.betr.valhalla.user.Service.AuthService;
import pru.betr.valhalla.user.Service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtutil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
        String token = authService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> request) {
        authService.registerUser(request.get("username"), request.get("email"), request.get("password"));
        return "Usuario registrado exitosamente";
    }

     
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String token) {
        // Verifica que el token comience con "Bearer "
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // Remueve "Bearer "
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token format");
        }

        // Extrae el nombre de usuario (o correo) desde el token
        String username = jwtutil.extractUserName(token);
        
        // Obtiene los datos del usuario desde el servicio
        UserDTO userDTO = userService.getUserByUsername(username);

        // Retorna los datos del usuario junto con el token
        return ResponseEntity.ok(Map.of(
            "user", userDTO,
            "token", token
        ));
    }
}
