package com.udea.energym.service;

public interface IEmailService {

	void sendWelcomeEmail(String to, String subject, String text);
}
