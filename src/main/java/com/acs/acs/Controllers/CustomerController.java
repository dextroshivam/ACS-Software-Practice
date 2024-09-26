package com.acs.acs.Controllers;

import com.acs.acs.DTO.RequestDTO.CustomerRequest.CustomerRequestDTO;
import com.acs.acs.DTO.ResponseDTO.CustomerResponse.CustomerResponseDTO;
import com.acs.acs.Enitities.Customer;
import com.acs.acs.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Create a new customer
    @PostMapping("/add")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        try{
            Customer createdCustomer = customerService.createCustomer(customerRequestDTO);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Get a customer by ID
    @GetMapping("get/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {

        try{
            CustomerResponseDTO customerResponseDTO = customerService.getCustomerById(id);
            return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


    }

    // Get all customers
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }



//     Update a customer
    @PutMapping("update/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO updateCustomerResponseDTO = customerService.updateCustomer(id, customerRequestDTO);
        return new ResponseEntity<>(updateCustomerResponseDTO, HttpStatus.OK);
    }


//    // Delete a customer
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
//        customerService.deleteCustomer(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
