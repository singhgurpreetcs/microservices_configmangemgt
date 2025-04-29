package com.gurpreet.cards.exception;

import com.gurpreet.cards.dto.ErrorResponseDto;
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
     * @param request the web request
     * @return a ResponseEntity containing a map of validation errors with a BAD_REQUEST status
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles any global exception which may occur.
     *
     * It returns a ResponseEntity with an INTERNAL_SERVER_ERROR status and an ErrorResponseDto as the body.
     * The ErrorResponseDto contains the error message, the status code and the current time.
     *
     * @param exception The exception to be handled
     * @param webRequest The WebRequest object
     * @return a ResponseEntity with an INTERNAL_SERVER_ERROR status and an ErrorResponseDto as the body
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
                                                                  WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponseDTO);
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
                                                                            WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the CardAlreadyExistsException which is thrown when a card with the same number already exists.
     * Constructs an ErrorResponseDto containing the error message, HTTP status code, and the current timestamp.
     * Returns a ResponseEntity with a BAD_REQUEST status and the ErrorResponseDto in the body.
     *
     * @param exception the CardAlreadyExistsException to be handled
     * @param webRequest the WebRequest in the context of which the exception occurred
     * @return a ResponseEntity with a BAD_REQUEST status and an ErrorResponseDto in the body
     */
    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCardAlreadyExistsException(CardAlreadyExistsException exception,
                                                                             WebRequest webRequest){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

}