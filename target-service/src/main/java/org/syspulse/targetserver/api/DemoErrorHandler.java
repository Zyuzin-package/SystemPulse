package org.syspulse.targetserver.api;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.syspulse.targetserver.utils.exception.DemoCustomException;

import java.time.Instant;

@RestControllerAdvice
class DemoErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(DemoErrorHandler.class);

    @ExceptionHandler({DemoCustomException.class, Exception.class, Error.class})
    ResponseEntity<DemoErrorResponse> handle(Throwable error, HttpServletRequest request) {
        log.error("Demo endpoint failed: {}", request.getRequestURI(), error);

        DemoErrorResponse response = new DemoErrorResponse(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRequestURI(),
                error.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
