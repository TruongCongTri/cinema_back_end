package com.example.cinema_back_end.services;

import java.util.List;

/**
 * @author tritcse00526x
 */
public interface IGeneralService<T> {
    List<T> findAll();

    T getById(Integer id);

    void update(T t);

    void remove(Integer id);
}
