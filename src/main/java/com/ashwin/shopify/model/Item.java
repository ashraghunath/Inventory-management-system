package com.ashwin.shopify.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents item stored in an inventory
 * @author Ashwin Raghunath
 * @since 1.0
 */
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "quantity", nullable = false, length = 6)
    private Long quantity;

    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    public Item() {
    }

    public Item(String name, Long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }
}
