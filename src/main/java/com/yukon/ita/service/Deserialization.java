package com.yukon.ita.service;

import java.io.Serializable;
import java.util.Optional;

public abstract class Deserialization<T> {

    protected final String path;

    protected Deserialization(String path){
        this.path = path;
    }

    public abstract Optional<T> parse();

    public abstract Optional<T> deserializeString(String str);
}
