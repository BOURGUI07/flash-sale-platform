package com.example.customer_service.advice;

import com.example.customer_service.exceptions.CustomerAlreadyExistsException;
import com.example.customer_service.exceptions.CustomerNotFoundException;
import com.example.customer_service.exceptions.InvalidCustomerRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.util.function.Consumer;

@ControllerAdvice
public class ApplicationExceptionHandler {

    public ProblemDetail handleException(Exception ex, HttpStatus status, Consumer<ProblemDetail> consumer) {
        var problem= ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        consumer.accept(problem);
        return problem;
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ProblemDetail handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex) {
        return handleException(
                ex,
                HttpStatus.BAD_REQUEST,
                problemDetail -> {
                    problemDetail.setTitle("Customer already exists");
                    problemDetail.setType(URI.create("http://example.com/problems/customer-already-exists"));
                }
        );
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleCustomerNotFound(CustomerNotFoundException ex) {
        return handleException(
                ex,
                HttpStatus.NOT_FOUND,
                problemDetail -> {
                    problemDetail.setTitle("Customer not found");
                    problemDetail.setType(URI.create("http://example.com/problems/customer-not-found"));
                }
        );
    }

    @ExceptionHandler(InvalidCustomerRequestException.class)
    public ProblemDetail handleInvalidCustomerRequest(InvalidCustomerRequestException ex) {
        return handleException(
                ex,
                HttpStatus.BAD_REQUEST,
                problemDetail -> {
                    problemDetail.setTitle("Customer request is invalid");
                    problemDetail.setType(URI.create("http://example.com/problems/invalid-customer-request"));
                }
        );
    }
}
