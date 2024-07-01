package com.jabhay2012.ShoppingCart.controller;

import java.util.Collections;

import com.jabhay2012.ShoppingCart.dto.AuthResponseDto;
import com.jabhay2012.ShoppingCart.dto.LoginDto;
import com.jabhay2012.ShoppingCart.dto.LoginResponseDto;
import com.jabhay2012.ShoppingCart.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.jabhay2012.ShoppingCart.dto.RegisterDto;
import com.jabhay2012.ShoppingCart.entities.Role;
import com.jabhay2012.ShoppingCart.entities.UserEntity;
import com.jabhay2012.ShoppingCart.repos.RoleRepository;
import com.jabhay2012.ShoppingCart.repos.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        System.out.println("123");
    }




    @GetMapping("/register")
    public String greet(){
        return "hello";
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())

        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        // Fetch user details
        UserEntity user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(()-> new RuntimeException("User not found"));
        LoginResponseDto responseDto = new LoginResponseDto(token, "Bearer",user.getId(), user.getUsername(), user.getEmail());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        System.out.println("456");
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            System.out.println("print inside if");
            return new ResponseEntity<>("User name already taken", HttpStatus.BAD_REQUEST);
        }
        System.out.println("print outside");

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();

        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User Registered", HttpStatus.OK);
    }

}
