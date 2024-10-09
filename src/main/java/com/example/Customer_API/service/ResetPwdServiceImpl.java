package com.example.Customer_API.service;

import com.example.Customer_API.dto.ResetPassword;
import com.example.Customer_API.dto.RestResponse;
import com.example.Customer_API.entity.Customer;
import com.example.Customer_API.repo.CustomerRepo;
import org.springframework.stereotype.Service;

@Service
public class ResetPwdServiceImpl implements ResetPwdService{

    private CustomerRepo customerRepo;
    public ResetPwdServiceImpl(CustomerRepo customerRepo){
        this.customerRepo=customerRepo;
    }
    RestResponse response=new RestResponse();

    Customer customer=new Customer();

    public RestResponse resetPassword(ResetPassword resetPassword){
        System.out.println(resetPassword);
        customer=customerRepo.findByEmail(resetPassword.getEmail());

        if(!resetPassword.getNewPwd().equals(resetPassword.getConfirmNewPwd())){
            response.setResponse("Passwords must match");
            return response;
        }else {

            customer.setEmail(customer.getEmail());
            customer.setPassword(resetPassword.getNewPwd());
            customerRepo.save(customer);
            response.setResponse("Password updated please try to login now");
            return  response;
        }
    }

}
