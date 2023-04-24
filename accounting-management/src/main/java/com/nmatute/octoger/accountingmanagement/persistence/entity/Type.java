package com.nmatute.octoger.accountingmanagement.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Clase para Entidad de Tipos.
 * 
 * @author NM4TT
 */
@Entity
@Table(name = "type", schema = "public")
@Data
public class Type {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String identifier;

    private String description;

}
