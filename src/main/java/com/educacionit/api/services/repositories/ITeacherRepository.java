package com.educacionit.api.services.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.educacionit.api.services.entities.Teacher;

public interface ITeacherRepository extends MongoRepository<Teacher,String>{

    /**
     * Busquedas:
     * por Email
     * por LastName
     * por Name
     * por Titulo
     * por Profesion
     **/
    Teacher findTeacherByEmailLike (String email);
    Teacher findTeacherByLastNameLike(String lastname);
    Teacher findTeacherByNameLike(String name);
    Teacher findTeacherByTitleLike(String title);
    Teacher findTeacherByProfessionLike(String profession);

}
