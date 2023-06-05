package com.mjc.school.controller.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.mjc.school.controller.impl")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
}
