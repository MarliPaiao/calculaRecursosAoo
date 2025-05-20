package br.com.solve4you.exceptionHandler;

import br.com.solve4you.domain.exception.CustomException;

public class EntityNotFoundException extends CustomException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
