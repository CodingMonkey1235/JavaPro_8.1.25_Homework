package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private long id;

    @Getter @Setter
    @Column(name = "account_number")
    private String accountNumber;

    @Getter @Setter
    @Column(name = "balance")
    private BigDecimal balance;

    @Getter @Setter
    @Column(name = "product_type")
    private String productType;

    @Getter @Setter
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", productType='" + productType + '\'' +
                ", user=" + user +
                '}';
    }
}
