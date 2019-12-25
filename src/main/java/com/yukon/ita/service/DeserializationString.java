package com.yukon.ita.service;

import java.io.Serializable;
import java.util.Optional;

public abstract class DeserializationString<T extends Serializable> {

    public  abstract Optional<T> deserializeString(String str);
}
