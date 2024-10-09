package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Contact {

    @Id
    private String id;
    @SuppressWarnings("unused")
	private String name;
    
    @SuppressWarnings("unused")
	private String email;
    
    @SuppressWarnings("unused")
	private String phoneNumber;
    
    @SuppressWarnings("unused")
	private String address;
    
    @SuppressWarnings("unused")
	private String picture;
    
    @Column(length = 1000)
    private String description;
    
    @SuppressWarnings("unused")
	private boolean favorite = false;
    
    @SuppressWarnings("unused")
	private String websiteLink;
    
    @SuppressWarnings("unused")
	private String linkedInLink;
    
    // private List<String> socialLinks=new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();

}