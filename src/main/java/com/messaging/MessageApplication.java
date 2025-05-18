package com.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageApplication {
	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
		// Optionally auto-open browser to Swagger UI
		String url = "http://localhost:8080/swagger-ui/index.html";
		try { java.awt.Desktop.getDesktop().browse(java.net.URI.create(url)); } catch (Exception ignore) {}
	}
}