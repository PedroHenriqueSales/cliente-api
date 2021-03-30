package br.com.crud.exception;

import br.com.crud.util.MessageCode;

public class ClienteParamsException extends CustomException {

    public ClienteParamsException(MessageCode message) {
        super(message);
    }

    public ClienteParamsException(MessageCode code, String... parametros) {
        super(code, parametros);
    }

}

