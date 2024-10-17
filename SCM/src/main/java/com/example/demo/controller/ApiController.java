package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Contact;
import com.example.demo.services.ContactService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ContactService contactservice;
    
    @GetMapping("/contact/{contactId}")
    public Contact getContact(@PathVariable String contactId){
        return contactservice.getById(contactId);
        
    }; 
}
