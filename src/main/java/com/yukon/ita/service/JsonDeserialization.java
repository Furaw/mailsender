package com.yukon.ita.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yukon.ita.recipient.Recipient;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

public class JsonDeserialization extends Deserialization<Recipient> {

    private Class<Recipient> typeOfClass;

    private ObjectMapper mapper;

    public JsonDeserialization(String path,Class<Recipient> typeOfClass){
        super(path);
        mapper = new ObjectMapper();
        this.typeOfClass = typeOfClass;
    }

    @Override
    public Optional<Recipient> parse(){
        try {
            return this.deserializationJson(path);
        } catch (IOException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Optional<Recipient> deserializationJson(String path) throws IOException{
        File file = new File(path);
        Gson gson = new GsonBuilder().create();
        return Optional.of((gson.fromJson(new FileReader(file),Recipient.class)));
    }

    @Override
    public Optional<Recipient> deserializeString(String str){
        try {
            return Optional.of(mapper.readValue(str, typeOfClass));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
