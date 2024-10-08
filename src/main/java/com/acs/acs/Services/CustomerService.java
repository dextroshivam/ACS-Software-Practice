package com.acs.acs.Services;

import com.acs.acs.DTO.RequestDTO.CustomerRequest.CustomerRequestDTO;
import com.acs.acs.DTO.ResponseDTO.CustomerResponse.CustomerResponseDTO;
import com.acs.acs.Enitities.City;
import com.acs.acs.Enitities.Country;
import com.acs.acs.Enitities.Customer;
import com.acs.acs.Enitities.State;
import com.acs.acs.Repository.CityRepository;
import com.acs.acs.Repository.CountryRepository;
import com.acs.acs.Repository.CustomerRepository;
import com.acs.acs.Repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    StateRepository stateRepository;
    @Autowired
    CountryRepository countryRepository;


    public Customer createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = CustomerRequestDTOToCustomer(customerRequestDTO);
        customer.setStatus(true);
        return customerRepository.save(customer);
    }

    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer;
        try {
            customer=customerRepository.findById(id).get();
        } catch (Exception e){
            throw new RuntimeException("Customer not found");
        }
        CustomerResponseDTO customerResponseDTO = customerToCustomerResponseDTO(customer);
        return customerResponseDTO;

    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO customerRequestDTO) {
        System.out.println("id is : "+id);
        Customer customer;
        try {
             customer = customerRepository.findById(id).get();
        }catch (Exception e){
            throw new RuntimeException("Customer not found");
        }

//        private String name;
//        private String email;
//        private String phone;
//        private String password;
//        private String addressLine1;
//        private String addressLine2;
//        private Long cityId;
//        private Long stateId;
//        private Long countryId;
//        private String zipCode;
        customer.setName(customerRequestDTO.getName());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setPhone(customerRequestDTO.getPhone());
        customer.setPassword(customerRequestDTO.getPassword());
        customer.setAddressLine1(customerRequestDTO.getAddressLine1());
        customer.setAddressLine2(customerRequestDTO.getAddressLine2());
        customer.setCityId(customerRequestDTO.getCityId());
        customer.setStateId(customerRequestDTO.getStateId());
        customer.setCountryId(customerRequestDTO.getCountryId());
        customer.setZipCode(customerRequestDTO.getZipCode());
        customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO=customerToCustomerResponseDTO(customer);
        return customerResponseDTO;
    }

//    @Override
//    public void deleteCustomer(Long id) {
//        Customer customer = getCustomerById(id);
//        customerRepository.delete(customer);
//    }
    private CustomerResponseDTO customerToCustomerResponseDTO(Customer customer) {
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
//        private String name;
//        private String email;
//        private String phone;
//        private String password;
//        private String addressLine1;
//        private String addressLine2;
//        private String city;
//        private String state;
//        private String country;
//        private String zipCode;
        customerResponseDTO.setName(customer.getName());
        customerResponseDTO.setEmail(customer.getEmail());
        customerResponseDTO.setPhone(customer.getPhone());
        customerResponseDTO.setPassword(customer.getPassword());
        customerResponseDTO.setAddressLine1(customer.getAddressLine1());
        customerResponseDTO.setAddressLine2(customer.getAddressLine2());
        // get city by id
        Optional<City> city=cityRepository.findById(customer.getCityId());
        customerResponseDTO.setCity(city.get().getName());
        // get state
        Optional<State> state=stateRepository.findById(customer.getStateId());
        customerResponseDTO.setState(state.get().getState().toString());
        // get country
        Optional<Country> country=countryRepository.findById(customer.getCountryId());
        customerResponseDTO.setCountry(country.get().getCountry().toString());
        customerResponseDTO.setZipCode(customer.getZipCode());

        return customerResponseDTO;
    }
    private Customer CustomerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO){
//        private String name;
//        private String email;
//        private String phone;
//        private String password;
//
//        private String addressLine1;
//        private String addressLine2;
//        private Long cityId;
//        private Long stateId;
//        private Long countryId;
//        private String zipCode;
        Customer customer = new Customer();
        customer.setName(customerRequestDTO.getName());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setPhone(customerRequestDTO.getPhone());
        customer.setPassword(customerRequestDTO.getPassword());
        customer.setAddressLine1(customerRequestDTO.getAddressLine1());
        customer.setAddressLine2(customerRequestDTO.getAddressLine2());
        customer.setIsPartialOrder(customerRequestDTO.getIsPartialOrder());
        customer.setCityId(customerRequestDTO.getCityId());
        customer.setStateId(customerRequestDTO.getStateId());
        customer.setCountryId(customerRequestDTO.getCountryId());
        customer.setZipCode(customerRequestDTO.getZipCode());

        return customer;
    }
}
