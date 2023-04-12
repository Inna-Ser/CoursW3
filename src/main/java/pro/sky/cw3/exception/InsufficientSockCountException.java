package pro.sky.cw3.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class InsufficientSockCountException extends RuntimeException {
    public InsufficientSockCountException(String message) {
        super(message);
    }
}


