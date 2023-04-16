package com.nmatute.octoger.accountingmanagement.persistence.entity;

import java.util.Date;

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
@Table(name = "sell", schema = "public")
@Data
public class Sell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "collection_id", referencedColumnName = "id")
    private ProductCollection collection;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "product_operation_id", referencedColumnName = "id")
    private ProductOperation productOperation;
    
}
