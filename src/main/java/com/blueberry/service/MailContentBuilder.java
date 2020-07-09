package com.blueberry.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailContentBuilder {

	private final TemplateEngine templateEngine;
	
	public String build(String msg) {
		Context context = new Context();
		context.setVariable("message", msg);
		return templateEngine.process("mailTemplate", context);
	}
}
