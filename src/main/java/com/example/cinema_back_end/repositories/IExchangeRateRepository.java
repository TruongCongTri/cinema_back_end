package com.example.cinema_back_end.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cinema_back_end.constants.Currency;
import com.example.cinema_back_end.entities.ExchangeRate;


/**
 * @author tritcse00526x
 */
public interface IExchangeRateRepository extends JpaRepository<ExchangeRate, Currency>{

}


