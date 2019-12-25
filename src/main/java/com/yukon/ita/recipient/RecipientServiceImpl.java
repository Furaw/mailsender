package com.yukon.ita.recipient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipientServiceImpl  implements RecipientService{

    @Autowired
    private RecipientRepository recipientRepository;

    public RecipientServiceImpl(RecipientRepository recipientRepository){
        this.recipientRepository = recipientRepository;
    }
    @Override
    public Recipient insert(Recipient recipient) {
        return recipientRepository.save(recipient);
    }
    @Override
    public List<Recipient> findAll() {
        return recipientRepository.findAll();
    }
    @Override
    public Optional<Recipient> findById(Long id) {
        return recipientRepository.findById(id);
    }
    @Override
    public Recipient update(Recipient recipient, Long id) {
        recipient.setRecipientId(id);
        return recipientRepository.save(recipient);
    }
    @Override
    public void deleteById(Long id) {
        recipientRepository.deleteById(id);
    }

}