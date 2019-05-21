package com.educacionit.api.services.controller;

import com.educacionit.api.services.beans.Message;
import com.educacionit.api.services.entities.Teacher;
import com.educacionit.api.services.repositories.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @validate no me funciona?
@RestController
@RequestMapping("teachers")
public class TeacherController {
    @Autowired
    private ITeacherRepository dao;


    //////////////////////////////// CRUD //////////////////////////////////////////////////////////////

    /*
    * GET : Retorna por ID */
    @RequestMapping(value="{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findTeacherById (@PathVariable("id") String id) {

        Teacher target = dao.findOne (id);

        if (target == null) {

            return ResponseEntity.status(404).body(new Message("400", "Resource Teacher", String.format ("Teacher %s not found", id)));

        } else {

            return ResponseEntity.ok (target);
        }
    }

    /*
     * GET : Retorna por LastName */
    @RequestMapping (value="/lastname/{val}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findTeacherByLastName (@PathVariable("val") String val) {

        Teacher target = dao.findTeacherByLastNameLike (val);

        if (target == null) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Teacher", String.format ("Teacher %s not found", val)));

        } else {

            return ResponseEntity.ok (target);
        }
    }

    /*
     * GET : Retorna por Name */
    @RequestMapping (value="/name/{val}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findTeacherByName (@PathVariable("val") String val) {

        Teacher target = dao.findTeacherByNameLike (val);

        if (target == null) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Teacher", String.format ("Teacher %s not found", val)));

        } else {

            return ResponseEntity.ok (target);
        }
    }
    /*
     * GET : Retorna por email */
    @RequestMapping (value="/email/{val}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findTeacherByEmail (@PathVariable("val") String val) {

        Teacher target = dao.findTeacherByEmailLike (val);

        if (target == null) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Teacher", String.format ("Teacher %s not found", val)));

        } else {

            return ResponseEntity.ok (target);
        }
    }

    /*
     * GET : Retorna por titulo */
    @RequestMapping (value="/title/{val}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findTeacherByTitle (@PathVariable("val") String val) {

        Teacher target = dao.findTeacherByTitleLike (val);

        if (target == null) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Teacher", String.format ("Teacher %s not found", val)));

        } else {

            return ResponseEntity.ok (target);
        }
    }

    /*
     * GET : Retorna por profesion */
    @RequestMapping (value="/profession/{val}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findProfessionByTitle (@PathVariable("val") String val) {

        Teacher target = dao.findTeacherByProfessionLike (val);

        if (target == null) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Teacher", String.format ("Teacher %s not found", val)));

        } else {

            return ResponseEntity.ok (target);
        }
    }


    /*
     * GET : Retorna todos los datos  */
    @RequestMapping (method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllTeachers () {

        List<Teacher> list = dao.findAll ();

        if (list.isEmpty ()) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Teacher", "List is empty, there are not Teacher."));

        } else {

            return ResponseEntity.ok (list);
        }
    }

    /*
     * POST : Envia  o inserta valores*/
    @RequestMapping (method = RequestMethod.POST)
    public ResponseEntity<?> save (@RequestBody Teacher data) {

        try {

            this.dao.save (data);

        } catch (Exception e) {

            return ResponseEntity.badRequest ().build ();
        }

        return ResponseEntity.noContent ().build ();
    }

    /*
     * PUT : Modifica un valor de un ID */
    @RequestMapping (value="{id}", method = RequestMethod.PUT, consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update (@PathVariable("id") String id, @RequestBody  Teacher data) {

        try {

            if (!this.dao.exists (id)) {

                return ResponseEntity.status(404).body(new Message ("400", "Resource Teacher", String.format ("Teacher %s not found", id)));
            }

            data.setId (id);
            this.dao.save (data);

        } catch (Exception e) {

            return ResponseEntity.badRequest ().build ();
        }

        return ResponseEntity.ok (data);
    }

    /*
     * DEL: Elimina por ID*/
    @RequestMapping (value="{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete (@PathVariable("id") String id) {

        if (!this.dao.exists (id)) {

            return ResponseEntity.status(404).body(new Message ("400", "Resource Teacher", String.format ("Teacher %s not found", id)));

        } else {

            this.dao.delete (id);

            return ResponseEntity.noContent ().build ();
        }
    }
}

