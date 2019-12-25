package com.yukon.ita.controllers;

import com.yukon.ita.recipient.Recipient;
import com.yukon.ita.recipient.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RecipientController {

    private final RecipientService recipientService;

    @Autowired
    public RecipientController (RecipientService  recipientService)
    {
        this.recipientService = recipientService;
    }
    @GetMapping(value = "/recipient/{id}")
    public Optional<Recipient> getRecipientById(@PathVariable("id") Long id)
    {
        return recipientService.findById(id);
    }
    @GetMapping(value = "/recipients")
    public ResponseEntity<List<Recipient>> getAllRecipients()
    {
        List<Recipient> recipients = recipientService.findAll();
        return ResponseEntity.ok().body(recipients);
    }
    @PostMapping(value = "/recipient/save")
    public Recipient insert(@RequestBody Recipient recipient)
    {
        return recipientService.insert(recipient);
    }
    @PutMapping(value = "/recipient/update/{id}")
    public ResponseEntity<Recipient> update(@PathVariable("id") Long id, @RequestBody Recipient recipient)
    {
        recipientService.update(recipient, id);
        return ResponseEntity.ok(recipient);
    }
    @DeleteMapping(value = "/recipient/delete/{id}")
    public void delete(@PathVariable("id") Long id)
    {
        recipientService.deleteById(id);
    }
}
