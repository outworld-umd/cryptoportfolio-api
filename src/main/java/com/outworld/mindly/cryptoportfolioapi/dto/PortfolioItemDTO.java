package com.outworld.mindly.cryptoportfolioapi.dto;

import com.outworld.mindly.cryptoportfolioapi.models.CryptoCurrency;
import com.outworld.mindly.cryptoportfolioapi.utils.validators.CryptoCurrencyConstraint;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PortfolioItemDTO {
    @Nullable
    private Long id;

    @NotNull(message = "Cryptocurrency must be set")
    @CryptoCurrencyConstraint
    private CryptoCurrency crypto;

    @NotNull(message = "Amount must be set")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    private Date dateOfPurchase;

    @Size(max = 100, message = "Location must not exceed 100 characters")
    private String location;

    @Nullable
    private double marketValue;
}
