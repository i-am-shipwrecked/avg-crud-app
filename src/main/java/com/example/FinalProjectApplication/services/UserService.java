package com.example.FinalProjectApplication.services;

import com.example.FinalProjectApplication.tables.Users;
import com.example.FinalProjectApplication.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users getUserByUsernameAndPassword(String username, String password) {
        List<Users> users = usersRepository.findByUsernameAndPassword(username, password);
        if (!users.isEmpty()) {
            return users.get(0);
        } else {
            return null;
        }
    }
}


