package shinydorky.mos.law_generator_backend.rest.error;


import jakarta.persistence.EntityNotFoundException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import shinydorky.mos.law_generator_backend.dto.ErrorMessage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
        Map<String, List<String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(f -> f.getDefaultMessage(), Collectors.toList())));

        Map<String, List<String>> globalErrors = ex.getBindingResult().getGlobalErrors().stream().collect(Collectors.groupingBy(ObjectError::getObjectName,
                Collectors.mapping(f -> f.getDefaultMessage(), Collectors.toList())));

        errors.putAll(globalErrors);
        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(responseStatus)
                .errors(errors)
                .build();
        return new ResponseEntity<>(errorMessage, responseStatus);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ErrorMessage> handleEntityNotFound(EntityNotFoundException ex){
        return null;
    }
}