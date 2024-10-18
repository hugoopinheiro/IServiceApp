package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.models.User;
import br.com.crisgo.iservice.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->  new EntityNotFoundException("Usuario de ID " + id + " não encontrado"));
    }
}
