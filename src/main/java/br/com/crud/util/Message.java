package br.com.crud.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Message {

    private static final ResourceBundle RESOURCE_MSG = ResourceBundle.getBundle("i18n/messages", new Locale("pt_br"));

    private Message() {
        throw new IllegalStateException("Class not instantiable");
    }

    public static String get(String key) {
        return RESOURCE_MSG.getString(key);
    }

    public static String get(MessageCode code) {
        return get(code.toString());
    }
}
