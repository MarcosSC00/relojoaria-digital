package br.com.relojoaria.service.impl;

import br.com.relojoaria.entity.User;
import br.com.relojoaria.error.exception.NotFoundException;
import br.com.relojoaria.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new NotFoundException("usuário "+username+" não encontrado");
        }
        return user;
    }
}
