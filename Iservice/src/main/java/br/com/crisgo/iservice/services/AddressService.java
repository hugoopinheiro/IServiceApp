package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.DTO.request.RequestAddressDTO;
import br.com.crisgo.iservice.DTO.response.ResponseAddressDTO;
import br.com.crisgo.iservice.mapper.DozerMapper;
import br.com.crisgo.iservice.models.Address;
import br.com.crisgo.iservice.repositorys.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public ResponseAddressDTO createAddress(RequestAddressDTO requestAddressDTO) {
        Address address = new Address();
        address.setCep(requestAddressDTO.getCep());
        address.setComplement(requestAddressDTO.getComplement());
        address.setHouseNumber(requestAddressDTO.getHouseNumber());
        address.setStreet(requestAddressDTO.getStreet());
        address.setState(requestAddressDTO.getState());

        Address savedAddress = addressRepository.save(address);

        return mapToResponseAddressDTO(savedAddress);
    }

    public List<ResponseAddressDTO> findAll() {
        List<Address> addresses = addressRepository.findAll();
        return DozerMapper.parseListObject(addresses, ResponseAddressDTO.class);
    }

    public ResponseAddressDTO mapToResponseAddressDTO(Address address) {
        ResponseAddressDTO response = new ResponseAddressDTO();
        response.setAddressId(address.getAddressId());
        response.setCep(address.getCep());
        response.setComplement(address.getComplement());
        response.setHouseNumber(address.getHouseNumber());
        response.setStreet(address.getStreet());
        response.setState(address.getState());
        return response;
    }
}
