package org.iftm.client_api_rest.services;

import org.iftm.client_api_rest.entities.Address;
import java.util.List;

import org.iftm.client_api_rest.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public List<Address> findAll() {
     	return addressRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Address findById(Long id) {
	return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
    }

    @Transactional
    public Address insert(Address address) {
        validateZipCode(address.getZipCode());
        return addressRepository.save(address);
    }

    @Transactional
    public Address update(Long id, Address updatedAddress) {
     Address entity = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        validateZipCode(updatedAddress.getZipCode());
        entity.setStreet(updatedAddress.getStreet());
        entity.setCity(updatedAddress.getCity());
        entity.setState(updatedAddress.getState());
        entity.setZipCode(updatedAddress.getZipCode());
        return addressRepository.save(entity);
    }

    private void validateZipCode(String zipCode) {
        if (zipCode == null || !zipCode.matches("\\d{5}-\\d{3}")) {
            throw new IllegalArgumentException("Invalid ZIP code.");
        }
    }
    
}

