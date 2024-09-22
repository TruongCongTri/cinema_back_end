package com.example.cinema_back_end.apis.admin;

import com.example.cinema_back_end.dtos.TicketDTO;
import com.example.cinema_back_end.services.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping(value = "/api/admin/tickets")
public class ManageTicketAPI {
    @Autowired
    private ITicketService ticketService;

    /**TODO: ADMIN - manage TICKET page*/

    @GetMapping("/{qrCode}")
    public ResponseEntity<TicketDTO> findCheckinTicket(@PathVariable String qrCode){
        System.out.println("LOG: get ticket detail by ticket code");
        try {
            return new ResponseEntity<>(ticketService.findTicketByQrCode(qrCode), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{qrCode}/checkin")
    public ResponseEntity<String> checkinTicket(@PathVariable String qrCode){
        try {
            ticketService.checkinTicket(qrCode);
            //System.out.println("SUCCESS: Update ticket's check-in status");
            return new ResponseEntity<String>("Soát vé thành công!" ,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update ticket's check-in status - " + e.getMessage());
            return new ResponseEntity<String>("Có lỗi xảy ra, Soát vé thất bại!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<List<TicketDTO>> findAllTicketsByBill(@Param("billId") Integer billId) {
        System.out.println("LOG: get all ticket in bill");
        return new ResponseEntity<>(ticketService.getTicketsByBill(billId), HttpStatus.OK);
    }

//INACTIVE
    /*@GetMapping("/search")
    public ResponseEntity<TicketDTO> searchTicket(@RequestParam String qrCode){
        System.out.println("LOG: get ticket detail by ticket code");
        try {
            return new ResponseEntity<>(ticketService.findTicketByQrCode(qrCode), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping
    public ResponseEntity<String> updateTicket(@RequestBody TicketDTO ticket){
        System.out.println("LOG: Start updating ticket's check-in status");
        try {
            ticketService.update(ticket);
            System.out.println("SUCCESS: Update ticket's check-in status");
            return new ResponseEntity<String>("Soát vé thành công!" ,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("FAIL: Update ticket's check-in status - " + e.getMessage());
            return new ResponseEntity<String>("Có lỗi xảy ra, Soát vé thất bại!", HttpStatus.BAD_REQUEST);
        }
    }*/
    /*@DeleteMapping
    public ResponseEntity<String> deleteTicket(@Param("ticketId") Integer ticketId) {
        ticketService.remove(ticketId);
        return new ResponseEntity<>("Xóa vé thành công!", HttpStatus.OK);
    }*/

    /*@GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets(){
        return new ResponseEntity<>(ticketService.findAll(), HttpStatus.OK);
    }*/

    /*@GetMapping("/detail")
    public TicketDTO getTicketDetail(@RequestParam Integer ticketId){
        System.out.println("LOG: get ticket detail");
        return ticketService.getById(ticketId);
    }*/
}
