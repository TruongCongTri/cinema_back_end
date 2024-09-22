package com.example.cinema_back_end.apis;

import com.example.cinema_back_end.constants.DefaultConfig;
import com.example.cinema_back_end.dtos.BillDTO;
import com.example.cinema_back_end.dtos.BookingRequestDTO;
import com.example.cinema_back_end.dtos.MovieDTO;
import com.example.cinema_back_end.entities.Ticket;
import com.example.cinema_back_end.entities.User;
import com.example.cinema_back_end.security.service.IUserService;
import com.example.cinema_back_end.security.service.UserService;
import com.example.cinema_back_end.services.IBillService;
import com.example.cinema_back_end.utils.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping("/api/bills")
public class BillAPI {
    @Autowired
    private IBillService billService;
    @Autowired
    private IUserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private JavaMailSender mailSender;

    // create new bill and send email
    @PostMapping("/create-new-bill")
    @Transactional
    public ResponseEntity<String> createNewBill(@RequestBody BookingRequestDTO bookingRequestDTO) {
        System.out.println("LOG: Start creating new bill");
        try {
            User user = userService.findById(bookingRequestDTO.getUserId()).get();
            List<Ticket> tickets = billService.createNewBill(bookingRequestDTO);
            String imageHtml = "";
            String infoHtml = "";
            String ticketsHtml = "";
            String scheduleHtml = "";
            for (Ticket ticket : tickets) {
                imageHtml = "<img src='" + ticket.getSchedule().getMovie().getSmallImageURl() + "'/>";
                infoHtml = "<h2><bold>" + ticket.getSchedule().getMovie().getName() + "</bold></h2>"
                        + "<h3><bold>" + ticket.getSchedule().getBranch().getName() + "</bold> - " + ticket.getSchedule().getBranch().getAddress() + "</h3>"
                        + "<h3>Rạp: <bold>" + ticket.getSchedule().getRoom().getName() + "</bold></h3>"
                        + "<h3>Ngày chiếu: <bold>" + ticket.getSchedule().getStartDate() + "</bold> - <bold>" + ticket.getSchedule().getStartTime() + "</bold></h3>";
                ticketsHtml += ticket.getSeat().getName() + ",";
                scheduleHtml += "<tr class='item'>"
                        + "<td>" + ticket.getSeat().getName() + "</td>"
                        + "<td><bold>" + ticket.getQrCode() + "</bold></td>"
                        + "</tr>";

            }
            sendEmail(user, bookingRequestDTO, imageHtml, infoHtml, ticketsHtml, scheduleHtml);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("FAIL: Create new bill " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Mua vé thất bại!", HttpStatus.EXPECTATION_FAILED);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("FAIL: Create new bill " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Mua vé thất bại!", HttpStatus.EXPECTATION_FAILED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("FAIL: Create new bill - token expired " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Mua vé thất bại!", HttpStatus.EXPECTATION_FAILED);
        }
        System.out.println("SUCCESS: Create new bill");
        return new ResponseEntity<>("Bạn đã mua vé xem phim thành công!", HttpStatus.OK);
    }

    //pack ticket's info into txt file
    //then send via email to buyer
    private void sendEmail(User user, BookingRequestDTO bookingRequestDTO, String imageHtml, String infoHtml, String ticketsHtml, String scheduleHtml)
            throws MessagingException, UnsupportedEncodingException {
        System.out.println("LOG: Start sending ticket email");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("no-reply@cineplex.com", "Cineplex");
        helper.setTo(DefaultConfig.MY_EMAIL);
        String subject = "Chúc mừng bạn đặt vé thành công!";
        String content = "";
        try {
            content = fileService.readFile("/thankyouforbooking.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // format date and time
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String now = LocalDateTime.now().format(outputFormatter);

        // format currency
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        // format amount of money follow with "100.000"
        String formattedAmount = en.format(bookingRequestDTO.getAmount());

        helper.setSubject(subject);

        helper.setText(content
                .replace("%1$s", imageHtml)
                .replace("%2$s", infoHtml)
                .replace("%3$s", ticketsHtml)
                .replace("%4$s", scheduleHtml)
                .replace("%6$s", formattedAmount + "đ")
                .replace("%7$s", now)
                .replace("%5$s", bookingRequestDTO.getOrderId()), true);

        mailSender.send(message);
        System.out.println("SUCCESS: Send ticket email!");
    }

}
