package com.outworld.mindly.cryptoportfolioapi.services;

import com.outworld.mindly.cryptoportfolioapi.models.CryptoCurrency;
import com.outworld.mindly.cryptoportfolioapi.models.PortfolioItem;
import com.outworld.mindly.cryptoportfolioapi.repositories.CryptoCurrencyRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("cryptoCurrencyService")
public class CryptoCurrencyService {

    private final CryptoCurrencyRepository repository;


    public CryptoCurrencyService(CryptoCurrencyRepository repository) {
        this.repository = repository;
    }

    public List<CryptoCurrency> getAll() {
        return repository.findAll();
    }

    public CryptoCurrency create(CryptoCurrency cryptoCurrency) {
        return repository.save(cryptoCurrency);
    }

    public boolean exists(Example<CryptoCurrency> example) {
        return repository.exists(example);
    }

    public void delete(CryptoCurrency item) {
        repository.delete(item);
    }

    public Optional<CryptoCurrency> findById(Long id) {
        return repository.findById(id);
    }
}
