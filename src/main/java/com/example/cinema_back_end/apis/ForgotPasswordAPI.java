package com.example.cinema_back_end.apis;

import com.example.cinema_back_end.constants.ClientURL;
import com.example.cinema_back_end.entities.ResetPasswordToken;
import com.example.cinema_back_end.entities.User;
import com.example.cinema_back_end.security.service.IResetPasswordTokenService;
import com.example.cinema_back_end.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.AccountNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author tritcse00526x
 */
@RestController
@RequestMapping("/api/forgot-password")
public class ForgotPasswordAPI {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private IResetPasswordTokenService resetPasswordTokenService;
    @Autowired
    private IUserService userService;

    private static final String BASE_URL_RESET_PASSWORD = ClientURL.baseURL + "/forgot-password/reset-password";

    /*START - Forgot password step*/
    @GetMapping
    public ResponseEntity<String> sendResetPasswordToken(@RequestParam String email)  {
        System.out.println("LOG: Start sending reset password token");
        try {
            ResetPasswordToken token = resetPasswordTokenService.generateResetPasswordToken(email);
            sendEmail(email,BASE_URL_RESET_PASSWORD + "?token=" + token.getToken() + "&email=" + email);
        } catch (AccountNotFoundException e) {
            System.out.println("FAIL: Send reset password token - account not found " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Gửi mã xác thực thất bại!", HttpStatus.EXPECTATION_FAILED);
        } catch (UnsupportedEncodingException e) {
            System.out.println("FAIL: Send reset password token " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Gửi mã xác thực thất bại!", HttpStatus.EXPECTATION_FAILED);
        } catch (MessagingException e) {
            System.out.println("FAIL: Send reset password token - account not found " + e.getMessage());
            return new ResponseEntity<>("Có lỗi xảy ra, Gửi mã xác thực thất bại!", HttpStatus.EXPECTATION_FAILED);
        }
        System.out.println("SUCCESS: Send reset password token");
        return new ResponseEntity<>("Gửi mã xác thực thành công!", HttpStatus.OK);
    }

    public void sendEmail(String recipientEmail,String data)
            throws MessagingException, UnsupportedEncodingException {
        System.out.println("LOG: Start sending reset password email");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("no-reply@cineplex.vn", "Cineplex");
        helper.setTo(recipientEmail);
        String subject = "Link Reset Password";
        String content = "<p>Xin chào,</p>"
                + "<p>Theo yêu cầu của bạn, Cineplex gửi bạn thông tin để cài đặt lại mật khẩu</p>"
                + "<p>Truy cập đường dẫn bên dưới để cập nhật mật khẩu:</p>"
                + "<p><a href=\"" + data+ "\">Change my password</a></p>"
                + "<br>"
                + "<p>Bỏ qua email này nếu bạn nhớ mật khẩu, "
                + "hoặc bạn không có đưa ra yêu cầu này.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
        System.out.println("SUCCESS: Send reset password email!");
    }
    /*END - Forgot password step*/

    /*START - Reset password step*/
    //
    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestParam String token){
        System.out.println("LOG: Start validating reset password token");
        ResetPasswordToken rpToken = resetPasswordTokenService.getByToken(token);
        if(rpToken != null) {
            Date date = new Date();
            if(rpToken.getExpiryDate().compareTo(date) > 0) {
                System.out.println("SUCCESS: Validate reset password token");
                return new ResponseEntity<>("Mã xác thực hợp lệ!", HttpStatus.OK);
            } else {
                System.out.println("FAIL: Validate reset password token - expired token");
                return new ResponseEntity<>("Mã xác thực không còn hợp lệ!", HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            System.out.println("FAIL: Validate reset password token - invalid token");
            return new ResponseEntity<>("Mã xác thực không hợp lệ!", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping
    public ResponseEntity<?> resetPassword(
            @RequestParam String token,
            @RequestParam String password){
        System.out.println("LOG: Start resetting password");
        // get reset password token
        ResetPasswordToken rpToken = resetPasswordTokenService.getByToken(token);

        if(rpToken != null) { // if token is NOT NULL
            // get current date
            Date date = new Date();

            if(rpToken.getExpiryDate().compareTo(date) > 0) { // if the expiry date of that token is GREATER than the current date
                // equal to the token is still valid to use
                try {
                    // get user of that token
                    User user = rpToken.getUser();
                    // change password with that user id and the new password
                    userService.changePassword(user.getId(), password);
                    // remove token that requested to reset password
                    resetPasswordTokenService.remove(rpToken.getId());
                    System.out.println("SUCCESS: Reset password");
                    return new ResponseEntity<>("Đổi mật khẩu thành công!", HttpStatus.OK);
                } catch (RuntimeException e) { // if CANNOT change password or remove token
                    // return exception message
                    System.out.println("FAIL: Reset password " + e.getMessage());
                    return new ResponseEntity<>("Có lỗi xảy ra, Đổi mật khẩu thất bại!", HttpStatus.EXPECTATION_FAILED);
                }
            } else { // if the token is no longer valid to use
                System.out.println("FAIL: Reset password - expired token");
                return new ResponseEntity<>("Mã xác thực không còn hợp lệ!", HttpStatus.EXPECTATION_FAILED);
            }
        } else { // if token is NULL
            System.out.println("FAIL: Reset password - invalid token");
            return new ResponseEntity<>("Mã xác thực không hợp lệ!", HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*END - Reset password step*/

}
