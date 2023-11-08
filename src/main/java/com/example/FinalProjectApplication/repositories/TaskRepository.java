package com.example.FinalProjectApplication.repositories;

import com.example.FinalProjectApplication.Tables.Tasks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TaskRepository extends CrudRepository<Tasks, UUID> {

}
