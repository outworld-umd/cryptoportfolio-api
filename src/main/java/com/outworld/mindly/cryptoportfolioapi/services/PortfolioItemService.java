package com.outworld.mindly.cryptoportfolioapi.services;

import com.outworld.mindly.cryptoportfolioapi.dto.PortfolioItemDTO;
import com.outworld.mindly.cryptoportfolioapi.exceptions.ApiLimitException;
import com.outworld.mindly.cryptoportfolioapi.models.CryptoCurrency;
import com.outworld.mindly.cryptoportfolioapi.models.PortfolioItem;
import com.outworld.mindly.cryptoportfolioapi.repositories.PortfolioItemRepository;
import com.outworld.mindly.cryptoportfolioapi.utils.api.BitfinexApi;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("portfolioItemService")
public class PortfolioItemService {

    private final PortfolioItemRepository repository;
    private final BitfinexApi api;
    private final ModelMapper mapper = new ModelMapper();
    private Map<CryptoCurrency, BigDecimal> exchangeRatesMap;
    private final Function<PortfolioItem, PortfolioItemDTO> itemToDTOFunction = item -> {
        PortfolioItemDTO dto = mapper.map(item, PortfolioItemDTO.class);
        Optional<BigDecimal> marketValue = Optional.ofNullable(item.getAmount()
                .multiply(exchangeRatesMap.get(item.getCrypto())));
        marketValue.ifPresent(bigDecimal ->
                dto.setMarketValue(bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue()));
        return dto;
    };


    public PortfolioItemService(PortfolioItemRepository repository, BitfinexApi bitfinexApi) {
        this.repository = repository;
        this.api = bitfinexApi;
        this.exchangeRatesMap = new HashMap<>();
    }

    public List<PortfolioItemDTO> getAll() {
        List<PortfolioItem> portfolioItems = repository.findAll();
        getMarketValues(portfolioItems.stream().map(PortfolioItem::getCrypto).collect(Collectors.toSet()));
        return portfolioItems.stream().map(itemToDTOFunction).collect(Collectors.toList());
    }

    public PortfolioItem create(PortfolioItemDTO portfolioItemDTO) {
        if (portfolioItemDTO.getDateOfPurchase() == null) portfolioItemDTO.setDateOfPurchase(new Date());
        return repository.saveAndFlush(mapper.map(portfolioItemDTO, PortfolioItem.class));
    }

    public boolean exists(Example<PortfolioItem> example) {
        return repository.exists(example);
    }

    public void delete(PortfolioItem item) {
        repository.delete(item);
    }

    public void getMarketValues(Set<CryptoCurrency> currencySet) {
        currencySet.forEach(c -> {
            try {
                exchangeRatesMap.put(c, api.getExchangeRate(c));
            } catch (ApiLimitException ignored) {
            }
        });
    }

    public Optional<PortfolioItem> findById(Long id) {
        return repository.findById(id);
    }
}
