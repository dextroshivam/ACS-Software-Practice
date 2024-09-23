package com.acs.acs.Services;

import com.acs.acs.Enitities.User;
import com.acs.acs.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User addUser(User user) {
    }
}
