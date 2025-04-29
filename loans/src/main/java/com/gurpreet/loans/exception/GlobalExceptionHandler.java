package com.gurpreet.loans.exception;

import com.gurpreet.loans.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Handles MethodArgumentNotValidException triggered when a method argument fails validation.
     *
     * This method captures validation errors from the exception, extracts field names and validation messages,
     * and constructs a map of these errors. The map is then returned in the response entity with a BAD_REQUEST status.
     *
     * @param ex the exception containing validation errors
     * @param headers the HTTP headers
     * @param status the HTTP status code
     * @param webRequest the web request
     * @return a ResponseEntity containing a map of validation errors with a BAD_REQUEST status
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest)
    {
        Map<String,String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String validationMsg = error.getDefaultMessage();
                validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is used to handle any global exception which may occur.
     * It returns a ResponseEntity with an INTERNAL_SERVER_ERROR status and an ErrorResponseDto as the body.
     * The ErrorResponseDto contains the error message, the status code and the current time.
     *
     * @param exception The exception to be handled
     * @param webRequest The WebRequest object
     * @return a ResponseEntity with an INTERNAL_SERVER_ERROR status and an ErrorResponseDto as the body
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
                                                                  WebRequest webRequest){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles the ResourceNotFoundException which is thrown when a requested resource is not found.
     * Constructs an ErrorResponseDto containing the error message, HTTP status code, and the current timestamp.
     * Returns a ResponseEntity with a NOT_FOUND status and the ErrorResponseDto in the body.
     *
     * @param exception the ResourceNotFoundException that was thrown
     * @param webRequest the WebRequest in the context of which the exception occurred
     * @return a ResponseEntity with a NOT_FOUND status and an ErrorResponseDto in the body
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                            WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles the LoanAlreadyExistsException.
     * The exception is thrown when an attempt is made to create a loan that already exists for the given mobile number.
     * It returns a ResponseEntity with a BAD_REQUEST status and an ErrorResponseDto as the body.
     * The ErrorResponseDto contains the error message, the status code, and the current time.
     *
     * @param exception the LoanAlreadyExistsException to be handled
     * @param webRequest the WebRequest object
     * @return a ResponseEntity with a BAD_REQUEST status and an ErrorResponseDto as the body
     */
    @ExceptionHandler(LoanAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleLoanAlreadyExistsException(LoanAlreadyExistsException exception,
                                                                             WebRequest webRequest)
    {
        ErrorResponseDto errorResponseDto  = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
