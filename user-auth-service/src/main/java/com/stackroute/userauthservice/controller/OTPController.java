package com.stackroute.userauthservice.controller;


import com.stackroute.userauthservice.model.LoginWithOtp;
import com.stackroute.userauthservice.service.EmailService;
import com.stackroute.userauthservice.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;


@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class OTPController{
    @Autowired
    private OTPService otpService;
    @Autowired
    private EmailService emailService;




    String email;

    /* to generate otp*/
    @PostMapping("generateOtp")
    public ResponseEntity<?> generateOtp(@RequestBody LoginWithOtp loginWithOtp) throws MessagingException {
        //This is id where mail will be send
        email = loginWithOtp.getUsername();
        int otpnumber = otpService.generateOTP(email);
        //This message will send in email
        String message = "<p>One Time Password to login:</p>" + "<p><b>" + otpnumber + "</b></p>";
        emailService.sendOtpMessage(email, "OTP -SpringBoot", message);
        return new ResponseEntity<String>("\"otp sent  \"", HttpStatus.OK);
    }
    /* To validate sent otp*/
    @PostMapping("validateOtp")

    public ResponseEntity<?> validateOtp(@RequestBody LoginWithOtp loginWithOtp) {
        final String SUCCESS = "\"Entered Otp is valid\""; //message when otp is valid
        final String FAIL = "\"Entered Otp is NOT valid. Please Retry!\""; //when invalid otp is entered

        /*Matching entered otp with Cache otp */
        int otpnumber=loginWithOtp.getOtpnumber();

        if (otpnumber >= 0) {
            int serverOtp = otpService.getOTP(email);
            if(serverOtp > 0){
                if(otpnumber == serverOtp){
                    otpService.clearOTP(email);
                    return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<String>(FAIL,HttpStatus.CONFLICT);
                }
            }else {
                return new ResponseEntity<String>(FAIL,HttpStatus.CONFLICT);
            }
        }else {
            return new ResponseEntity<String>(FAIL,HttpStatus.CONFLICT);
        }
    }
}
