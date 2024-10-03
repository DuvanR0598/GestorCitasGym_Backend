package com.udea.energym.service;

public interface IEmailService {

	void enviarEmail(String destinatario, String asunto, String mensaje);
	
}
