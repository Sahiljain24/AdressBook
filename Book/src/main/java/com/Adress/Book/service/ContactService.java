package com.Adress.Book.service;




import com.Adress.Book.DTO.ContactDTO;
import com.Adress.Book.model.Contact;
import com.Adress.Book.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact addContact(ContactDTO contactDTO) {
        Contact contact = new Contact(null, contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhoneNumber());
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, ContactDTO contactDTO) {
        return contactRepository.findById(id).map(contact -> {
            contact.setName(contactDTO.getName());
            contact.setEmail(contactDTO.getEmail());
            contact.setPhoneNumber(contactDTO.getPhoneNumber());
            return contactRepository.save(contact);
        }).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
