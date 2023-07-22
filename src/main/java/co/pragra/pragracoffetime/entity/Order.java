package co.pragra.pragracoffetime.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "TABLE_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private double purchaseAmount;
    private Date orderDate;
    @OneToMany(targetEntity = Product.class, cascade = CascadeType.DETACH )
    private List<Product> products;
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
}
