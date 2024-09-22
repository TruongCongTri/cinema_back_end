package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.dtos.BillDTO;
import com.example.cinema_back_end.services.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping(value = "/api/admin/bills")
public class ManageBillAPI {
    @Autowired
    private IBillService billService;

    /**TODO: ADMIN - manage BIll page*/
    @GetMapping
    public ResponseEntity<List<BillDTO>> findAllBills() {
        System.out.println("LOG: get all bills");
        return new ResponseEntity<>(billService.findAll(), HttpStatus.OK);
    }
    // remove existing bill
    /*@DeleteMapping
    public ResponseEntity<String> deleteBill(@Param("billId") Integer billId){
        try {
            billService.remove(billId);
            return new ResponseEntity<>("Xóa hóa đơn thành công!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Xóa hóa đơn thất bại!", HttpStatus.BAD_REQUEST);
        }
    }*/
}
