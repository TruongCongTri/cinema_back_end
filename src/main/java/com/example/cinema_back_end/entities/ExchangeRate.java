package com.example.cinema_back_end.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.example.cinema_back_end.constants.Currency;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tritcse00526x
 */
@Data
@Entity
@NoArgsConstructor
public class ExchangeRate {
    @Id
    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Double toVND;
}
