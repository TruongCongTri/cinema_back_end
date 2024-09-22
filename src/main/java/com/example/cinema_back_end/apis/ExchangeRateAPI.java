package com.example.cinema_back_end.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cinema_back_end.constants.Currency;
import com.example.cinema_back_end.repositories.IExchangeRateRepository;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping(value = "/api/currency", produces = "application/json")
public class ExchangeRateAPI {
    @Autowired
    private IExchangeRateRepository exchangeRateRepository;

    @GetMapping("/vnd")
    public Double toVND(@RequestParam Double vndMoney, @RequestParam Currency currency) {
        System.out.println("LOG: Exchange usd to vnd in paypal payment method");
        return vndMoney/exchangeRateRepository.findById(currency).get().getToVND();
    }
}
