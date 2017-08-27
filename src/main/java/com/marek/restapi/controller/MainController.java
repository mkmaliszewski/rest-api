package com.marek.restapi.controller;

import com.marek.restapi.error.CustomError;
import com.marek.restapi.model.Contact;
import com.marek.restapi.repository.ContactRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class MainController {
    @Autowired
    private ContactRepository contactRepository;
    
    @GetMapping(path = "/list")
    public ResponseEntity<List<Contact>> getAllContacts(){
        List<Contact> contacts = (List<Contact>) contactRepository.findAll();
        if (contacts.isEmpty()){
            return new ResponseEntity<>(contacts, HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
    
    @GetMapping(path = "/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable("id") Long id){
        Contact contact = contactRepository.findOne(id);
        if (contact == null){
            return new ResponseEntity(new CustomError("User with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
    
    @PostMapping(path = "/add")
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactRepository.save(contact), 
                HttpStatus.CREATED);
    }
    
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Contact> deleteContact(@PathVariable("id") Long id) {
        Contact contact = contactRepository.findOne(id);
        if (contact == null){
            return new ResponseEntity(new CustomError("User with id " + id 
                    + " not foud"), HttpStatus.NOT_FOUND);
        }
        
        contactRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
    
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable("id") Long id, 
            @RequestBody Contact updatedContact) {
        Contact contact = contactRepository.findOne(id);
        if (contact == null){
            return new ResponseEntity(new CustomError("User with id " + id 
                    + " not foud"), HttpStatus.NOT_FOUND);
        }
        
        contact.setName(updatedContact.getName());
        contact.setLastName(updatedContact.getLastName());
        contact.setNumber(updatedContact.getNumber());
        contact.setMail(updatedContact.getMail());
        contact.setBornDate(updatedContact.getBornDate());
        contactRepository.save(contact);
        
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
}