package com.example.FinalProjectApplication.repositories;

import com.example.FinalProjectApplication.Tables.Projects;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProjectRepository extends CrudRepository<Projects, UUID> {

}
