package com.educacionit.api.services.controller;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.educacionit.api.services.beans.Message;
import com.educacionit.api.services.entities.Student;
import com.educacionit.api.services.repositories.IStudentRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {


    @Autowired
    private IStudentRepository dao;

    ////////////////////////////////////////////  CRUD   ////////////////////////////////////////////////////////////

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
            // F: Femenino
            if(data.getSexo().equals("F")){
                this.dao.save (data);
                this.savePhotoF();
            }else{
                // Caso contrario es M :Masculino
                this.dao.save (data);
                this.savePhotoM();
            }
            //this.dao.save (data);

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


    //////////////////////////////   Function y Method independientes /////////////////////////
    //save Photo M
    private void savePhotoM() {
        //Condicion para analizar el sexo : M
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("services");
        String newFileName = "Profile";
        File imageMan = new File("D:\\java\\workspace\\MyProyects\\API-Rest-Services\\imagenes\\man.jpg");
        GridFS gfsPhoto = new GridFS(db, "photo");
        GridFSInputFile gfsFile = null;
        try {
            gfsFile = gfsPhoto.createFile(imageMan);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gfsFile.setFilename(newFileName);
        gfsFile.save();


    }

    //save Photo F
    private void savePhotoF() {
        //Condicion para analizar el sexo : M
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("services");
        String newFileName = "Profile";
        File imageWoman = new File("D:\\java\\workspace\\MyProyects\\API-Rest-Services\\imagenes\\woman.jpg");
        GridFS gfsPhoto = new GridFS(db, "photo");
        GridFSInputFile gfsFile = null;
        try {
            gfsFile = gfsPhoto.createFile(imageWoman);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gfsFile.setFilename(newFileName);
        gfsFile.save();


    }
}
