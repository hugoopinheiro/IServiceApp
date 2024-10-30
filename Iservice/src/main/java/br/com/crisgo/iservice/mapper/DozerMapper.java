package br.com.crisgo.iservice.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    // Maps a new instance of destination class
    public static <O, D> D parseObject(O origin, Class<D> destinationClass) {
        return mapper.map(origin, destinationClass);
    }

    // Maps a list of origin objects to a list of destination class instances
    public static <O, D> List<D> parseListObject(List<O> originList, Class<D> destinationClass) {
        List<D> destinationList = new ArrayList<>();
        for (O origin : originList) {
            destinationList.add(mapper.map(origin, destinationClass));
        }
        return destinationList;
    }

    // Maps fields from origin object onto an existing destination object
    public static <O, D> void mapOntoExistingObject(O origin, D destination) {
        mapper.map(origin, destination);
    }
}
