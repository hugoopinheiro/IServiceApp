package br.com.crisgo.iservice.mapper.implementations;

import br.com.crisgo.iservice.DTO.response.ResponseAddressDTO;
import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import br.com.crisgo.iservice.models.User;
import br.com.crisgo.iservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class ModelMapperImpl implements Mapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ModelMapperImpl() {
        MODEL_MAPPER.getConfiguration()
                .setFieldMatchingEnabled(true) // Ensure fields match
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE); // Map private fields

        // ✅ Explicitly map Address -> ResponseAddressDTO
        MODEL_MAPPER.typeMap(User.class, ResponseUserDTO.class)
                .addMappings(mapper -> mapper.map(User::getAddress, ResponseUserDTO::setResponseAddressDTO));
    }

    @Override
    public <O, D> D map(O object, Class<D> destiny) {
        return MODEL_MAPPER.map(object, destiny);
    }

    @Override
    public <O, D> List<D> map(List<O> objects, Class<D> destiny) {
        List<D> result = new ArrayList<>();
        for (O object : objects) {
            result.add(MODEL_MAPPER.map(object, destiny));
        }
        return result;
    }

    @Override
    public <O, D> void mapOntoExistingObject(O source, D destination) {
        MODEL_MAPPER.map(source, destination);
    }
}
