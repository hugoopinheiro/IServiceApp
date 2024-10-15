package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.exceptions.UserNotFoundException;
import br.com.crisgo.iservice.models.Seller;
import br.com.crisgo.iservice.repositorys.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    // Find all sellers from the database
    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    // Find seller by ID
    public Seller findById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario de ID " + id + " não encontrado"));
    }

    @Transactional
    public void deleteSellerById(Long id) {
        if (!sellerRepository.existsById(id)) {
            throw new UserNotFoundException("Usuario de ID " + id + " não encontrado");
        }
        sellerRepository.deleteById(id);
    }

    public void saveOrUpdate(Seller seller) {
        sellerRepository.save(seller);
    }

    @Transactional
    public Seller updateSeller(Long id, Seller sellerDetails) {
        // Find existing seller
        Seller existingSeller = sellerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario de ID " + id + " não encontrado"));

        // Update fields
        existingSeller.setName(sellerDetails.getName());  // example field, adjust based on your model
        existingSeller.setEmail(sellerDetails.getEmail());
        existingSeller.setAddress(sellerDetails.getAddress());

        // Save the updated seller
        return sellerRepository.save(existingSeller);
    }

}
