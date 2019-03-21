package com.globomatics.bike.controllers;

import com.globomatics.bike.model.Bike;
import com.globomatics.bike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by b010nsm on 19/03/2019.
 */
@RestController
@RequestMapping("/api/v1/bikes")
public class BikeController {

    @Autowired
    private BikeRepository bikeRepository;

     @GetMapping
     public List<Bike> list(){
         return bikeRepository.findAll();
     }

     @PostMapping
     @ResponseStatus(HttpStatus.OK)
     public void create(@RequestBody Bike bike){
         bikeRepository.save(bike);
     }

     @GetMapping("/{id}")
     public Bike getBike(@PathVariable("id") long id){
         return bikeRepository.getOne(id);
     }
}
