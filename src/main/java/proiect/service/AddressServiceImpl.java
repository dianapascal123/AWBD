package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.entity.Address;
import proiect.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService{
    AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
