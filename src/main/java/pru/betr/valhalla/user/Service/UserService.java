package pru.betr.valhalla.user.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pru.betr.valhalla.user.DTOs.UserDTO;
import pru.betr.valhalla.user.Entity.User;
import pru.betr.valhalla.user.Repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new UserDTO(user.getUsername(), user.getEmail());
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getUsername(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
