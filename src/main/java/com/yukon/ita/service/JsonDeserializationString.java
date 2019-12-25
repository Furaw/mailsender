package com.yukon.ita.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.Optional;

public class JsonDeserializationString<T extends Serializable> extends DeserializationString<T> {

    private Class<T> typeOfClass;
    private ObjectMapper mapper;

    {
        mapper = new ObjectMapper();
    }

    public JsonDeserializationString(Class<T> typeOfClass) {
        this.typeOfClass = typeOfClass;
    }


    @Override
    public Optional<T> deserializeString(String str){
        try {
            return Optional.of(mapper.readValue(str, typeOfClass));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
