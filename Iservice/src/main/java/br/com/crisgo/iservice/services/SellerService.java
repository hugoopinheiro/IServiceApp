package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.exceptions.UserNotFoundException;
import br.com.crisgo.iservice.models.Seller;
import br.com.crisgo.iservice.repositorys.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

   //  Find all sellers from the database
    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    // Find seller by ID
    public Seller findById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() ->  new UserNotFoundException("Usuario de ID" + id + "não encontrado"));
    }
}
