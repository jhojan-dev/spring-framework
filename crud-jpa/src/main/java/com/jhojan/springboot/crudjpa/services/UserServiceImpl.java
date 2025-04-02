package com.jhojan.springboot.crudjpa.services;

import com.jhojan.springboot.crudjpa.entities.Role;
import com.jhojan.springboot.crudjpa.entities.User;
import com.jhojan.springboot.crudjpa.repositories.RoleRepository;
import com.jhojan.springboot.crudjpa.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) throw new RuntimeException("Username already exists");

        List<Role> roles = new ArrayList<>();
        if (user.isAdmin()) {
            roles.addAll( roleRepository.findByName("ROLE_ADMIN").stream().toList() );
        }
        roles.addAll( roleRepository.findByName("ROLE_USER").stream().toList() );

        user.setRoles( roles );
        user.setPassword( passwordEncoder.encode(user.getPassword()) );

        return userRepository.save(user); // <- acá
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @PostConstruct
    public void init() {
        User user = userRepository.save(
                new User().setAdmin(true).setUsername("admin").setPassword(passwordEncoder.encode("admin"))
                        .setRoles(List.of( new Role(1L, "ROLE_ADMIN") ))
        );

        System.out.println("user = " + user);
    }

}
