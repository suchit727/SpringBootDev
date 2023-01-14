package com.example.cartanditem.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @SequenceGenerator(name = "seq_item_id", initialValue = 1, sequenceName = "seq_item_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_id")
    @Column(name = "item_id")
    private Long id;
    @Column(name = "item_name")
    private String name;
    @Column(name = "item_price")
    private Double price;
    @Column(name = "item_quantity")
    private Long quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="cart_id")
    private CartEntity cartEntity;
}
