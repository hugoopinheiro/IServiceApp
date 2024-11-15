package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.DTO.request.RequestSellerDTO;
import br.com.crisgo.iservice.DTO.response.ResponseSellerDTO;
import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.mapper.DozerMapper;
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

    public List<ResponseSellerDTO> findAll() {
        List<Seller> sellers = sellerRepository.findAll();
        return DozerMapper.parseListObject(sellers, ResponseSellerDTO.class);
    }

    public ResponseSellerDTO findById(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario de ID " + id + " não encontrado"));
        return DozerMapper.parseObject(seller, ResponseSellerDTO.class);
    }

    public ResponseSellerDTO createSeller(RequestSellerDTO requestSellerDTO) {
        // Map RequestSellerDTO to Seller entity
        Seller seller = DozerMapper.parseObject(requestSellerDTO, Seller.class);
        Seller savedSeller = sellerRepository.save(seller);
        // Map saved Seller entity to ResponseSellerDTO
        return DozerMapper.parseObject(savedSeller, ResponseSellerDTO.class);
    }

    @Transactional
    public ResponseSellerDTO updateSeller(Long id, RequestSellerDTO requestSellerDTO) {
        Seller existingSeller = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario de ID " + id + " não encontrado"));

        // Map fields from requestSellerDTO onto existingSeller
        DozerMapper.mapOntoExistingObject(requestSellerDTO, existingSeller);

        Seller updatedSeller = sellerRepository.save(existingSeller);
        return DozerMapper.parseObject(updatedSeller, ResponseSellerDTO.class);
    }

    @Transactional
    public void deleteSellerById(Long id) {
        if (!sellerRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario de ID " + id + " não encontrado");
        }
        sellerRepository.deleteById(id);
    }
}
