package com.acs.acs.Services;

import com.acs.acs.DTO.ResponseDTO.ReportResponse.CustomerReportResponseDTO;
import com.acs.acs.DTO.ResponseDTO.ReportResponse.ProductReportResponseDTO;
import com.acs.acs.DTO.ResponseDTO.ReportResponse.UserReportResponseDTO;
import com.acs.acs.Enitities.Customer;
import com.acs.acs.Enitities.Product;
import com.acs.acs.Enitities.User;
import com.acs.acs.Repository.CustomerRepository;
import com.acs.acs.Repository.ProductRepository;
import com.acs.acs.Repository.ReportRepository;
import com.acs.acs.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;

    \
    public Object getReport(Long id){

        if (id == 1) {
            try{

                List<User> userList=userRepository.findAll();
                List<UserReportResponseDTO> userReportResponseDTOList=new ArrayList<>();
                for(User user:userList){
                    UserReportResponseDTO userReportResponseDTO = new UserReportResponseDTO();
                    userReportResponseDTO.setName(user.getName());
                    userReportResponseDTO.setPhone(user.getPhone());
                    userReportResponseDTOList.add(userReportResponseDTO);
                }

                return userReportResponseDTOList;
            }catch (Exception e){
                return "Can't fetch report of id 1";
            }
        }


        else if (id == 2) {
             try{
                 List<Product> productList=productRepository.findAll();
                 List<ProductReportResponseDTO> productReportResponseDTOList=new ArrayList<>();
                 for(Product product:productList){
                     ProductReportResponseDTO productReportResponseDTO = new ProductReportResponseDTO();
                     productReportResponseDTO.setUpc(product.getUpc());
                     productReportResponseDTO.setSku(product.getSku());
                     productReportResponseDTO.setName(product.getName());
                     productReportResponseDTOList.add(productReportResponseDTO);
                 }
                 return productReportResponseDTOList;

            }catch (Exception e){
                 return "Can't fetch report of id 2";

            }
        }
        else if (id == 3) {
             try{
                 List<Customer> customerList=customerRepository.findAll();
                 List<CustomerReportResponseDTO> customerReportResponseDTOList=new ArrayList<>();
                 for(Customer customer:customerList){
                     CustomerReportResponseDTO customerReportResponseDTO = new CustomerReportResponseDTO();
                     customerReportResponseDTO.setId(customer.getId());
                     customerReportResponseDTO.setName(customer.getName());
                     customerReportResponseDTOList.add(customerReportResponseDTO);
                 }
                 return customerReportResponseDTOList;

            }catch (Exception e){
                 return "Can't fetch report of id 3";
            }
        }
        else {
            return "Can't fetch report of given ID";
        }
    }
}
