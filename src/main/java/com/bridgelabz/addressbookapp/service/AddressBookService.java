package com.bridgelabz.addressbookapp.service;


import com.bridgelabz.addressbookapp.model.AddressBook;
import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    public List<AddressBook> getAllContacts() {
        return repository.findAll();
    }

    public Optional<AddressBook> getContactById(Long id) {
        return repository.findById(id);
    }

    public AddressBook createContact(AddressBookDTO addressBookDTO) {
        AddressBook contact = new AddressBook();
        contact.setName(addressBookDTO.getName());
        contact.setPhoneNumber(addressBookDTO.getPhoneNumber());
        return repository.save(contact);
    }

    public Optional<AddressBook> updateContact(Long id, AddressBookDTO addressBookDTO) {
        return repository.findById(id).map(contact -> {
            contact.setName(addressBookDTO.getName());
            contact.setPhoneNumber(addressBookDTO.getPhoneNumber());
            return repository.save(contact);
        });
    }

    public boolean deleteContact(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
