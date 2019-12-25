package com.yukon.ita.recipient;

import java.util.List;
import java.util.Optional;

public interface RecipientService {

    Recipient insert(Recipient recipient);

    List<Recipient> findAll();

    Optional<Recipient> findById(Long id);

    Recipient update(Recipient recipient, Long id);

    void deleteById(Long id);
}
