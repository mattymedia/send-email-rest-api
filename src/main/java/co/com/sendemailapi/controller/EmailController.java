package co.com.sendemailapi.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.sendemailapi.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

	@PostMapping("/send")
	public String enviarCorreo() throws IOException {
        EmailService emailService = new EmailService();
        emailService.sendEmail();
        return "El correo ha sido enviado correctamente.";
    }
}
