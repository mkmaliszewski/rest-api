package com.marek.restapi.repository;

import com.marek.restapi.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    
}