package hyundai.cc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
@Log
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("NOT FOUND", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PageNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePageNotFoundException(PageNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("NOT FOUND", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<Map<String, String>> handleUserCreationException(UserCreationException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("NOT FOUND", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(LectureNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleLectureNotFoundException(LectureNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("NOT FOUND", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DuplicateNicknameException.class)
    public ResponseEntity<?> handleDuplicateNicknameException(DuplicateNicknameException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("nickname", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEmailException(DuplicateEmailException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("email", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<Map<String, String>> handleBindException(BindException e) {
        log.info("handleBindException"+ e);
        Map<String, String> response = new HashMap<>();
        response.put("GENERAL", String.valueOf(e.getBindingResult()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.info("handleMethodArgumentTypeMismatchException"+ e);
        Map<String, String> response = new HashMap<>();
        response.put("GENERAL", "잘못된 요청입니다.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.info("handleHttpRequestMethodNotSupportedException"+ e);
        Map<String, String> response = new HashMap<>();
        response.put("GENERAL", "허용되지 않는 요청입니다.");
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
     */
//    @ExceptionHandler(AccessDeniedException.class)
//    protected ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
//        log.info("handleAccessDeniedException"+ e);
//        Map<String, String> response = new HashMap<>();
//        response.put("GENERAL", "허용되지 않는 권한입니다.");
//        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
//    }

    /**
     * 비즈니스 로직 수행 도중, 사용자의 요청 파라미터가 적절하지 않을 때 발생
     */
    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<?> handleIllegalStatementException(IllegalStateException e){
        log.info("illegalStateException"+ e);
        Map<String, String> response = new HashMap<>();
        response.put("GENERAL", "잘못된 요청입니다.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 비즈니스 로직 수행 도중, 해당 도메인 객체의 상태가 로직을 수행할 수 없을 때 발생
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e){
        log.info("illegalArgumentException"+ e);
        Map<String, String> response = new HashMap<>();
        response.put("GENERAL", "잘못된 요청입니다.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<?> handleException(Exception ex) {
//        Map<String, String> errorResponse = new HashMap<>();
//        errorResponse.put("GENERAL", "ERROR");
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @Getter
    @AllArgsConstructor
    private static class ErrorResponse {
        private final int status;
        private final String message;
    }
}

