package pru.betr.valhalla.user.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pru.betr.valhalla.user.Entity.User;
import pru.betr.valhalla.user.Repository.UserRepository;
import pru.betr.valhalla.user.Security.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticate(String username, String password) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new BadCredentialsException("❌ Usuario no encontrado"));

            log.info("🔍 Usuario encontrado: {}", username);
            log.info("🔐 Contraseña ingresada: {}", password);
            log.info("🔑 Contraseña en la BD: {}", user.getPassword());

        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.error("❌ Error: Credenciales inválidas");
            throw new BadCredentialsException("Credenciales inválidas");
        }

        log.info("✅ Autenticación exitosa, generando token...");
        return jwtUtil.generateToken(username);
    }

    

    public void registerUser(String username, String email, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email); 
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
