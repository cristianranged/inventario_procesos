package com.procesos.inventario.controllers;

import com.procesos.inventario.models.User;
import com.procesos.inventario.services.UserServiceImp;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity findUserById(@PathVariable Long id){
        Map response = new HashMap();


        try {
            return new ResponseEntity(userServiceImp.getUser(id), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("message","No se encontro el usuario");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }

    }

    // TAREA
    @GetMapping(value = "/users")
    public ResponseEntity allUsers(){
        Map response = new HashMap();
        try {
            return new ResponseEntity(userServiceImp.allUsers(), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("message","No existen usuarios ");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

    }
    //

    @PostMapping (value = "/user")
    public ResponseEntity saveUser(@RequestBody User user){
        Map response = new HashMap();
        Boolean userResp = userServiceImp.createUser(user);

        if (userResp == true){
            response.put("status","201");
            response.put("message"," se creo  el usuario");
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
        response.put("status","404");
        response.put("message","Hubo un error al crear el usuario");
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
