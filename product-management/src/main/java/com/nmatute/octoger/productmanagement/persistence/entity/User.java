package com.nmatute.octoger.productmanagement.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad de Usuarios.
 * 
 * @author NM4TT
 */
@Entity
@Table(name = "user", schema = "public")
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "personal_identifier")
    private String personalIdentifier;

    private String name;

    private String lastname;

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "identifier")
    private Type type;

}
