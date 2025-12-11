package br.com.relojoaria.controller;

import br.com.relojoaria.dto.request.UserLogin;
import br.com.relojoaria.dto.request.UserRegister;
import br.com.relojoaria.dto.response.TokenResponse;
import br.com.relojoaria.entity.User;
import br.com.relojoaria.repository.UserRepository;
import br.com.relojoaria.service.impl.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutheticationController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private TokenService tokenService;

    @Autowired
    public AutheticationController(AuthenticationManager authenticationManager,
                                   UserRepository userRepository,
                                   TokenService
                                           tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UserLogin request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);
        String jwt = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRegister request) {
        if(userRepository.findByName(request.getName()) != null){
            return ResponseEntity.badRequest().body("usuario ja existe.");
        }
        String password =  new BCryptPasswordEncoder().encode(request.getPassword());

        User user = new User(request.getName(), password, request.getRole());

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
