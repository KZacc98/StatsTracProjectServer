package com.example.demo.contact;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public List<Contact> getContacts() {

        return contactRepository.findAll();

    }

    public void addNewContact(Contact contact) {
        Optional<Contact> contactOptional = contactRepository.findContactByEmail(contact.getEmail());
        if(contactOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        contactRepository.save(contact);
    }
}
