package com.procesos.inventario.controllers;

import com.procesos.inventario.models.User;
import com.procesos.inventario.services.UserService;
import com.procesos.inventario.services.UserServiceImp;
import com.procesos.inventario.utils.ApiResponse;
import com.procesos.inventario.utils.Constants;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private ApiResponse apiResponse;
    Map data = new HashMap<>();

    @GetMapping(value = "/{id}")
    public ResponseEntity findUserById(@PathVariable Long id){
        try {
            apiResponse = new ApiResponse(Constants.REGISTER_FOUND,(userService.getUser(id)));
            return new ResponseEntity(apiResponse,HttpStatus.OK);
            }catch(Exception e)
            {
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND,"");
            return new ResponseEntity(apiResponse,HttpStatus.NOT_FOUND);
            }

    }
    @PostMapping (value = "")
    public ResponseEntity saveUser(@RequestBody User user){
        Boolean userResp = userService.createUser(user);

        if (userResp == true){
           apiResponse = new ApiResponse(Constants.REGISTER_CREATED,"");
           return  new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        apiResponse = new ApiResponse(Constants.REGISTER_BAD,user);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
    // TAREA
    @GetMapping(value = "")
    public ResponseEntity allUsers(){

        try {
            apiResponse = new ApiResponse(Constants.REGISTER_LOGIN,userService.allUsers());
            return new ResponseEntity(apiResponse,HttpStatus.OK);
        }catch(Exception e){
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND,"");
            return new ResponseEntity(apiResponse,HttpStatus.NOT_FOUND);
        }

    }

/*
    @PutMapping(value = "/user/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {

        Boolean userDB = userServiceImp.updateUser(id, user);
        try {
            if (userDB == null) {

                apiResponse = new ApiResponse(Constants.REGISTER_UPDATED,"");
                return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(apiResponse.getUser(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("status", "201");
            response.put("message", "se encontro usuario");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }


*/





}
