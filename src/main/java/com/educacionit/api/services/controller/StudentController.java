package com.educacionit.api.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.educacionit.api.services.beans.Message;
import com.educacionit.api.services.entities.Student;
import com.educacionit.api.services.repositories.IStudentRepository;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {


    @Autowired
    private IStudentRepository dao;


    @RequestMapping (value="{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findStudentById (@PathVariable("id") String id) {

        Student target = dao.findOne (id);

        if (target == null) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Student", String.format ("Student %s not found", id)));

        } else {

            return ResponseEntity.ok (target);
        }
    }

    @RequestMapping (value="/email/{val}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findStudentByEmail (@PathVariable("val") String val) {

        Student target = dao.findStudentByEmailLike (val);

        if (target == null) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Student", String.format ("Student %s not found", val)));

        } else {

            return ResponseEntity.ok (target);
        }
    }

    @RequestMapping (value="/name/{val}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findStudentByName (@PathVariable("val") String val) {

        Student target = dao.findStudentByNameLike(val);

        if (target == null) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Student", String.format ("Student %s not found", val)));

        } else {

            return ResponseEntity.ok (target);
        }
    }

    @RequestMapping (value="/lastname/{val}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findStudentByLastName(@PathVariable("val") String val) {

        Student target = dao.findStudentByLastNameLike(val);

        if (target == null) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Student", String.format ("Student %s not found", val)));

        } else {

            return ResponseEntity.ok (target);
        }
    }

    @RequestMapping (method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllStudents () {

        List<Student> list = dao.findAll ();

        if (list.isEmpty ()) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Student", "List is empty, there are not students."));

        } else {

            return ResponseEntity.ok (list);
        }
    }

    @RequestMapping (method = RequestMethod.POST)
    public ResponseEntity<?> save (@RequestBody Student data) {

        try {

            this.dao.save (data);

        } catch (Exception e) {

            return ResponseEntity.badRequest ().build ();
        }

        return ResponseEntity.noContent ().build ();
    }

    @RequestMapping (value="{id}", method = RequestMethod.PUT, consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update (@PathVariable("id") String id, @RequestBody  Student data) {

        try {

            if (!this.dao.exists (id)) {

                return ResponseEntity.status(404).body(new Message ("400", "Resource Student", String.format ("Student %s not found", id)));
            }

            data.setId (id);
            this.dao.save (data);

        } catch (Exception e) {

            return ResponseEntity.badRequest ().build ();
        }

        return ResponseEntity.ok (data);
    }

    @RequestMapping (value="{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete (@PathVariable("id") String id) {

        if (!this.dao.exists (id)) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Student", String.format ("Student %s not found", id)));

        } else {

            this.dao.delete (id);

            return ResponseEntity.noContent ().build ();
        }
    }
}
