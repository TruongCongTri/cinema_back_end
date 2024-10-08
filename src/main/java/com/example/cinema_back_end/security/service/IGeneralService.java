package com.example.cinema_back_end.security.service;

import java.util.List;
import java.util.Optional;

/**
 * @author tritcse00526x
 */
public interface IGeneralService<T> {
    List<T> findAll();
    Optional<T> findById(Integer id);
    T save(T t);
    void remove(Integer id);
}
