package com.udea.energym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.udea.energym.service.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {
	
	@Autowired
    private JavaMailSender mailSender;

	//Enviar correo electrónico de bienvenida de cancelación o reprogramación
	@Override
	public void enviarEmail(String destinatario, String asunto, String mensaje) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject(asunto);
        message.setText(mensaje);
        mailSender.send(message);
	}
}
