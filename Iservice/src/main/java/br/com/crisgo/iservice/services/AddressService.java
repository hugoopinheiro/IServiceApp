package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.models.Address;
import br.com.crisgo.iservice.repositorys.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address) {
        // Any business logic for address creation can go here
        return addressRepository.save(address);
    }
}


