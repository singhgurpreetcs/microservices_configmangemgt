package com.gurpreet.accounts.exception;

import com.gurpreet.accounts.dto.ErrorResponseDto;
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
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList  = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error)-> {
                    String fieldName = ((FieldError)error).getField();
                    String validationMsg = error.getDefaultMessage();
                    validationErrors.put(fieldName, validationMsg);
                }
        );
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is used to handle any global exception which may occur.
     * It returns a ResponseEntity with an INTERNAL_SERVER_ERROR status and an ErrorResponseDto as the body.
     * The ErrorResponseDto contains the error message, the status code and the current time.
     * @param exception The exception to be handled
     * @param webRequest The WebRequest object
     * @return a ResponseEntity with an INTERNAL_SERVER_ERROR status and an ErrorResponseDto as the body
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
                                                                                 WebRequest webRequest){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method is used to handle the CustomerAlreadyExistsException.
     * The exception is thrown by the AccountsServiceImpl when a customer with the same mobile number already exists.
     * The method returns a ResponseEntity with a BAD_REQUEST status and an ErrorResponseDto as the body.
     * The ErrorResponseDto contains the error message, the status code and the current time.
     *
     * @param exception the CustomerAlreadyExistsException to be handled
     * @param webRequest the WebRequest object
     * @return a ResponseEntity with a BAD_REQUEST status and an ErrorResponseDto as the body
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,
                                                                                 WebRequest webRequest){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is used to handle the ResourceNotFoundException.
     * The exception is thrown by the AccountsServiceImpl when the requested customer does not exist.
     * The method returns a ResponseEntity with a NOT_FOUND status and an ErrorResponseDto as the body.
     * The ErrorResponseDto contains the error message, the status code and the current time.
     *
     * @param exception the ResourceNotFoundException to be handled
     * @param webRequest the WebRequest object
     * @return a ResponseEntity with a NOT_FOUND status and an ErrorResponseDto as the body
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                                 WebRequest webRequest){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }
}
