package com.outworld.mindly.cryptoportfolioapi.repositories;

import com.outworld.mindly.cryptoportfolioapi.models.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
}
