package com.educacionit.api.services.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.educacionit.api.services.entities.Student;

@Repository
public interface IStudentRepository extends MongoRepository<Student, String> {
    /**
     * Busquedas:
     * por Email
     * por LastName
     * por Name
     **/
    Student findStudentByEmailLike (String email);
    Student findStudentByLastNameLike(String lastname);
    Student findStudentByNameLike(String name);

}