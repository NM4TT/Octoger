package com.nmatute.octoger.usermanagement.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class User {
    
    @Id
    @OneToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "personal_identifier")
    private String personalIdentifier;

    private String name;

    private String lastname;

    @ManyToOne
    @JoinColumn(name = "type", insertable = false, updatable = false)
    private String type;

}
