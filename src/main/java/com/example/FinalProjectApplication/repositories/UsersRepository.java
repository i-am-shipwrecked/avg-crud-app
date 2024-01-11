package com.example.FinalProjectApplication.repositories;


import com.example.FinalProjectApplication.tables.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UsersRepository extends CrudRepository<Users, UUID> {

}
