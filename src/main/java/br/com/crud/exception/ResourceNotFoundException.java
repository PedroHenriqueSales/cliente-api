package br.com.crud.exception;

import br.com.crud.util.MessageCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends CustomException{

    public ResourceNotFoundException(MessageCode message) {
        super(message);
    }

}
