package br.com.crud.exception;

import br.com.crud.util.MessageCode;

public class PersistenceException extends CustomException {

    public PersistenceException(MessageCode message) {
        super(message);
    }
}
