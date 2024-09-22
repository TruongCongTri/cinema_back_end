package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tritcse00526x
 */
public interface IBillRepository extends JpaRepository<Bill, Integer> {

}
