package com.outworld.mindly.cryptoportfolioapi.controllers;

import com.outworld.mindly.cryptoportfolioapi.dto.PortfolioItemDTO;
import com.outworld.mindly.cryptoportfolioapi.dto.ResponseDTO;
import com.outworld.mindly.cryptoportfolioapi.models.PortfolioItem;
import com.outworld.mindly.cryptoportfolioapi.services.PortfolioItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Validated
@CrossOrigin
@RequestMapping("/api/items")
public class PortfolioController {

    private final PortfolioItemService portfolioItemService;


    public PortfolioController(PortfolioItemService portfolioItemService) {
        this.portfolioItemService = portfolioItemService;
    }

    @GetMapping("/")
    ResponseEntity<ResponseDTO<Object>> findAll() {
        ResponseDTO<Object> responseDTO = ResponseDTO.builder().body(portfolioItemService.getAll()).build();
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/add")
    ResponseEntity<ResponseDTO<Object>> create(@Valid @RequestBody PortfolioItemDTO portfolioItemDTO) {
        ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                .body(portfolioItemService.create(portfolioItemDTO)).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseDTO<Object>> delete(@PathVariable Long id) {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);
        if (portfolioItem.isPresent()) {
            portfolioItemService.delete(portfolioItem.get());
            return ResponseEntity.ok(ResponseDTO.builder().build());
        }
        return ResponseEntity.notFound().build();
    }
}
