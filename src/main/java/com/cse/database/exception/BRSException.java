package com.cse.database.exception;

import org.springframework.stereotype.Component;

@Component
public  class BRSException {

    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String message) {
            super(message);
        }
    }

    public static class AccountExpiredException extends RuntimeException {
        public AccountExpiredException(String message) {super(message);}
    }

}
