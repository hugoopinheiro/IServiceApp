package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.controllers.AuthenticationController;
import br.com.crisgo.iservice.repositorys.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    private final UserRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    public AuthorizationService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = repository.findByUsername(username);
        logger.info("estou no metodo loadUser");
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException{
        var user = repository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found:" + email);
        }
        return user;
    }
}

