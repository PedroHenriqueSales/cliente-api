package br.com.crud.exception;

import br.com.crud.util.Message;
import br.com.crud.util.MessageCode;
import br.com.crud.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomException extends RuntimeException {

	public CustomException(MessageCode code) {
		super(Message.get(code));
	}

	public CustomException(MessageCode code, String... parametros) {
		super(Utils.formatarString(Message.get(code), parametros));
	}

	protected void logger(Class<?> loggerClass, String message) {
		Logger logger = LoggerFactory.getLogger(loggerClass);
		logger.warn(message);
	}

}
