package br.com.apostas.dto;

import br.com.apostas.misc.Jsonable;

public class Message extends Jsonable {

    private final String message;

    public Message(String message) {
        this.message = message;
    }
}
