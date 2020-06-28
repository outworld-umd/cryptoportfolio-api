package com.outworld.mindly.cryptoportfolioapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "items")
public class PortfolioItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = CryptoCurrency.class)
    @NonNull
    private CryptoCurrency crypto;

    @NonNull
    private BigDecimal amount;

    private Date dateOfPurchase;

    private String location;
}