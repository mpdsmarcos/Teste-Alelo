package com.teste.alelo.services;

import org.springframework.mail.SimpleMailMessage;

import com.teste.alelo.domain.Cliente;

public interface EmailService {
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass) ;
}