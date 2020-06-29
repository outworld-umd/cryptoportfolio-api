package com.outworld.mindly.cryptoportfolioapi.utils.validators;

import com.outworld.mindly.cryptoportfolioapi.models.CryptoCurrency;
import com.outworld.mindly.cryptoportfolioapi.services.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CryptoCurrencyValidator implements ConstraintValidator<CryptoCurrencyConstraint, CryptoCurrency> {

    @Autowired
    private CryptoCurrencyService cryptoCurrencyService;

    public void initialize(CryptoCurrencyConstraint constraint) {
    }

    public boolean isValid(CryptoCurrency obj, ConstraintValidatorContext context) {
        return obj != null && cryptoCurrencyService.exists(Example.of(obj));
    }
}
