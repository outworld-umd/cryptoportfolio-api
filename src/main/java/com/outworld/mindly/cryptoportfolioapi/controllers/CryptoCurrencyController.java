package com.outworld.mindly.cryptoportfolioapi.controllers;

import com.outworld.mindly.cryptoportfolioapi.dto.ResponseDTO;
import com.outworld.mindly.cryptoportfolioapi.models.CryptoCurrency;
import com.outworld.mindly.cryptoportfolioapi.services.CryptoCurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Validated
@CrossOrigin
@RequestMapping("/api/currencies")
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;

    public CryptoCurrencyController(CryptoCurrencyService cryptoCurrencyService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
    }

    @GetMapping("/")
    ResponseEntity<ResponseDTO<Object>> findAll() {
        ResponseDTO<Object> responseDTO = ResponseDTO.builder().body(cryptoCurrencyService.getAll()).build();
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/add")
    ResponseEntity<ResponseDTO<Object>> create(@Valid @RequestBody CryptoCurrency cryptoCurrency) {
        cryptoCurrencyService.create(cryptoCurrency);
        ResponseDTO<Object> responseDTO = ResponseDTO.builder().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseDTO<Object>> delete(@PathVariable Long id) {
        Optional<CryptoCurrency> currency = cryptoCurrencyService.findById(id);
        if (currency.isPresent()) {
            cryptoCurrencyService.delete(currency.get());
            return ResponseEntity.ok(ResponseDTO.builder().build());
        }
        return ResponseEntity.notFound().build();
    }


}
